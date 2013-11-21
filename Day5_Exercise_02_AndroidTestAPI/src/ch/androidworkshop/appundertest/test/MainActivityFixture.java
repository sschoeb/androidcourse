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
	}

	@UiThreadTest
	public void testCalculationWorking() {
		
		// Arrange
		// TODO: Get all the views you need -> same methods as when accessing views in normal activity

		// Act
		// TODO: Enter text and click the button

		// Assert
		// TODO: Make sure the result is correct using assertXXX()-methods
	}

}
