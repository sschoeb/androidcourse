package ch.schoeb.friendfinder;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import ch.schoeb.friendfinder.model.Post;
import ch.schoeb.friendfinder.model.PostRepository;

import com.example.day2_exercisefinal_friendfinder.R;

public class PostListFragment extends ListFragment {

	private static final String SELECTED_INDEX_KEY = "SelectedIndexKey";
	private boolean isDualPane;
	private int currentCheckedIndex = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		List<Post> posts = PostRepository.getAllPosts();
		PostListAdapter adapter = new PostListAdapter(getActivity(), posts);
		setListAdapter(adapter);

		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position, long id) {
		showDetails(position);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		View detailsFrame = getActivity().findViewById(R.id.postdetails);
		isDualPane = detailsFrame != null;

		if (savedInstanceState != null) {
			currentCheckedIndex = savedInstanceState.getInt(SELECTED_INDEX_KEY, 0);
		}

		if (isDualPane) {
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			showDetails(currentCheckedIndex);
		}
	}

	private void showDetails(int index) {
		currentCheckedIndex = index;

		if (isDualPane) {
			PostDetailFragment details = (PostDetailFragment) getFragmentManager().findFragmentById(R.id.postdetails);
			if (details == null || details.getShownIndex() != index) {
				createDetailFragment(index);
			}
		} else {
			Intent intent = new Intent(getActivity(), PostDetailActivity.class);
			intent.putExtra(PostDetailFragment.INDEX_KEY, index);
			startActivity(intent);
		}
	}

	@Override
	public void onResume() {
		super.onResume();

		if (isDualPane) {
			getListView().setItemChecked(currentCheckedIndex, true);
		}

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(SELECTED_INDEX_KEY, currentCheckedIndex);
	}
	
	private void createDetailFragment(int index) {
		PostDetailFragment details;
		details = PostDetailFragment.newInstance(index);

		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.postdetails, details);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.commit();
	}
}
