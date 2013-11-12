package ch.schoeb.day4_exercise_06_volley;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Application;

public class ApplicationController extends Application {

	private static RequestQueue queue;

	public void onCreate() {
		//TODO: Get the volley queue here
	};

	public static RequestQueue getVolleyRequestQueue() {
		return queue;
	}
}
