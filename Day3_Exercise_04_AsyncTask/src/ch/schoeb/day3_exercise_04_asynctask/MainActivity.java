package ch.schoeb.day3_exercise_04_asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ProgressBar progressBar;
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		textView = (TextView) findViewById(R.id.textViewState);

		Button startButton = (Button) findViewById(R.id.startButton);
		startButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO: Execute your AsyncTask here
				new Worker().execute();
			}
		});
	}

	// TODO: Programm your AsyncTask here
	//

	public class Worker extends AsyncTask<Void, Integer, Void> {
		protected Void doInBackground(Void... arg0) {

			for (int i = 1; i <= 10; i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				super.publishProgress(i * 10);
			}

			return null;
		}

		protected void onPreExecute() {
			textView.setText("Running");
		}

		protected void onPostExecute(Void result) {
			textView.setText("Finished");
		}

		protected void onProgressUpdate(Integer... values) {
			progressBar.setProgress(values[0]);
		}
	}
}
