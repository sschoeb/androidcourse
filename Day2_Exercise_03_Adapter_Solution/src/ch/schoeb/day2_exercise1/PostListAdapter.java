package ch.schoeb.day2_exercise1;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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

	/**
	 * Gets called by the ListView for every item which is provided by this
	 * Adapter when the item becomes visible to the user.
	 * 
	 * This method should then return the view which does represent the item on
	 * the given position.
	 * 
	 * Android does cache the views which were visible to the user and does pass
	 * them into this method using the "view"-parameter. In case of this one is
	 * not null you should use the cached view instead of creating a new one
	 */
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		if (view == null) {
			// Using a LayoutInflater you can "instantiate" layout files
			// In this example we get an instance of the
			// res/layout/listvieitem.xml-class
			// You could cast it here into a LinearLayout as this is the
			// Root-Element
			// of this layout file
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.listviewitem, parent, false);

			// findViewById(..) calls are expensive -> Remember the result in
			// the ViewHolder
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.commentTextView = (TextView) view.findViewById(R.id.commentTextView);
			viewHolder.nameTextView = (TextView) view.findViewById(R.id.nameTextView);

			/*
			 * Use the Tag property of the view to store the ViewHolder
			 * 
			 * Clean-Code-Note: 
			 * I would suggest in this case to create a new
			 * class like "ViewHolderLinearLayout" which does inherit from
			 * LinearLayout but does provide a "ViewHolder"-Property. With this
			 * you could use this class as root layout in the
			 * res/layout/listviewitem.xml file and you don't have to use the
			 * tag property here. With this way you would be on the save side as
			 * you never now whether someone else tries to use the tag to store
			 * anything in it
			 */
			view.setTag(viewHolder);
		}

		Post currentPost = (Post) getItem(position);

		// Request the references to our TextViews from the ViewHolder
		// and directly set the text for the current post
		ViewHolder viewHolder = (ViewHolder) view.getTag();
		viewHolder.commentTextView.setText(currentPost.comment);
		viewHolder.nameTextView.setText(currentPost.name);

		// Return the view
		// --> This is now the fully prepared view which will be shown to
		// the user in the ListView
		return view;
	}

	static class ViewHolder {
		TextView commentTextView;
		TextView nameTextView;
	}
}
