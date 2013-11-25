package ch.androidworkshop.widget;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

	private static final String PREFERENCE_NAME = "counter";
	private static final String COUNTER_KEY = "currentcounter";

	public static long getCurrentCounter(Context context) {
		return getSharedPreferences(context).getLong(COUNTER_KEY, 0);
	}

	public static void setCurrentCounter(Context context, long value) {
		SharedPreferences preferences = getSharedPreferences(context);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putLong(COUNTER_KEY, value);
		editor.commit();
	}

	private static SharedPreferences getSharedPreferences(Context context) {
		return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
	}

}
