package ch.schoeb.JavaBasics;

public class NestedClassExample {

		
	public void doSomething()
	{
		
	}
	
	public void doSomethingElse()
	{
		
	}
	
	public class InnerClass
	{
		
		public void doSomething()
		{
			NestedClassExample.this.doSomething();
			
			doSomethingElse();
		}
	}
	
	public static class StaticInnerClass
	{
		public void doSomething()
		{
		
		}
	}
	
}
