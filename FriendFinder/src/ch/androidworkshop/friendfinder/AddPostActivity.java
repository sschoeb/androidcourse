package ch.androidworkshop.friendfinder;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import ch.androidworkshop.friendfinder.contentprovider.PostsContract;
import ch.androidworkshop.friendfinder.model.Post;
import ch.androidworkshop.friendfinder.service.CommunicationService;
import ch.androidworkshop.friendfinder.service.LocationService;
import ch.androidworkshop.friendfinder.service.CommunicationService.CommunicationServiceBinder;
import ch.androidworkshop.friendfinder.service.CommunicationService.SendCallbackListener;
import ch.androidworkshop.friendfinder.service.LocationService.LocationServiceBinder;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class AddPostActivity extends FragmentActivity {

	private CommunicationService communicationService;
	private LocationService locationService;
	private Location currentLocation;

	private SupportMapFragment supportMapFragment;
	private EditText commentEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_post);

		setupActionBar();
		
		supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentByTag("addmapfragment");
		commentEditText = (EditText) findViewById(R.id.commentEditText);
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		MenuItem item = menu.findItem(R.id.action_send);
		item.setEnabled(currentLocation != null);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_post, menu);
		return true;
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		case R.id.action_send:
			handleSendCommand();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	private void handleSendCommand() {

		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		
		Post post = new Post();
		post.latitude = currentLocation.getLatitude();
		post.longitude = currentLocation.getLongitude();
		post.comment = commentEditText.getText().toString();
		post.name = prefs.getString("general_username", "Anonymous");

		communicationService.send(post, new SendCallbackListener() {

			@Override
			public void onPostSuccessfullySent(Post post, int newId) {
				Toast.makeText(AddPostActivity.this, "Message posted", Toast.LENGTH_SHORT).show();

				ContentValues values = new ContentValues();
				values.put(PostsContract.Posts._ID, newId);
				values.put(PostsContract.Posts.COLUMN_NAME_COMMENT, post.comment);
				values.put(PostsContract.Posts.COLUMN_NAME_LATITUDE, post.latitude);
				values.put(PostsContract.Posts.COLUMN_NAME_LONGITUDE, post.longitude);
				values.put(PostsContract.Posts.COLUMN_NAME_NAME, post.name);
				getContentResolver().insert(PostsContract.Posts.CONTENT_URI, values);

				finish();
			}

			@Override
			public void onPostSendFailed() {
				Toast.makeText(AddPostActivity.this, "Sending message failed", Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		bindService(new Intent(this, LocationService.class), locationServiceConnection, Context.BIND_AUTO_CREATE);
		bindService(new Intent(this, CommunicationService.class), communicationServiceConnection, Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onPause() {
		super.onPause();

		unbindService(locationServiceConnection);
		unbindService(communicationServiceConnection);
	}

	private ServiceConnection locationServiceConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName name, IBinder serviceBinder) {
			locationService = ((LocationServiceBinder) serviceBinder).getService();
			locationService.registerLocationListener(locationListener);
			Log.d("MyMessage", "Connected");
		}

		public void onServiceDisconnected(ComponentName name) {
			locationService.unregisterLocationListener(locationListener);
			Log.d("MyMessage", "Disconnected");
		}
	};

	private ServiceConnection communicationServiceConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName name, IBinder serviceBinder) {
			communicationService = ((CommunicationServiceBinder) serviceBinder).getService();
		}

		public void onServiceDisconnected(ComponentName name) {
		}
	};

	private LocationListener locationListener = new LocationListener() {
		@Override
		public void onLocationChanged(Location location) {
			setCurrentLocation(location);
		}
	};

	private void setCurrentLocation(Location location) {
		currentLocation = location;
		if (location != null) {
			
			GoogleMap map = supportMapFragment.getMap();
			map.clear();
			MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).title("Your location");
			map.addMarker(markerOptions);
			
			map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 12.0f));
		}

		invalidateOptionsMenu();
	}
}
