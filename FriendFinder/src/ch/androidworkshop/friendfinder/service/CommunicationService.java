package ch.androidworkshop.friendfinder.service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import ch.androidworkshop.friendfinder.contentprovider.ContentProviderHelper;
import ch.androidworkshop.friendfinder.contentprovider.PostsContract;
import ch.androidworkshop.friendfinder.model.Post;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CommunicationService extends Service {

	private static String baseUrl = "http://androidcourse.azurewebsites.net/api/";
	private static String postUrlPart = "post/";

	public class CommunicationServiceBinder extends Binder {
		public CommunicationService getService() {
			return CommunicationService.this;
		}
	}

	private CommunicationServiceBinder binder = new CommunicationServiceBinder();

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		requestPosts(new PostRequestListener() {

			@Override
			public void onPostsReceiveingFailed() {

			}

			@Override
			public void onPostsReceived(ArrayList<Post> posts) {

				if (posts.size() == 0) {
					Log.i("FriendFinder", "Got no messages from server");
					return;
				}

				Log.i("FriendFinder", "Got messages from server: " + posts.size());
				ContentValues[] contentValues = new ContentValues[posts.size()];
				for (int i = 0; i < contentValues.length; i++) {
					Post post = posts.get(i);
					ContentValues value = new ContentValues();

					value.put(PostsContract.Posts.COLUMN_NAME_COMMENT, saveValue(post.comment));
					value.put(PostsContract.Posts.COLUMN_NAME_NAME, saveValue(post.name));
					value.put(PostsContract.Posts.COLUMN_NAME_LONGITUDE, post.longitude);
					value.put(PostsContract.Posts.COLUMN_NAME_LATITUDE, post.latitude);

					contentValues[i] = value;
				}

				getContentResolver().bulkInsert(PostsContract.Posts.CONTENT_URI, contentValues);
			}
		});

		return super.onStartCommand(intent, flags, startId);
	}

	private String saveValue(String value) {
		if (value == null) {
			return "";
		}
		return value;
	}

	@Override
	public IBinder onBind(Intent arg) {
		return binder;
	}

	public void send(Post post, SendCallbackListener sendCallbackListener) {
		Log.i("FriendFinder", "Start sending post to azure");
		new SenderAsyncTask(post, sendCallbackListener).execute();
	}

	public void requestPosts(PostRequestListener listener) {
		Log.i("FriendFinder", "Requesting posts from azure");
		new RequestAsyncTask(listener).execute();
	}

	public interface PostRequestListener {
		void onPostsReceived(ArrayList<Post> posts);

		void onPostsReceiveingFailed();
	}

	public interface SendCallbackListener {
		void onPostSuccessfullySent(Post post, int newId);

		void onPostSendFailed();
	}

	private class SenderAsyncTask extends AsyncTask<Void, Void, Integer> {
		private SendCallbackListener listener;
		private Post post;

		public SenderAsyncTask(Post post, SendCallbackListener listener) {
			this.listener = listener;
			this.post = post;
		}

		@Override
		protected Integer doInBackground(Void... args) {
			HttpURLConnection urlConnection = null;

			try {
				URL url = new URL(baseUrl + postUrlPart);
				urlConnection = (HttpURLConnection) url.openConnection();
				urlConnection.setRequestProperty("Content-Type", "application/json");
				urlConnection.setRequestMethod("POST");

				String json = objectToJson(post);
				writeJsonToUrlConnection(urlConnection, json);

				Log.i("FriendFinder", "JSON: " + json);
				Log.i("FriendFinder", "Sending done:" + urlConnection.getResponseCode());

				String answer = ReadAnswer(urlConnection);

				Log.i("FriendFinder", "Answer: " + answer);
				return Integer.parseInt(answer);

			} catch (MalformedURLException e) {
				return -1;
			} catch (IOException e) {
				return -1;
			} finally {
				if (urlConnection != null) {
					urlConnection.disconnect();
				}
			}
		}

		private String ReadAnswer(HttpURLConnection urlConnection) throws UnsupportedEncodingException, IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
			String line = null;
			StringBuffer sb = new StringBuffer();

			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();
			return sb.toString();
		}

		@Override
		protected void onPostExecute(Integer result) {
			if (result >= 0) {
				listener.onPostSuccessfullySent(post, result);
			} else {
				listener.onPostSendFailed();
			}
		}

		private void writeJsonToUrlConnection(HttpURLConnection urlConnection, String json) throws IOException {
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(urlConnection.getOutputStream());
			outputStreamWriter.write(json);
			outputStreamWriter.flush();
		}

		private String objectToJson(Object data) {
			Gson gson = new Gson();
			return gson.toJson(data);
		}
	}

	private class RequestAsyncTask extends AsyncTask<Void, Void, Void> {

		private PostRequestListener listener;

		public RequestAsyncTask(PostRequestListener listener) {
			this.listener = listener;
		}

		@Override
		protected Void doInBackground(Void... params) {
			HttpURLConnection urlConnection = null;

			try {
				URL url = new URL(baseUrl + postUrlPart + ContentProviderHelper.getMaximumPostId(getContentResolver()));
				urlConnection = (HttpURLConnection) url.openConnection();

				BufferedInputStream in = new BufferedInputStream(urlConnection.getInputStream());
				String json = getStreamContent(in);

				Log.i("FriendFinder", "Got json from azure: " + json);

				ArrayList<Post> data = jsonToObject(json, new TypeToken<ArrayList<Post>>() {
				}.getType());
				listener.onPostsReceived(data);

			} catch (MalformedURLException e) {
				listener.onPostsReceiveingFailed();
				return null;
			} catch (IOException e) {
				listener.onPostsReceiveingFailed();
				return null;
			} finally {
				if (urlConnection != null) {
					urlConnection.disconnect();
				}
			}
			return null;
		}

		private <T> T jsonToObject(String json, Type type) {
			Gson gson = new Gson();
			return gson.fromJson(json, type);
		}

		private String getStreamContent(BufferedInputStream in) throws IOException {
			StringBuilder inputStringBuilder = new StringBuilder();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				inputStringBuilder.append(line);
			}
			String json = inputStringBuilder.toString();
			return json;
		}

	}
}
