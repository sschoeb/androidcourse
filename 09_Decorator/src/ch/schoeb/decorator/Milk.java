package ch.schoeb.decorator;

public class Milk extends CoffeeDecorator {
	public Milk(Coffee decoratedCoffee) {
		super(decoratedCoffee);
	}

	public double getCost() { 
		return super.getCost() + 0.5;
	}

	public String getIngredients() {
		return super.getIngredients() + ingredientSeparator + "Milk";
	}
}
