package project;

public class SystemInvoker 
{
	public Command command;
	
	public SystemInvoker(Command c)
	{
		this.command = c;
	}
	
	public void execute()
	{
		this.command.execute();
	}
}
