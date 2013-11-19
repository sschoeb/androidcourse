package ch.androidworkshop.appundertest.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.TextView;
import ch.androidworkshop.appundertest.MainActivity;
import ch.androidworkshop.appundertest.R;

import com.jayway.android.robotium.solo.Solo;

public class MainActivityFixture extends ActivityInstrumentationTestCase2<MainActivity> {
	public MainActivityFixture() {
		super(MainActivity.class);
	}

	private Solo solo;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}

	public void testCalculationWorking() {
		
		// Arrange
		double firstNumber = 5;
		double secondNumber = 20;
		double expectedResult = firstNumber + secondNumber;
		
		EditText firstNumberEditText = (EditText)solo.getView(R.id.editTextNumberOne);
		EditText secondNumberEditText = (EditText)solo.getView(R.id.editTextNumberTwo);
		TextView resultTextView = (TextView)solo.getView(R.id.textViewResult);
		
		// Act
		solo.enterText(firstNumberEditText, firstNumber +"");
		solo.enterText(secondNumberEditText, secondNumber + "");
		solo.clickOnButton("Calculate");

		// Assert
		double calculatedResult = Double.parseDouble(resultTextView.getText().toString());
		assertEquals(expectedResult, calculatedResult);
	}
	
	// TODO: Write a test to check the visibility of the resultTitle
	// TODO: Write a test to check whether the clear button works fine

	@Override
	protected void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}
}
