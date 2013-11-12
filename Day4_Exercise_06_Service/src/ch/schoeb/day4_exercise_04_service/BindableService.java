package ch.schoeb.day4_exercise_04_service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class BindableService extends Service {

	//TODO: Create a binder here
	
	@Override
	public IBinder onBind(Intent intent) {
		//TODO return your binder here
		return null;
	}

	public void writeSomethingToLogFile(String data) {
		Log.d("BindableService", data);
	}
}
