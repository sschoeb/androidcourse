package ch.schoeb.day3_exercise_10_notifications;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button buttonAddOne = (Button) findViewById(R.id.addOneButton);
		buttonAddOne.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				addOneToNotification();
			}
		});

		Button cancelButton = (Button) findViewById(R.id.cancelButton);
		cancelButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				cancelNotifications();
			}
		});
	}

	private void addOneToNotification() {

		Intent intent = new Intent(this, DetailActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

		Notification n = new Notification.Builder(this).setContentTitle("Demo Notification").setContentText("Subject").setSmallIcon(R.drawable.ic_launcher).setContentIntent(pendingIntent).setAutoCancel(true)
				.addAction(R.drawable.ic_launcher, "Detail", pendingIntent).addAction(R.drawable.ic_launcher, "Extras", pendingIntent).build();

		NotificationManager man = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		man.notify(0, n);
	}

	private void cancelNotifications() {
		NotificationManager man = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		man.cancel(0);
	}
	
}
