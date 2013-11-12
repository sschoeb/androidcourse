package ch.schoeb.day4_exercise_04_service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private BindableService service;
	private ServiceConnection bindableServiceConnection = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName componentName) {
			service = null;
		}

		@Override
		public void onServiceConnected(ComponentName componentName, IBinder binder) {
			// TODO: Get service instance from your binder here
			// You may have to cast the binder first...
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btnWriteBindable = (Button) findViewById(R.id.writeToBindableButton);
		btnWriteBindable.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				writeToBindableService();
			}
		});

		Button btnWriteIntentService = (Button) findViewById(R.id.writeToIntentServiceButton);
		btnWriteIntentService.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				writeToIntentService();
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		// TODO: Bind to BindableService here
	}

	@Override
	protected void onPause() {
		super.onPause();
		// TODO: Make sure bindableService is released
	}

	private void writeToIntentService() {
		// TODO: Send intent to CustomIntentService
		// TODO: Pass "data" - extra to it
	}

	private void writeToBindableService() {
		// TODO: Write something to bindable service
	}
}
