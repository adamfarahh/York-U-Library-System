package project;

public class SystemReceiver implements BookStatusReceiver
{
	@Override
	public void borrowPhysical() 
	{
		System.out.println("You have borrowed the book");
	}	
	
	@Override
	public void returnPhysical() 
	{
		System.out.println("You have returned the book");
	}
}
