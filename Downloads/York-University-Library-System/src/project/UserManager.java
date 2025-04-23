package project;

import java.util.HashMap;
import java.util.Map;

public class UserManager 
{
	public String email;
	
	public static Map<String, Integer> users = new HashMap<String, Integer>();
	
	private static IDGenerator instance = new IDGenerator();
	
	//A mapping between the email and a new userID
	protected static void registerUser(String email) 
	{
		users.put(email, instance.getNextID());
	}
	
	//Then we could check to see if they are in the system to login?
	protected static boolean validateUser(String email)
	{
		return users.containsKey(email);
	}
}
