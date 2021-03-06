package ch.schoeb.day4_exercise_02_contentprovider;

import com.example.day4_exercise_02_contentprovider.R;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity {

	private Cursor cursor;

	private EditText editText;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		editText = (EditText) findViewById(R.id.editTextNote);
		listView = (ListView) findViewById(R.id.listView);

		Button btnAdd = (Button) findViewById(R.id.insertButton);
		btnAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				insertItemIntoDatabase();
			}
		});

	}

	@Override
	protected void onResume() {
		super.onResume();
		cursor = queryContentProvider();
		setAdapter(cursor);
	}

	private Cursor queryContentProvider() {
		//TODO: Query your ContentProvider 
		return null;
	}

	private void setAdapter(Cursor dataCursor) {
		String[] adapterColumns = new String[] { Contracts.Notes.COLUMN_NAME };
		int[] adapterLayoutIds = new int[] { android.R.id.text1 };
		int layout = android.R.layout.simple_list_item_1;

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, layout, dataCursor, adapterColumns, adapterLayoutIds, CursorAdapter.NO_SELECTION);
		listView.setAdapter(adapter);
	}

	@Override
	protected void onPause() {
		super.onPause();
		cursor.close();
	}

	private void insertItemIntoDatabase() {
		String newNote = editText.getText().toString();

		ContentValues values = new ContentValues();
		values.put(Contracts.Notes.COLUMN_NAME, newNote);
		getContentResolver().insert(Contracts.Notes.CONTENT_ID_URI_BASE, values);
		
		setAdapter(queryContentProvider());
	}
}
