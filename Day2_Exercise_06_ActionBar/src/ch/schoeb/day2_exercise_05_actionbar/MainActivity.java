package ch.schoeb.day2_exercise_05_actionbar;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MainActivity extends Activity implements ActionBar.OnNavigationListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, new String[] { "FirstView" });
		actionBar.setListNavigationCallbacks(adapter, this);
	}

	@Override
	public boolean onNavigationItemSelected(int position, long id) {

		Fragment newFragment;
		if (position == 0) {
			newFragment = new FirstViewFragment();
		} else {
			return false;
		}

		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(android.R.id.content, newFragment);
		ft.commit();

		return true;
	}

}
