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
		_activity = getActivity();
	}

	@UiThreadTest
	public void testCalculationWorking() {
		
		// Arrange
		double firstNumber = 5;
		double secondNumber = 20;
		double expectedResult = firstNumber + secondNumber;
		EditText editTextFirsNumber = (EditText) _activity.findViewById(R.id.editTextNumberOne);
		EditText editTextSecondNumber = (EditText) _activity.findViewById(R.id.editTextNumberTwo);
		Button buttonCalculate = (Button) _activity.findViewById(R.id.submitButton);
		TextView resultTextView = (TextView) _activity.findViewById(R.id.textViewResult);

		// Act
		editTextFirsNumber.setText(firstNumber + "");
		editTextSecondNumber.setText(secondNumber + "");
		buttonCalculate.performClick();

		// Assert
		double calculatedResult = Double.parseDouble(resultTextView.getText().toString());
		assertEquals(expectedResult, calculatedResult);
	}

}
