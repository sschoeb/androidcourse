package ch.schoeb.day2_exercise3_menudemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * TODO: Implement onOptionsItemSelected
	 * 
	 * 1. Get the checked item by item.getItemId() 
	 * 2. Make sure the selected item is yours
	 * 3. Call the "startDetailActivity"-method
	 * 
	 */

	
	

	private void startDetailActivity() {
		Intent intent = new Intent(this, DetailActivity.class);
		startActivity(intent);
	}

}
