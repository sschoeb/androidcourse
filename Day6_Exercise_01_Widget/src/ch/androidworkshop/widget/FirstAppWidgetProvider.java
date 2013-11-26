package ch.androidworkshop.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.os.Bundle;
import android.widget.RemoteViews;

public class FirstAppWidgetProvider extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		
		for (int appWidgetId : appWidgetIds) {
			updateWidgetLayout(context, appWidgetManager, appWidgetId);
		}
	}

	@Override
	public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
		super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
		updateWidgetLayout(context, appWidgetManager, appWidgetId);
	}

	private void updateWidgetLayout(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
		Bundle options = appWidgetManager.getAppWidgetOptions(appWidgetId);
		int minWidth = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH);
		appWidgetManager.updateAppWidget(appWidgetId, getRemoteViews(context, minWidth));
	}

	private RemoteViews getRemoteViews(Context context, int minWidth) {
		int columns = getCellsForSize(minWidth);
		switch (columns) {
		case 1:
			return Create1x1WidgetLayout(context);
		case 2:
		case 3:
			return Create2x1WidgetLayout(context);
		case 4:
			return Create4x1WidgetLayout(context);
		}

		return new RemoteViews(context.getPackageName(), R.layout.widget_default);
	}

	private RemoteViews Create4x1WidgetLayout(Context context) {
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_4x1);
		wireUpFirstButton(context, views);
		wireUpSecondButton(context, views);
		return views;
	}

	private RemoteViews Create2x1WidgetLayout(Context context) {
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_2x1);
		wireUpFirstButton(context, views);
		wireUpSecondButton(context, views);
		return views;
	}

	private RemoteViews Create1x1WidgetLayout(Context context) {
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_1x1);
		wireUpFirstButton(context, views);
		return views;
	}

	private void wireUpFirstButton(Context context, RemoteViews views) {
		// TODO: Make sure Button nr. 1 does open FirstActivity
	}

	private void wireUpSecondButton(Context context, RemoteViews views) {
		// TODO: Make sure Button nr. 2 does open SecondActivity
	}

	private static int getCellsForSize(int size) {
		int n = 2;
		while (80 * n - 30 < size) {
			n++;
		}
		return n - 1;
	}
}
