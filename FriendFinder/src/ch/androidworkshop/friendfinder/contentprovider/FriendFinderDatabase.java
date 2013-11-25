package ch.androidworkshop.friendfinder.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FriendFinderDatabase extends SQLiteOpenHelper {

	private static final String DATABASE_CREATE = "create table " + PostsContract.Posts.TABLE_NAME + "(" + PostsContract.Posts._ID + " integer primary key, " + PostsContract.Posts.COLUMN_NAME_COMMENT + " text not null, " + PostsContract.Posts.COLUMN_NAME_LATITUDE + " text not null, " + PostsContract.Posts.COLUMN_NAME_LONGITUDE + " text not null, " + PostsContract.Posts.COLUMN_NAME_NAME + " text not null);";
	private static final String DATABASE_DROP = "drop table " + PostsContract.Posts.TABLE_NAME;
	private static String DATABASE_NAME = "FriendFinderDatabase";
	private static int VERSION = 8;

	public FriendFinderDatabase(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		database.execSQL(DATABASE_DROP);
		onCreate(database);
	}
}
