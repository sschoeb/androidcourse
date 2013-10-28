package ch.schoeb.day3_exercise_07_locationaddressresolution;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textViewFoundAddress;
	private EditText editTextLongitude;
	private EditText editTextLatitude;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textViewFoundAddress = (TextView) findViewById(R.id.foundAddress);
		editTextLatitude = (EditText)findViewById(R.id.latitude);
		editTextLongitude = (EditText)findViewById(R.id.longitude);
		
		Button searchButton = (Button) findViewById(R.id.search);
		searchButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				searchAddress();
			}
		});
		
	}

	private void searchAddress() {
		//TODO's
		// Use the Geocoder-class to get an address for the long/lat given by
		// editTextLatitude and editTextLongitude
		// Write the first found address into the textViewRoundAddress TextView
	}

}
