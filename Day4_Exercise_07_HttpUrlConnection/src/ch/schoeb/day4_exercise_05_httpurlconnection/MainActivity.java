package ch.schoeb.day4_exercise_05_httpurlconnection;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TextView resultTextView;
	private EditText urlEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		resultTextView = (TextView) findViewById(R.id.resultTextView);
		urlEditText = (EditText) findViewById(R.id.urlEditText);

		Button loadButton = (Button) findViewById(R.id.loadButton);
		loadButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {

				if (!isOnline()) {
					Toast.makeText(MainActivity.this, "Check network connection", Toast.LENGTH_LONG).show();
					return;
				}

				new DataLoader().execute(urlEditText.getText().toString());
			}
		});
	}

	public boolean isOnline() {
		ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		return (networkInfo != null && networkInfo.isConnected());
	}

	class DataLoader extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... urlToLoadParam) {

			String urlToLoad = urlToLoadParam[0];
			HttpURLConnection urlConnection = null;
			String data = "";
			try {
				URL url = new URL(urlToLoad);
				urlConnection = (HttpURLConnection) url.openConnection();
				data = getStreamContent(new BufferedInputStream(urlConnection.getInputStream()));
			} catch (MalformedURLException e) {
			} catch (IOException e) {
			} finally {
				if (urlConnection != null) {
					urlConnection.disconnect();
				}
			}

			return data;
		}

		private String getStreamContent(BufferedInputStream in) throws IOException {
			StringBuilder inputStringBuilder = new StringBuilder();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				inputStringBuilder.append(line);
			}
			String data = inputStringBuilder.toString();
			return data;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			resultTextView.setText(result);
		}

	}
}
