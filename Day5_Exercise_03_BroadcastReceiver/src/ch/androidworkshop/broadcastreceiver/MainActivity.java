package ch.androidworkshop.broadcastreceiver;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			TextView numberTextView = (TextView) findViewById(R.id.textViewNumber);
			numberTextView.setText(extras.getString("number"));

			TextView messageTextView = (TextView) findViewById(R.id.textViewMessage);
			messageTextView.setText(extras.getString("message"));
		}

	}

}
