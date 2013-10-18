package ch.schoeb.decorator;

public class PlainCoffee extends Coffee {
	public double getCost() {
		return 3.0;
	}

	public String getIngredients() {
		return "Coffee";
	}
}
