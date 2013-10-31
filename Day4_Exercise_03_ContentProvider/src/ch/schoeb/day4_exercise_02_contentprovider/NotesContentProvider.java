package ch.schoeb.day4_exercise_02_contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class NotesContentProvider extends ContentProvider {

	private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	private NotesDatabaseHelper databaseHelper;

	static {
		// TODO initialize URI's which should be matched by this ContentProvider
	}

	@Override
	public int delete(Uri uri, String arg1, String[] arg2) {
		// Ignore delete case
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO: Make sure URI matches correctly
		return Contracts.Notes.CONTENT_TYPE;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO implement insert
		// Make sure URI matches correctly
		// Use the databseHelper to access the database
		return null;
	}

	@Override
	public boolean onCreate() {
		databaseHelper = new NotesDatabaseHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		// TODO implement query
		// Make sure URI matches correctly
		// Use the databaseHelper to access the database
		return null;
	}

	@Override
	public int update(Uri uri, ContentValues values, String arg2, String[] arg3) {
		// Ignore update
		return 0;
	}
}
