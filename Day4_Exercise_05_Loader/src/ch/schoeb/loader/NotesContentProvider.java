package ch.schoeb.loader;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class NotesContentProvider extends ContentProvider {

	private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	private NotesDatabaseHelper databaseHelper;

	private static final int MATCH_NOTES = 1;

	static {
		uriMatcher.addURI(Contracts.AUTHORITY, "notes", MATCH_NOTES);
	}

	@Override
	public int delete(Uri uri, String arg1, String[] arg2) {
		// Ignore delete case
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		return Contracts.Notes.CONTENT_TYPE;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {

		if (uriMatcher.match(uri) == MATCH_NOTES) {
			SQLiteDatabase database = databaseHelper.getWritableDatabase();
			long rowId = database.insert(Contracts.Notes.TABLE_NAME, null, values);
			
			Uri notesUri = ContentUris.withAppendedId(Contracts.Notes.CONTENT_ID_URI_BASE, rowId);
			getContext().getContentResolver().notifyChange(notesUri, null);
		}

		return null;
	}

	@Override
	public boolean onCreate() {
		databaseHelper = new NotesDatabaseHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		if (uriMatcher.match(uri) == MATCH_NOTES) {
			SQLiteDatabase database = databaseHelper.getReadableDatabase();
			Cursor cursor = database.query(Contracts.Notes.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
			
			cursor.setNotificationUri(getContext().getContentResolver(), uri);
			return cursor;
		}

		return null;
	}

	@Override
	public int update(Uri uri, ContentValues values, String arg2, String[] arg3) {
		// Ignore update
		return 0;
	}
}
