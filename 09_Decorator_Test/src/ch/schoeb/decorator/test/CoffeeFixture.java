package ch.schoeb.decorator.test;

import org.junit.Test;

import ch.schoeb.decorator.Coffee;
import ch.schoeb.decorator.Milk;
import ch.schoeb.decorator.PlainCoffee;
import ch.schoeb.decorator.Sprinkles;
import ch.schoeb.decorator.Sugar;

import junit.framework.*;

public class CoffeeFixture extends TestCase {

	@Test
	public void testNavData() {
		PlainCoffee coffee = new PlainCoffee();

		assertEquals(3.0, coffee.getCost());
		assertEquals("Coffee", coffee.getIngredients());
	}
	
	public void testCoffeeWithMilk() {
		Coffee coffee = new Milk(new PlainCoffee());

		assertEquals(3.5, coffee.getCost());
		assertEquals("Coffee, Milk", coffee.getIngredients());
	}

	public void testCoffeeWithSugar() {
		Coffee coffee = new Sugar(new PlainCoffee());

		assertEquals(3.2, coffee.getCost());
		assertEquals("Coffee, Sugar", coffee.getIngredients());
	}
	
	public void testCoffeeWithSprinkles()
	{
		Coffee coffee = new Sprinkles(new PlainCoffee());

		assertEquals(3.3, coffee.getCost());
		assertEquals("Coffee, Sprinkles", coffee.getIngredients());
	}
	
	public void testCoffeeWithMilkAndSugar()
	{
		Coffee coffee = new Milk(new Sugar(new PlainCoffee()));

		assertEquals(3.7, coffee.getCost());
		assertEquals("Coffee, Sugar, Milk", coffee.getIngredients());
	}
	
	public void testFullBlownCoffee()
	{
		Coffee coffee = new Sprinkles(new Milk(new Sugar(new PlainCoffee())));

		assertEquals(4.0, coffee.getCost());
		assertEquals("Coffee, Sugar, Milk, Sprinkles", coffee.getIngredients());
	}

}
