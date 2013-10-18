package ch.schoeb.coffeemixer;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class PriceActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_price);

		// Find the TextView's where we would like to show the data passed by CoffeMixerActivity
		TextView priceView = (TextView) findViewById(R.id.textViewPrice);
		TextView ingrediantsView = (TextView) findViewById(R.id.textViewIngredients);

		// Get the passed data from the Intent and set it to the TextView's
		Bundle bundle = getIntent().getExtras();
		priceView.setText(bundle.getDouble("price") + "");
		ingrediantsView.setText(bundle.getString("ingrediants"));
	}
}
