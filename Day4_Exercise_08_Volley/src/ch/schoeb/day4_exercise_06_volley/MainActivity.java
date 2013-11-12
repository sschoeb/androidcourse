package ch.schoeb.day4_exercise_06_volley;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	// Note: This URL can also be checked using your browser to get current data
	private static final String URL = "http://androidcourse.azurewebsites.net/api/Post/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btnSend = (Button) findViewById(R.id.buttonSend);
		btnSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				sendItemToServer();
			}
		});

		Button btnReceive = (Button) findViewById(R.id.buttonReceive);
		btnReceive.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				receiveItemsFromServer();
			}
		});
	}

	private void sendItemToServer() {
		// TODO: Send a new post to the server using volley
	}

	private void receiveItemsFromServer() {
		// TODO: Receive all posts from the server and show count as toast
	}

}
