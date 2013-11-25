package ch.androidworkshop.appundertest.test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import ch.androidworkshop.appundertest.MainActivity;
import ch.androidworkshop.appundertest.R;

public class MainActivityFixture extends ActivityInstrumentationTestCase2<MainActivity> {

	public MainActivityFixture() {
		super(MainActivity.class);
	}

	private Activity _activity;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		setActivityInitialTouchMode(false);
		
		// TODO: Create your activity here
		_activity = getActivity();
	}

	@UiThreadTest
	public void testCalculationWorking() {
		
		// Arrange
		// TODO: Get all the views you need -> same methods as when accessing views in normal activity
		EditText editTextNumberOne = (EditText)_activity.findViewById(R.id.editTextNumberOne);
		EditText editTextNumberTwo = (EditText)_activity.findViewById(R.id.editTextNumberTwo);
		Button buttonSubmit = (Button)_activity.findViewById(R.id.submitButton);
		TextView resultTextView = (TextView)_activity.findViewById(R.id.textViewResult);
		
		// Act
		// TODO: Enter text and click the button
		editTextNumberOne.setText("1");
		editTextNumberTwo.setText("2");
		buttonSubmit.performClick();
		
		// Assert
		// TODO: Make sure the result is correct using assertXXX()-methods
		int result = (int)Double.parseDouble(resultTextView.getText().toString());
		assertEquals(3, result);
	}

}
