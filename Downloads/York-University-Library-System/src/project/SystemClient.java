package project;

public class SystemClient 
{
	//Remove in final build
	public static void main(String[] args) 
	{
		 // Creating receiver object
	    BookStatusReceiver br = new SystemReceiver();
	    
	    // Creating first command (BorrowPhysicalCommand) and associating with receiver
	    BorrowPhysicalCommand borrowPhysicalCommand = new BorrowPhysicalCommand(br);
	    
	    // Creating first invoker and associating with command
	    SystemInvoker system1 = new SystemInvoker(borrowPhysicalCommand);
	    
	    // Perform action on first invoker object (Borrow the book)
	    system1.execute();
	    
	    // Creating second command (ReturnPhysicalCommand) and associating with receiver
	    ReturnPhysicalCommand returnPhysicalCommand = new ReturnPhysicalCommand(br);
	    
	    // Creating second invoker and associating with command
	    SystemInvoker system2 = new SystemInvoker(returnPhysicalCommand);
	    
	    // Perform action on second invoker object (Return the book)
	    system2.execute();
	}
}
