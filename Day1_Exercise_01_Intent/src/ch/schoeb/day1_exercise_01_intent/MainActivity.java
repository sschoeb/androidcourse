
package ch.schoeb.day1_exercise_01_intent;

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

		Button btnNavigate = (Button) findViewById(R.id.btnNavigate);
		btnNavigate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View clickedView) {
				navigateToDetailActivity();
			}
		});
	}

	private void navigateToDetailActivity() {
		// TODO 1: Implement navigation here
		// TODO 2: Pass any string to DetailActivity
	}
}
