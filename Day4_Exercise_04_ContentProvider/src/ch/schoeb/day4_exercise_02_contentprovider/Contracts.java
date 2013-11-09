package ch.schoeb.day4_exercise_02_contentprovider;

import android.net.Uri;
import android.provider.BaseColumns;

public class Contracts {

	public static final String AUTHORITY = "ch.schoeb.ContentProvider";

	public static class Notes implements BaseColumns {

		public static final String TABLE_NAME = "notes";
		public static final String COLUMN_NAME = "name";

		public static final String CREATE_SQL = "create table " + TABLE_NAME + "(" + _ID + " integer primary key, " + COLUMN_NAME + " text not null);";
		public static final String DROP_SQL = "DROP TABLE " + TABLE_NAME;

		private static final String SCHEME = "content://";

		private static final String PATH_NOTES = "/notes";
		private static final String PATH_NOTES_ID = "/notes/";

		public static final Uri CONTENT_ID_URI_BASE = Uri.parse(SCHEME + AUTHORITY + PATH_NOTES);
		public static final Uri CONTENT_ID_URI_PATTERN = Uri.parse(SCHEME + AUTHORITY + PATH_NOTES_ID + "/#");
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.ch.schoeb.contentprovider.notes";
	}
}