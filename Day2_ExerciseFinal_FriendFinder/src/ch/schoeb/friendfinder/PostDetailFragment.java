package ch.schoeb.friendfinder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import ch.schoeb.friendfinder.model.Post;
import ch.schoeb.friendfinder.model.PostRepository;

import com.example.day2_exercisefinal_friendfinder.R;

public class PostDetailFragment extends Fragment {

	public static final String INDEX_KEY = "index";

	public static PostDetailFragment newInstance(int index) {
		PostDetailFragment f = new PostDetailFragment();

		Bundle args = new Bundle();
		args.putInt(INDEX_KEY, index);
		f.setArguments(args);

		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Post post = PostRepository.getPostById(getShownIndex());

		View view = inflater.inflate(R.layout.postdetailfragment, container, false);
		if (post == null) {
			return view;
		}

		TextView username = (TextView) view.findViewById(R.id.userNameTextView);
		username.setText(post.name);

		TextView comment = (TextView) view.findViewById(R.id.commentTextView);
		comment.setText(post.comment);

		return view;
	}

	public int getShownIndex() {
		return getArguments().getInt(INDEX_KEY, 0);
	}
}
