package ch.schoeb.day2_exercise_05_actionbar;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

public class DetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		if(item.getItemId() == android.R.id.home)
		{
			finish();
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
}
