package ch.schoeb.day2_exercise1;

import android.app.ListActivity;
import android.os.Bundle;

public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getListView().setAdapter(new PostListAdapter(this, PostGenerator.generatePosts(5000)));
	}

}
