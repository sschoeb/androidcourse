package ch.androidworkshop.friendfinder.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

public class LocationService extends Service implements ConnectionCallbacks, OnConnectionFailedListener {

	private final IBinder binder = new LocationServiceBinder();

	public class LocationServiceBinder extends Binder {
		public LocationService getService() {
			return LocationService.this;
		}
	}

	private LocationClient locationClient;

	@Override
	public IBinder onBind(Intent arg0) {
		Log.d("MyMessage", "onBind");
		locationClient = new LocationClient(this, this, this);
		locationClient.connect();
		return binder;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		locationClient.disconnect();
		return super.onUnbind(intent);
	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		Log.d("MyMessage", "Connection FAILED");
	}

	@Override
	public void onConnected(Bundle arg0) {
		if (locationListener != null) {
			Log.d("MyMessage", "Connected and GO");
			RequestLocationUpdates();
		}
		Log.d("MyMessage", "Connected and ????");
	}

	@Override
	public void onDisconnected() {
	}

	private LocationListener locationListener;

	public void registerLocationListener(LocationListener locationListener) {

		this.locationListener = locationListener;
		if (locationClient.isConnected()) {
			RequestLocationUpdates();
		}
	}

	private void RequestLocationUpdates() {
		LocationRequest locationrequest = LocationRequest.create();
		locationrequest.setInterval(100);
		locationrequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

		locationListener.onLocationChanged(locationClient.getLastLocation());
		locationClient.requestLocationUpdates(locationrequest, locationListener);
	}

	public void unregisterLocationListener(LocationListener locationListener) {
		locationClient.removeLocationUpdates(locationListener);
	}

}
