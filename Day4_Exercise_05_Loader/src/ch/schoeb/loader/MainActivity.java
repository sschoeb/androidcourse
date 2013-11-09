package ch.schoeb.loader;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {

	private SimpleCursorAdapter simpleCursorAdapter;

	private EditText editText;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		editText = (EditText) findViewById(R.id.editTextNote);
		listView = (ListView) findViewById(R.id.listView);

		simpleCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, null, new String[] { Contracts.Notes.COLUMN_NAME }, new int[] { android.R.id.text1 }, 0);
		listView.setAdapter(simpleCursorAdapter);

		Button btnAdd = (Button) findViewById(R.id.insertButton);
		btnAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				insertItemIntoDatabase();
			}
		});
		
		// TODO: Initialize LoaderManager here
	}

	private void insertItemIntoDatabase() {
		String newNote = editText.getText().toString();

		ContentValues values = new ContentValues();
		values.put(Contracts.Notes.COLUMN_NAME, newNote);
		getContentResolver().insert(Contracts.Notes.CONTENT_ID_URI_BASE, values);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		//TODO: Create a CursorLoader here
		return null;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		// TODO: set the new cursor to the adapter (Hint: swapCursor(...))
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		//TODO: set the cursor to null for the adapter
	}
}
