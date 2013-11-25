package ch.androidworkshop.friendfinder;

import android.annotation.TargetApi;
import android.app.Activity;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import ch.androidworkshop.friendfinder.contentprovider.PostsContract;
import ch.androidworkshop.friendfinder.model.Post;

public class PostDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_detail);

		setupActionBar();

		long postId = getIntent().getExtras().getLong("postid");
		Cursor result = getContentResolver().query(PostsContract.Posts.CONTENT_ID_URI_BASE, new String[] {PostsContract.Posts.COLUMN_NAME_COMMENT, PostsContract.Posts.COLUMN_NAME_NAME,PostsContract.Posts.COLUMN_NAME_LATITUDE,PostsContract.Posts.COLUMN_NAME_LONGITUDE}, PostsContract.Posts._ID + "=?", new String[] { postId + "" }, null);
		result.moveToFirst();

		Post post = new Post();
		post.comment = result.getString(0);
		post.name = result.getString(1);
		post.longitude = result.getDouble(2);
		post.latitude = result.getDouble(3);

		TextView textViewComment = (TextView) findViewById(R.id.textViewComment);
		textViewComment.setText(post.comment);

		TextView textViewName = (TextView) findViewById(R.id.textViewName);
		textViewName.setText(post.name);

		TextView textViewLatitude = (TextView) findViewById(R.id.textViewLatitude);
		textViewLatitude.setText(post.latitude + "");

		TextView textViewLongitude = (TextView) findViewById(R.id.textViewLongitude);
		textViewLongitude.setText(post.longitude + "");
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.post_detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
