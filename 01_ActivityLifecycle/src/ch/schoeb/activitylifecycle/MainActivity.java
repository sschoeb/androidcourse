package ch.schoeb.activitylifecycle;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;

public class MainActivity extends Activity {

	private final String Tag = "ActivityLifecycleTag";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Log.d(Tag, "onCreate");
		
		Button finishButton = (Button)findViewById(R.id.finishButton);
		finishButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(Tag, "onResume");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d(Tag, "onPause");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(Tag, "onDestroy");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d(Tag, "onRestart");
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(Tag, "onStart");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d(Tag, "onStop");
	}
}
