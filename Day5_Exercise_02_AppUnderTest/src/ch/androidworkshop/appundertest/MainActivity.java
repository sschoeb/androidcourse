package ch.androidworkshop.appundertest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private EditText _firstNumberEditText;
	private EditText _secondNumberEditText;
	private Button _clearButton;
	private TextView _resultTextView;
	private TextView _resultTitleTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		_firstNumberEditText = (EditText) findViewById(R.id.editTextNumberOne);
		_secondNumberEditText = (EditText) findViewById(R.id.editTextNumberTwo);
		_clearButton = (Button) findViewById(R.id.clearButton);
		_resultTextView = (TextView) findViewById(R.id.textViewResult);
		_resultTitleTextView = (TextView) findViewById(R.id.textViewResultTitle);

		Button btn = (Button) findViewById(R.id.submitButton);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startDetailActivity();
			}
		});

		_clearButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				_resultTextView.setVisibility(View.GONE);
				_resultTitleTextView.setVisibility(View.GONE);
				_clearButton.setVisibility(View.GONE);
			}
		});

	}

	private void startDetailActivity() {
		double firstSummand = Double.parseDouble(_firstNumberEditText.getText().toString());
		double secondSummand = Double.parseDouble(_secondNumberEditText.getText().toString());

		double result = firstSummand + secondSummand;

		_resultTextView.setText(result + "");
		_resultTextView.setVisibility(View.VISIBLE);

		_resultTitleTextView.setVisibility(View.VISIBLE);
		_clearButton.setVisibility(View.VISIBLE);
	}

}
