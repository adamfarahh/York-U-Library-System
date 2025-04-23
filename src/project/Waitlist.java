package project;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Waitlist 
{
    // Private static instance variable to hold the single instance of Waitlist
    private static Waitlist instance;

    // Private constructor to prevent instantiation from outside
    private Waitlist() 
    {
        waitlistMap = new HashMap<>();
    }

    // Public static method to get the single instance of Waitlist
    public static Waitlist getInstance() 
    {
        // Create the instance if it doesn't exist
        if (instance == null) 
        {
            instance = new Waitlist();
        }
        return instance;
    }

    private Map<String, Queue<User>> waitlistMap;

    public void addToWaitlist(String itemName, User user) 
    {
        if (!waitlistMap.containsKey(itemName)) 
        {
            waitlistMap.put(itemName, new LinkedList<>());
        }
        waitlistMap.get(itemName).offer(user);
    }

    public boolean isOnWaitlist(String itemName) 
    {
        return waitlistMap.containsKey(itemName) && !waitlistMap.get(itemName).isEmpty();
    }

    public User getNextUserFromWaitlist(String itemName) 
    {
        if (isOnWaitlist(itemName)) 
        {
            return waitlistMap.get(itemName).peek();
        }
        
        return null;
    }
}
