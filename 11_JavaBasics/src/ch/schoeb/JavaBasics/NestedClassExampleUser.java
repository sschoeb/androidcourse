package ch.schoeb.JavaBasics;

public class NestedClassExampleUser {

	public void callStaticNestedClass()
	{
		NestedClassExample.StaticInnerClass staticInstance = new NestedClassExample.StaticInnerClass();
		staticInstance.doSomething();
		
		NestedClassExample outerClass = new NestedClassExample();
		outerClass.doSomething();
		outerClass.doSomethingElse();
		
		NestedClassExample.InnerClass innerInstance = outerClass.new InnerClass();
		innerInstance.doSomething();
	}
	
}

