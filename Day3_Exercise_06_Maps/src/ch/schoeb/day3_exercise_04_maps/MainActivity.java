package ch.schoeb.day3_exercise_04_maps;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity {

	private EditText editTextLongitude;
	private EditText editTextLatitude;
	private GoogleMap map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		editTextLongitude = (EditText) findViewById(R.id.editTextLongitude);
		editTextLatitude = (EditText) findViewById(R.id.editTextLatitude);
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

		Button goButton = (Button) findViewById(R.id.buttonGo);
		goButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showCurrentPositionOnMap();
			}
		});

		Button zoomInButton = (Button) findViewById(R.id.buttonZoomIn);
		zoomInButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				zoomIn();
			}
		});
		Button zoomOutButton = (Button) findViewById(R.id.buttonZoomOut);
		zoomOutButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				zoomOut();
			}
		});
	}

	private void showCurrentPositionOnMap() {
		map.clear();

		LatLng position = GetLatLngFromEditTexts();
		MarkerOptions markerOptions = new MarkerOptions().position(position).title("Your location");
		map.addMarker(markerOptions);
	}

	private LatLng GetLatLngFromEditTexts() {
		double longitude = Double.parseDouble(editTextLongitude.getText().toString());
		double latitude = Double.parseDouble(editTextLatitude.getText().toString());

		return new LatLng(latitude, longitude);
	}

	private void zoomIn() {
		map.animateCamera(CameraUpdateFactory.zoomIn());
	}

	private void zoomOut() {
		map.animateCamera(CameraUpdateFactory.zoomOut());
	}
}
