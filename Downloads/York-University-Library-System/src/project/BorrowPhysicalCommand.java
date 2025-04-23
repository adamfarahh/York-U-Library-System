package project;

public class BorrowPhysicalCommand implements Command
{
	private BookStatusReceiver bookstatus;
	
	public BorrowPhysicalCommand(BookStatusReceiver br) 
	{
		this.bookstatus = br;
	}

	public void execute() 
	{
		this.bookstatus.borrowPhysical();
	}

}
