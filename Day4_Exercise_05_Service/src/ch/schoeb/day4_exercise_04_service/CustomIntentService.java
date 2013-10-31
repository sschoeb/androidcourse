package ch.schoeb.day4_exercise_04_service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class CustomIntentService extends IntentService {

	public CustomIntentService() {
		super("CustomIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		String extraData = intent.getExtras().getString("data");
		Log.d("CustomIntentService", extraData);

	}

}
