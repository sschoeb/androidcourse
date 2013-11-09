package ch.schoeb.day3_exercise_04_locationprovider;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

public class MainActivity extends Activity implements 
GooglePlayServicesClient.ConnectionCallbacks, 
GooglePlayServicesClient.OnConnectionFailedListener {

	private TextView textViewLongitude;
	private TextView textViewLatitude;
	private LocationClient client;

	private LocationListener locationListener = new LocationListener() {

		@Override
		public void onLocationChanged(Location location) {

			if (location != null) {
				textViewLatitude.setText(location.getLatitude() + "");
				textViewLongitude.setText(location.getLongitude() + "");
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textViewLatitude = (TextView) findViewById(R.id.textViewLatitude);
		textViewLongitude = (TextView) findViewById(R.id.textViewLongitude);

		client = new LocationClient(this, this, this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		client.connect();
	}

	@Override
	protected void onPause() {
		super.onPause();
		client.disconnect();
	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		Log.d("LocationClient", "Connection failed" + arg0.toString());
	}

	@Override
	public void onConnected(Bundle arg0) {
		Log.d("LocationClient", "Connected");
		LocationRequest request = LocationRequest.create();
		request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
		request.setInterval(1000);
		client.requestLocationUpdates(request, locationListener);
	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		
	}


	// TODO's:
	// 1. Instantiate LocationClient
	// 2. Connect locationclient
	// 3. Implement interfaces for Connection and Connecatil failed callbacks
	// 4. Create a new LocationRequest
	// 5. Request location updates using the locationclient you instantieted in
	// 1 as soon as you are connected
}
