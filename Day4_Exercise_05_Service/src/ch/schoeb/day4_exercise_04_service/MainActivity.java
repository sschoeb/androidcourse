package ch.schoeb.day4_exercise_04_service;

import ch.schoeb.day4_exercise_04_service.BindableService.BindableServiceBinder;
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
			Log.d("mydata", "NOT WORKING");
			service = null;
		}

		@Override
		public void onServiceConnected(ComponentName componentName, IBinder binder) {
			Log.d("mydata", "GOT I");
			service = ((BindableServiceBinder) binder).getService();
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
		bindService(new Intent(this, BindableService.class), bindableServiceConnection, Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onPause() {
		super.onPause();
		unbindService(bindableServiceConnection);
	}

	private void writeToIntentService() {
		Intent intent = new Intent(this, CustomIntentService.class);
		intent.putExtra("data", "This is the data");
		startService(intent);
	}

	private void writeToBindableService() {
		service.writeSomethingToLogFile("Data to be dumped");
	}
}
