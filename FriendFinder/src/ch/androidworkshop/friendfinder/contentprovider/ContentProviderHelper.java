package ch.androidworkshop.friendfinder.contentprovider;

import android.content.ContentResolver;
import android.database.Cursor;

public class ContentProviderHelper {

	public static int getMaximumPostId(ContentResolver contentResolver)
	{
		Cursor cursor = contentResolver.query(
		         PostsContract.Posts.CONTENT_URI,
		          new String[] {"MAX("+  PostsContract.Posts._ID +") AS maxid"}, null,
		         null, null);
		
		cursor.moveToFirst();
		
		return cursor.getInt(0);
	}
	
}
