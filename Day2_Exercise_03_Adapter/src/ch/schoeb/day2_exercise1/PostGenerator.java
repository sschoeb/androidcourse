package ch.schoeb.day2_exercise1;

import java.util.ArrayList;
import java.util.List;

public class PostGenerator {

	public static List<Post> generatePosts(int count) {
		List<Post> posts = new ArrayList<Post>();

		for (int i = 0; i < count; i++) {
			Post post = createPost(i);
			posts.add(post);
		}

		return posts;
	}

	private static Post createPost(int id) {
		return new Post(id, "Name " + id, "Comment " + id);
	}

}
