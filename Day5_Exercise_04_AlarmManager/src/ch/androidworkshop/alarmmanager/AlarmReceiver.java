package ch.androidworkshop.alarmmanager;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		Intent notificationIntent = new Intent(context, NotifiedActivity.class);
		PendingIntent targetIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
		
		Notification.Builder builder = new Notification.Builder(context);
		builder.setContentTitle("ALARM");
		builder.setContentText("A new alarm arrived...");
		builder.setSmallIcon(R.drawable.ic_launcher);
		builder.addAction(R.drawable.ic_launcher, "Open", targetIntent);
		Notification notification = builder.build();
		
		NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		manager.notify(1, notification);
		
		Log.d("AlarmReceiver", "Got alarm received");
	}
}
