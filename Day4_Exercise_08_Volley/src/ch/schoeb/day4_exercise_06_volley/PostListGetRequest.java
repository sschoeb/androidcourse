package ch.schoeb.day4_exercise_06_volley;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class PostListGetRequest extends Request<ArrayList<Post>> {
	private final Gson gson = new Gson();
	private final Listener<ArrayList<Post>> listener;

	public PostListGetRequest(String url, Listener<ArrayList<Post>> listener, ErrorListener errorListener) {
		super(Method.GET, url, errorListener);
		this.listener = listener;
	}

	@Override
	protected Response<ArrayList<Post>> parseNetworkResponse(NetworkResponse response) {
		try {
			// Get Json from response
			Class<ArrayList<Post>> clazz = (Class<ArrayList<Post>>) (Class<?>) ArrayList.class;
			String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
			
			// Use Gson to parse json to an ArrayList of posts
			ArrayList<Post> posts = gson.fromJson(json, clazz);
			return Response.success(posts, HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		} catch (JsonSyntaxException e) {
			return Response.error(new ParseError(e));
		}
	}

	@Override
	protected void deliverResponse(ArrayList<Post> response) {
		listener.onResponse(response);
	}
}
