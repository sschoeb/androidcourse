package ch.schoeb.decorator;

public class Sprinkles extends CoffeeDecorator {
	public Sprinkles(Coffee decoratedCoffee) {
		super(decoratedCoffee);
	}

	public double getCost() {
		return super.getCost() + 0.3;
	}

	public String getIngredients() {
		return super.getIngredients() + ingredientSeparator + "Sprinkles";
	}
}