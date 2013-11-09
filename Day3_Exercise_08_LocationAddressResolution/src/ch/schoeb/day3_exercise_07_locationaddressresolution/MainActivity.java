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
		editTextLatitude = (EditText) findViewById(R.id.latitude);
		editTextLongitude = (EditText) findViewById(R.id.longitude);

		Button searchButton = (Button) findViewById(R.id.search);
		searchButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				searchAddress();
			}
		});

	}

	private void searchAddress() {

		Geocoder coder = new Geocoder(this);
		double longitude = Double.parseDouble(editTextLongitude.getText().toString());
		double latitude = Double.parseDouble(editTextLatitude.getText().toString());

		List<Address> addresses = null;
		try {
			addresses = coder.getFromLocation(latitude, longitude, 1);
		} catch (IOException e) {
		}

		Address firstOne = addresses.get(0);
		textViewFoundAddress.setText(getAddressAsString(firstOne));

	}

	private String getAddressAsString(Address address) {
		String data = address.getAddressLine(0);
		data += "\r\n" + address.getAddressLine(1);
		data += "\r\n" + address.getAddressLine(2);
		return data;
	}

}
