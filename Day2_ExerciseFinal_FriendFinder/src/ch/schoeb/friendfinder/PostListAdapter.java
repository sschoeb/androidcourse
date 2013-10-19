package ch.schoeb.friendfinder;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import ch.schoeb.friendfinder.model.Post;

import com.example.day2_exercisefinal_friendfinder.R;

public class PostListAdapter extends BaseAdapter {

	private List<Post> posts;
	private Context context;

	public PostListAdapter(Context context, List<Post> posts) {
		this.context = context;
		this.posts = posts;
	}

	@Override
	public int getCount() {
		return posts.size();
	}

	@Override
	public Object getItem(int position) {
		return posts.get(position);
	}

	@Override
	public long getItemId(int position) {
		return posts.get(position).id;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {

		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.postlistfragment_listviewitem, parent, false);

			ViewHolder holder = new ViewHolder();
			holder.textViewComment = (TextView) view.findViewById(R.id.commentTextView);
			holder.textViewName = (TextView) view.findViewById(R.id.userNameTextView);
			view.setTag(holder);
		}

		Post currentPostItem = (Post) getItem(position);

		ViewHolder holder = (ViewHolder) view.getTag();
		holder.textViewComment.setText(currentPostItem.comment);
		holder.textViewName.setText(currentPostItem.name);

		return view;
	}

	static class ViewHolder {
		TextView textViewComment;
		TextView textViewName;
	}
}
