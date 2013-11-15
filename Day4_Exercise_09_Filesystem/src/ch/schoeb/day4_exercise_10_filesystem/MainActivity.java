package ch.schoeb.day4_exercise_10_filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button buttonInternal = (Button) findViewById(R.id.createInternalButton);
		buttonInternal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				createInternalFile();
			}
		});

		Button buttonExternal = (Button) findViewById(R.id.createExternalButton);
		buttonExternal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				createExternalFile();
			}
		});

	}

	private void createExternalFile() {

		File root = Environment.getExternalStorageDirectory();
		File newFile = new File(root + "/mydemofile");

		try {
			newFile.createNewFile();
		} catch (IOException e) {
		}

	}

	private void createInternalFile() {

		File root = getFilesDir();
		File newFile = new File(root + "/mydemofile");

		try {
			newFile.createNewFile();
		} catch (IOException e) {
		}
	}

}
