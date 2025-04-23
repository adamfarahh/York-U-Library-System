package project;

public class ReturnPhysicalCommand implements Command
{
	private BookStatusReceiver bookstatus;
	
	public ReturnPhysicalCommand(BookStatusReceiver br) 
	{
		this.bookstatus = br;
	}
	
	public void execute() 
	{
		this.bookstatus.returnPhysical();
	}
}
