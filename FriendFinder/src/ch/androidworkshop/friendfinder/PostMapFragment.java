package ch.androidworkshop.friendfinder;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import ch.androidworkshop.friendfinder.contentprovider.PostsContract;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class PostMapFragment extends SupportMapFragment implements LoaderManager.LoaderCallbacks<Cursor> {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getLoaderManager().initLoader(0, null, this);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		String[] projection = new String[] { PostsContract.Posts.COLUMN_NAME_COMMENT, PostsContract.Posts.COLUMN_NAME_LATITUDE, PostsContract.Posts.COLUMN_NAME_LONGITUDE, PostsContract.Posts.COLUMN_NAME_NAME };
		CursorLoader cursorLoader = new CursorLoader(getActivity(), PostsContract.Posts.CONTENT_URI, projection, null, null, null);
		return cursorLoader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor curser) {
		clearMarkers();
		updateMarkers(curser);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> curser) {
		clearMarkers();
	}

	private void clearMarkers() {
		GoogleMap map = getMap();
		if (map != null) {
			map.clear();
		}
	}

	private void updateMarkers(Cursor cursor) {
		GoogleMap map = getMap();
		if (cursor.moveToFirst()) {

			LatLngBounds.Builder builder = new LatLngBounds.Builder();
			while (!cursor.isAfterLast()) {

				double latitude = cursor.getDouble(1);
				double longitude = cursor.getDouble(2);

				LatLng position= new LatLng(latitude, longitude);
				MarkerOptions markerOptions = new MarkerOptions().position(position).title("Your location");
				map.addMarker(markerOptions);
				builder.include(position);
				cursor.moveToNext();
			}
			
			LatLngBounds bounds = builder.build();
			int padding = 50; // offset from edges of the map in pixels
			CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, getResources().getDisplayMetrics().widthPixels,  getResources().getDisplayMetrics().heightPixels, padding);
			map.moveCamera(cu);
		}
	}
}
