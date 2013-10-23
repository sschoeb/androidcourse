package ch.schoeb.day2_exercise_05_actionbar;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class FirstViewFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.firstviewfragment, container, false);
		Button button = (Button) view.findViewById(R.id.detailButton);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(getActivity(), DetailActivity.class));
			}
		});

		return view;
	}

}
