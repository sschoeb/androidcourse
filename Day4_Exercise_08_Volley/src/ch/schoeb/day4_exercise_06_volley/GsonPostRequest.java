package ch.schoeb.day4_exercise_06_volley;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

public class GsonPostRequest<T> extends Request<T> {
	private final Gson gson = new Gson();
	private final T data;
	private final Listener<Object> listener;

	public GsonPostRequest(String url, Class<T> clazz, T data, Listener<Object> listener, ErrorListener errorListener) {
		super(Method.POST, url, errorListener);
		
		this.data = data;
		this.listener = listener;
	}

	@Override
	public byte[] getBody() throws AuthFailureError {
		// Get Json representation for the given object
		String json = gson.toJson(data);
		return json.getBytes();
	}
	
	@Override
	public String getBodyContentType() {
		return "application/json";
	}
	
	@Override
	protected void deliverResponse(Object response) {
		listener.onResponse(response);
	}

	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		// We return the posted object as response
		return Response.success(data, HttpHeaderParser.parseCacheHeaders(response));
	}
}
