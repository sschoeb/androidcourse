package ch.androidworkshop.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

public class FirstAppWidgetProvider extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		for (int i = 0; i < appWidgetIds.length; i++) {
			int appWidgetId = appWidgetIds[i];
			appWidgetManager.updateAppWidget(appWidgetId, getRemoteViews(context, 1));
		}
	}

	@Override
	public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
		Bundle options = appWidgetManager.getAppWidgetOptions(appWidgetId);

		int minWidth = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH);
		int columns = getCellsForSize(minWidth);
		appWidgetManager.updateAppWidget(appWidgetId, getRemoteViews(context, columns));

		super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);

	}

	private RemoteViews getRemoteViews(Context context, int columns) {
		switch (columns) {
		case 1:
			return Create1x1WidgetLayout(context);
		case 2:
			return Create2x1WidgetLayout(context);
		case 3:
			return Create3x1WidgetLayout(context);
		}

		return new RemoteViews(context.getPackageName(), R.layout.widget_default);

	}

	private RemoteViews Create3x1WidgetLayout(Context context) {
		return new RemoteViews(context.getPackageName(), R.layout.widget_3x1);
	}

	private RemoteViews Create2x1WidgetLayout(Context context) {
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_2x1);
		
		Intent firstIntent = new Intent(context, FirstActivity.class);
		PendingIntent openFirstPendingIntent = PendingIntent.getActivity(context, 0, firstIntent, 0);
		views.setOnClickPendingIntent(R.id.buttonOpenFirst, openFirstPendingIntent);
		
		Intent secondIntent = new Intent(context, SecondActivity.class);
		PendingIntent openSecondPendingIntent = PendingIntent.getActivity(context, 0, secondIntent, 0);
		views.setOnClickPendingIntent(R.id.buttonOpenSecond, openSecondPendingIntent);
		return views;
	}

	private RemoteViews Create1x1WidgetLayout(Context context) {
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_1x1);
		views.setTextViewText(R.id.currentCounterValueTextView, PreferenceHelper.getCurrentCounter(context) + "");
		return views;
	}

	private static int getCellsForSize(int size) {
		int n = 2;
		while (70 * n - 30 < size) {
			n++;
		}
		return n - 1;
	}

}
