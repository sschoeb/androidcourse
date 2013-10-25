package ch.schoeb.day3_demo_anr;

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

		Button button = (Button) findViewById(R.id.doWorkButton);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				doLongRunningWork();
			}
		});
	}

	private void doLongRunningWork() {
		try {
			Thread.sleep(200000);
		} catch (InterruptedException e) {
		}
	}
}
