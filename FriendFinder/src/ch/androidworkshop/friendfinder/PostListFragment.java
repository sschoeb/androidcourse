package ch.androidworkshop.friendfinder;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import ch.androidworkshop.friendfinder.contentprovider.PostsContract;

public class PostListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

	private SimpleCursorAdapter simpleCursorAdapter;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		setEmptyText("No posts available");

		simpleCursorAdapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_list_item_2, null, new String[] { PostsContract.Posts.COLUMN_NAME_COMMENT, PostsContract.Posts.COLUMN_NAME_NAME }, new int[] { android.R.id.text1, android.R.id.text2 }, 0);
		setListAdapter(simpleCursorAdapter);
		
		getListView().setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(getActivity(), PostDetailActivity.class);
				intent.putExtra("postid", id);
				startActivity(intent);
			}
		});

		getLoaderManager().initLoader(0, null, this);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		String[] projection = new String[] { PostsContract.Posts._ID, PostsContract.Posts.COLUMN_NAME_COMMENT, PostsContract.Posts.COLUMN_NAME_LATITUDE, PostsContract.Posts.COLUMN_NAME_LONGITUDE, PostsContract.Posts.COLUMN_NAME_NAME };
		CursorLoader cursorLoader = new CursorLoader(getActivity(), PostsContract.Posts.CONTENT_URI, projection, null, null, null);
		return cursorLoader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		simpleCursorAdapter.swapCursor(cursor);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		simpleCursorAdapter.swapCursor(null);
	}
}
