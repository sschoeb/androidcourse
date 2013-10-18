
public class BasicStatements {

	public void ShowStatements()
	{
		
		
		// if Statement
		int i = 0;
		if(i == 1)
		{
			return;
		}
		
		// for statement
		for(int k=0; k<10; k++)
		{
			// Do something 10 times
		}
		
		// foreach statement
		String[] data = new String[]{"Bli", "Bla", "blub"};
		for(String item : data)
		{
			// Do something for every string in data
		}
	}
	
	private void privateMethod()
	{
		
	}
	
	private Boolean methodWithParemter(String parameter)
	{
		return true;
	}
	
}
