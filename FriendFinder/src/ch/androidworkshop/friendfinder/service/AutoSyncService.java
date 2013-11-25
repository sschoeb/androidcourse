package ch.androidworkshop.friendfinder.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class AutoSyncService extends Service {

	private final IBinder binder = new AutoSyncServiceBinder();

	public class AutoSyncServiceBinder extends Binder {
		public AutoSyncService getService() {
			return AutoSyncService.this;
		}
	}

	private Runner autoSyncThread;

	@Override
	public IBinder onBind(Intent arg0) {
		autoSyncThread = new Runner();
		autoSyncThread.start();

		return binder;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		autoSyncThread.terminate();
		return super.onUnbind(intent);
	}

	class Runner extends Thread implements Runnable {
		private boolean isRunning = true;

		public void terminate() {
			isRunning = false;
		}

		@Override
		public void run() {
			while(isRunning)
			{
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
				}
				
				startService(new Intent(AutoSyncService.this, CommunicationService.class));
			}
		}
	}
}
