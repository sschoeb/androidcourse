package ch.schoeb.day1_exercise_01_intent;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		TextView receivedDataTextView = (TextView)findViewById(R.id.receivedDataTextView);
		
		// TODO 3: Write the received data to the given receivedDataTextView
	}
}
