package ch.schoeb.coffeemixer;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class PriceActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_price);

		setupActionBar();

		TextView priceView = (TextView) findViewById(R.id.textViewPrice);
		TextView ingrediantsView = (TextView) findViewById(R.id.textViewIngredients);

		Bundle bundle = getIntent().getExtras();
		priceView.setText(bundle.getDouble("price") + "");
		ingrediantsView.setText(bundle.getString("ingrediants"));
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
