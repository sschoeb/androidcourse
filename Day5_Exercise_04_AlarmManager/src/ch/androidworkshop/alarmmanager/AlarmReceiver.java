package ch.androidworkshop.alarmmanager;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Notification.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		// TODO: Create notification here
		// - Create a target Intent
		// - Create a PendingIntent which should be started when clicked on the
		// Notification
		// - Build a notification
		// -> Do not forget to set the small icon / content title and content
		// text, otherwise the notification will not show up
		// - get NotificationManager
		
		Intent targetIntent = new Intent(context, NotifiedActivity.class);
		PendingIntent targetPendingIntent = PendingIntent.getActivity(context, 0, targetIntent, 0);
		
		Notification.Builder builder = new Notification.Builder(context);
		builder.setContentTitle("ALARM");
		builder.setContentText("YOU HAVE AN ALARM");
		builder.setSmallIcon(R.drawable.ic_launcher);
		builder.setContentIntent(targetPendingIntent);
		Notification notification = builder.build();
		
		NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		manager.notify(2, notification);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
