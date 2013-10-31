package ch.schoeb.day4_exercise_05_httpurlconnection;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView resultTextView;
	private EditText urlEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		resultTextView = (TextView) findViewById(R.id.resultTextView);
		urlEditText = (EditText) findViewById(R.id.urlEditText);


		Button loadButton = (Button) findViewById(R.id.loadButton);
		loadButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				new DataLoader().execute(urlEditText.getText().toString());
			}
		});
	}

	class DataLoader extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... urlToLoadParam) {

			String urlToLoad = urlToLoadParam[0];
			HttpURLConnection urlConnection = null;
			String data = "";
			try {
				URL url = new URL(urlToLoad);
				urlConnection = (HttpURLConnection) url.openConnection();
				data = getStreamContent(new BufferedInputStream(urlConnection.getInputStream()));
				Log.d("UrlConnection", "Start waiting");
				Thread.sleep(50000);
				Log.d("UrlConnection", "Waiting done");
			} catch (MalformedURLException e) {
				Log.d("UrlConnection", "MalformedUrl");
			} catch (IOException e) {
				Log.d("UrlConnection", "IOException");
			} catch (InterruptedException e) {
				Log.d("UrlConnection", "InterruptedException");
			} finally {
				if (urlConnection != null) {
					urlConnection.disconnect();
				}
			}


			Log.d("UrlConnection", "Thread finished");
			return data;
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
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			
			Log.d("UrlConnection", "Loading finished -> set to UI");
			resultTextView.setText(result);
		}

	}
}
