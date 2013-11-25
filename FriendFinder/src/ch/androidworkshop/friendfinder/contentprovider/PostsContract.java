package ch.androidworkshop.friendfinder.contentprovider;

import android.net.Uri;
import android.provider.BaseColumns;

public class PostsContract {

	public static final String AUTHORITY = "ch.schoeb.FriendFinder";

	private PostsContract() {
	}

	public static final class Posts implements BaseColumns {
        private Posts() {}

        public static final String TABLE_NAME = "posts";

        private static final String SCHEME = "content://";

        private static final String PATH_POSTS = "/posts";

        private static final String PATH_POSTS_ID = "/posts/";

        public static final int POSTS_ID_PATH_POSITION = 1;

        public static final Uri CONTENT_URI =  Uri.parse(SCHEME + AUTHORITY + PATH_POSTS);

        /**
         * The content URI base for a single post. Callers must
         * append a numeric post id to this Uri to retrieve a post
         */
        public static final Uri CONTENT_ID_URI_BASE = Uri.parse(SCHEME + AUTHORITY + PATH_POSTS_ID);

        /**
         * The content URI match pattern for a single post, specified by its ID. Use this to match
         * incoming URIs or to construct an Intent.
         */
        public static final Uri CONTENT_ID_URI_PATTERN = Uri.parse(SCHEME + AUTHORITY + PATH_POSTS_ID + "/#");

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.ch.schoeb.friendfinder.posts";

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.ch.schoeb.friendfinder.post";

        public static final String COLUMN_NAME_COMMENT = "comment";

        public static final String COLUMN_NAME_LONGITUDE = "longitude";

        public static final String COLUMN_NAME_LATITUDE = "latitude";
        
        public static final String COLUMN_NAME_NAME = "name";
	}
}
