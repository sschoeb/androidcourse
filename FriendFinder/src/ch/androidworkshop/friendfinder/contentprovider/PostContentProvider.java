package ch.androidworkshop.friendfinder.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class PostContentProvider extends ContentProvider {

	private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	private FriendFinderDatabase friendFinderDatabase;
	private static final int MATCH_POST = 1;
	
	
	static {
		uriMatcher.addURI("ch.schoeb.FriendFinder", "posts", MATCH_POST);
	}

	@Override
	public int delete(Uri uri, String where, String[] whereArgs) {

		if (uriMatcher.match(uri) != MATCH_POST) {
			throw new IllegalArgumentException("Unknown URI " + uri);
		}

		int count = 0;
		SQLiteDatabase db = friendFinderDatabase.getWritableDatabase();
		count = db.delete(PostsContract.Posts.TABLE_NAME, where, whereArgs);

		getContext().getContentResolver().notifyChange(uri, null);

		return count;
	}

	@Override
	public String getType(Uri uri) {
		if (uriMatcher.match(uri) != MATCH_POST) {
			throw new IllegalArgumentException("Unknown URI " + uri);
		}

		return PostsContract.Posts.CONTENT_TYPE;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {

		if (uriMatcher.match(uri) != MATCH_POST) {
			throw new IllegalArgumentException("Unknown URI " + uri);
		}

		SQLiteDatabase db = friendFinderDatabase.getWritableDatabase();
		long rowId = db.insert(PostsContract.Posts.TABLE_NAME, null, values);
		if (rowId < 0) {
			throw new SQLException("Could not insert data into SQLite-Database");
		}

		// Inser 
		Uri postUri = ContentUris.withAppendedId(PostsContract.Posts.CONTENT_ID_URI_BASE, rowId);

		getContext().getContentResolver().notifyChange(postUri, null);
		return postUri;
	}

	@Override
	public boolean onCreate() {
		friendFinderDatabase = new FriendFinderDatabase(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

		Cursor cursor = null;
		switch (uriMatcher.match(uri)) {
		case MATCH_POST:
			cursor = friendFinderDatabase.getReadableDatabase().query(PostsContract.Posts.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}

		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
		if (uriMatcher.match(uri) != MATCH_POST) {
			throw new IllegalArgumentException("Unknown URI " + uri);
		}

		SQLiteDatabase db = friendFinderDatabase.getWritableDatabase();
		int count = db.update(PostsContract.Posts.TABLE_NAME, values, where, whereArgs);

		getContext().getContentResolver().notifyChange(uri, null);

		return count;
	}

}
