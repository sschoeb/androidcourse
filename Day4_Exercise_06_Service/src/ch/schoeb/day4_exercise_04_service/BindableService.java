package ch.schoeb.day4_exercise_04_service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class BindableService extends Service {

	private final IBinder binder = new BindableServiceBinder();

	public class BindableServiceBinder extends Binder {
		public BindableService getService() {
			return BindableService.this;
		}
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}

	public void writeSomethingToLogFile(String data) {
		Log.d("BindableService", data);
	}
}
