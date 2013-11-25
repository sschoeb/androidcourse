package ch.androidworkshop.appundertest.test;

import android.app.Activity;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.view.View;
import android.widget.EditText;
import ch.androidworkshop.appundertest.MainActivity;
import ch.androidworkshop.appundertest.R;

public class MainActivityUnitTestFixture extends ActivityUnitTestCase<MainActivity> {

	public MainActivityUnitTestFixture() {
		super(MainActivity.class);
	}

	private Activity _activity;

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		// Start activity -> onCreate is called here
		startActivity(new Intent(getInstrumentation().getTargetContext(), MainActivity.class), null, null);

		// Get the activity we want to test
		_activity = getActivity();
	}

	public void testNumberOneEditTextVisible() {
		// TODO: Check if the EditText for the first number is visible
		// - access views on _activity as you do in the onCreate of a normal
		// activity
		
		EditText editText = (EditText)_activity.findViewById(R.id.editTextNumberOne);
		assertEquals(View.VISIBLE, editText.getVisibility());
	}

}
