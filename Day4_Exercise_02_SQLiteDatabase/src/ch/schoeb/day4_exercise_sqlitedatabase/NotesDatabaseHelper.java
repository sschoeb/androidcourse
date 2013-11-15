package ch.schoeb.day4_exercise_sqlitedatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NotesDatabaseHelper extends SQLiteOpenHelper {

	private static final int DATABSE_VERSION = 3;
	private static final String DATABASE_NAME = "customdatabase";

	public NotesDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABSE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Execute create scripts
		db.execSQL(NoteContract.CREATE_SQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Execute upgrade scripts
		db.execSQL(NoteContract.DROP_SQL);
		onCreate(db);
	}
}
