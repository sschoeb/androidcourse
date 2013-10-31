package ch.schoeb.day4_exercise_sqlitedatabase;

import android.provider.BaseColumns;

public class NoteContract implements BaseColumns {

	public static final String TABLE_NAME = "notes";
	public static final String COLUMN_NAME = "name";
	
	public static final String CREATE_SQL = "create table " + TABLE_NAME + "(" + _ID + " integer primary key, " + COLUMN_NAME + " text not null);";
	public static final String DROP_SQL = "DROP TABLE " + TABLE_NAME;
	
}
