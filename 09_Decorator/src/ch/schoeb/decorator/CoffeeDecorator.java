package ch.schoeb.decorator;

public abstract class CoffeeDecorator extends Coffee {
	protected final Coffee decoratedCoffee;
	protected String ingredientSeparator = ", ";

	public CoffeeDecorator(Coffee decoratedCoffee) {
		this.decoratedCoffee = decoratedCoffee;
	}

	public double getCost() { 
		return decoratedCoffee.getCost();
	}

	public String getIngredients() {
		return decoratedCoffee.getIngredients();
	}
}


