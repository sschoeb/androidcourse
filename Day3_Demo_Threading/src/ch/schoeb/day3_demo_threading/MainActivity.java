package ch.schoeb.day3_demo_threading;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button tv = (Button)findViewById(R.id.textView);
		tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Thread t = new Thread(new Runnable() {
					
					@Override
					public void run() {
						Button tv = (Button)findViewById(R.id.textView);
						tv.setText("Do it");
						tv.setBackgroundColor(0xfff00000);
						tv.clearFocus();
						tv.forceLayout();
					}
				});
				t.start();
				
			}
		});
		
	}

}
