package ch.schoeb.friendfinder.model;

import java.util.ArrayList;
import java.util.List;

public class PostRepository {

	private static List<Post> posts;

	static {
		posts = new ArrayList<Post>();
		posts.add(new Post(0, "Stan Wawrinka", "Go for my first GrandSlam!", 37.2, 89.9));
		posts.add(new Post(1, "Hans Muster", "My first comment", 47.2, 15.9));
		posts.add(new Post(2, "Michael Schumacher", "Over and out!", 47.7, 75.8));
		posts.add(new Post(3, "Roger Federer", "London calling", 43.4, 36.2));
		posts.add(new Post(4, "Didier Cuche", "Yea this rocks", 46.1, 45.2));
	}

	public static List<Post> getAllPosts() {
		return posts;
	}

	public static Post getPostById(int id) {
		for (Post post : posts) {
			if (post.id == id) {
				return post;
			}
		}

		return null;
	}

}
