package ch.schoeb.JavaBasics.Exception;

public class ExceptionExample {

	public void doSomethingNotGood() {
		try {
			new DangerousController().executeDangerousStuff();
		} catch (CustomException ex) {

		} finally {

		}
	}

	public void doSomethingNotGoodAndRouteException() throws CustomException  {
		new DangerousController().executeDangerousStuff();
	}

}
