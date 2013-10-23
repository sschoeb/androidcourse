package ch.schoeb.day2_exercise1;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

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

		View newView = new View(context);
		ListView.LayoutParams layoutParams = new ListView.LayoutParams(ListView.LayoutParams.MATCH_PARENT, 50);
		newView.setLayoutParams(layoutParams);
		newView.setBackgroundColor(0xfff00000);
		return newView;

		// LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// view = inflater.inflate(/*your layout here use the R.-class/*, parent, false);

		// 1. Get the views from your layout by findViewById(R....)
		// 2. Get the current object which should be shown in this view using this adapter
		// 3. Set the comment/name for this view from the Post given by step 2
		// --> Use setText(...)
	}
}
