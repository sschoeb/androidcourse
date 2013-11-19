package ch.androidworkshop.alarmmanager;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button chooseTimeButton = (Button) findViewById(R.id.buttonChooseDate);
		chooseTimeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showTimePickerFragment();
			}
		});
	}

	private void showTimePickerFragment() {
		TimePickerDialogFragment timePicker = new TimePickerDialogFragment();
		timePicker.setHandler(handler);

		timePicker.setArguments(getArgumentBundle());

		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.add(timePicker, "time_picker");
		ft.commit();
	}

	private Bundle getArgumentBundle() {
		Calendar calendar = Calendar.getInstance();
		int hours = calendar.get(Calendar.HOUR);
		int minutes = calendar.get(Calendar.MINUTE);

		Bundle bundle = new Bundle();
		bundle.putInt("set_hour", hours);
		bundle.putInt("set_minute", minutes);
		return bundle;
	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message m) {
			Bundle b = m.getData();
			int hour = b.getInt("set_hour");
			int minute = b.getInt("set_minute");

			scheduleAlarm(hour, minute);
		}
	};

	private void scheduleAlarm(int hour, int minute) {
		TextView textViewCurrentAlarm = (TextView) findViewById(R.id.textViewCurrentAlarm);
		textViewCurrentAlarm.setText("Current Alarm: " + hour + ":" + minute);
		
		Calendar target = Calendar.getInstance();
		target.set(Calendar.HOUR, hour);
		target.set(Calendar.MINUTE, minute);
		target.set(Calendar.SECOND, 0);
		target.set(Calendar.MILLISECOND, 0);
		
		// TODO: Make sure alarm is set here
		Log.d("AlarmReceiver", "Set alarm");
		Intent intent = new Intent(this, AlarmReceiver.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
		
		AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
		manager.set(AlarmManager.RTC_WAKEUP, target.getTimeInMillis(), pendingIntent);
	
	}
}
