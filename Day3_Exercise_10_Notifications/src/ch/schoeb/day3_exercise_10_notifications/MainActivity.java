package ch.schoeb.day3_exercise_10_notifications;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button buttonAddOne = (Button) findViewById(R.id.addOneButton);
		buttonAddOne.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				addOneToNotification();
			}
		});
	}

	private void addOneToNotification() {

	}
}
