package ch.schoeb.day2_exercise_05_actionbar;

import android.app.Activity;
import android.os.Bundle;

public class DetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
}
