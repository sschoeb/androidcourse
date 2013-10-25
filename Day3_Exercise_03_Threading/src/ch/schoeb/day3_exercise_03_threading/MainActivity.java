package ch.schoeb.day3_exercise_03_threading;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button buttonRunOnUiThread = (Button) findViewById(R.id.buttonRunOnUiThread);
		buttonRunOnUiThread.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						changeUsingRunOnUiThread();
					}
				}).start();
			}
		});
	}

	private void changeUsingRunOnUiThread() {
		TextView textView = (TextView) findViewById(R.id.textViewRunOnUiThread);
		textView.setText("This does work");
	}
}
