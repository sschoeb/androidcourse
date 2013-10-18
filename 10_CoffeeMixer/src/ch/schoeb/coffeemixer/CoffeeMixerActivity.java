package ch.schoeb.coffeemixer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import ch.schoeb.decorator.Coffee;
import ch.schoeb.decorator.Milk;
import ch.schoeb.decorator.PlainCoffee;
import ch.schoeb.decorator.Sprinkles;
import ch.schoeb.decorator.Sugar;

public class CoffeeMixerActivity extends Activity {

	private CheckBox milkCheckBox;
	private CheckBox sugarCheckBox;
	private CheckBox sprinklesCheckBox;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_coffee_mixer);

		milkCheckBox = (CheckBox) findViewById(R.id.checkBoxMilk);
		sugarCheckBox = (CheckBox) findViewById(R.id.checkBoxSugar);
		sprinklesCheckBox = (CheckBox) findViewById(R.id.checkBoxSprinkles);

		Button calculateButton = (Button) findViewById(R.id.buttonCalculate);
		calculateButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Coffee coffee = createCoffee();

				Intent intent = new Intent(CoffeeMixerActivity.this, PriceActivity.class);
				intent.putExtra("price", coffee.getCost());
				intent.putExtra("ingrediants", coffee.getIngredients());
				startActivity(intent);
			}
		});
	}
	
	private Coffee createCoffee() {
		Coffee coffee = new PlainCoffee();

		if (milkCheckBox.isChecked()) {
			coffee = new Milk(coffee);
		}

		if (sugarCheckBox.isChecked()) {
			coffee = new Sugar(coffee);
		}

		if (sprinklesCheckBox.isChecked()) {
			coffee = new Sprinkles(coffee);
		}
		return coffee;
	}
}
