package ch.schoeb.day3_exercise_04_asynctask;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ProgressBar progressBar;
	private TextView textViewState;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		textViewState = (TextView) findViewById(R.id.textViewState);

		Button startButton = (Button) findViewById(R.id.startButton);
		startButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO: Execute your AsyncTask here
			}
		});
		
	}

	// TODO: Programm your AsyncTask here
	// use "progressBar" to update the current progress
	// use "textViewState" to show the current state (started/running/finished)
}
