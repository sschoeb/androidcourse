package com.example.day2_exercise5_dialogtoast;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button toastButton = (Button) findViewById(R.id.buttonToast);
		toastButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				showToast();
			}
		});

		Button dialogButton = (Button) findViewById(R.id.buttonDialog);
		dialogButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				showDialog();
			}
		});
	}

	private void showDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Do you want to exit?").setTitle("TestTitle");
		builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// The User clicked the OK button, nothing todo here
			}
		});

		builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				Toast.makeText(MainActivity.this, "You clicked NO", Toast.LENGTH_LONG).show();

			}
		});

		AlertDialog dialog = builder.create();
		dialog.show();
	}

	private void showToast() {
		Toast.makeText(this, "TestToast", Toast.LENGTH_LONG).show();
	}

}
