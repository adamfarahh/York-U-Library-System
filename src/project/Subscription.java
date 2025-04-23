package project;

import java.io.IOException;

public class Subscription 
{
    private String email;
    private boolean subscribed;

    public Subscription(String email) 
    {
        this.email = email;
        this.subscribed = false;
    }

    public String getEmail() 
    {
        return email;
    }

    public boolean isSubscribed() 
    {
        return subscribed;
    }

    // Code to subscribe the user to the newsletter
    public void subscribe() 
    {
        if(subscribed)
        {
        	System.out.println("You are currently subscribed.");
        }
        else
        {
        	System.out.println("Subscribing " + email + " to the newsletter...");
            subscribed = true;
            System.out.println("Subscription successful!");
        }
    }

    // Code to unsubscribe the user from the newsletter
    public void unsubscribe() 
    {
    	if(!subscribed)
    	{
    		System.out.println("You are currently not subscribed.");
    	}
    	else
    	{
    		System.out.println("Unsubscribing " + email + " from the newsletter...");
            subscribed = false;
            System.out.println("Unsubscription successful!");
    	}
    }

    public void accessNewYorkTimes()
    {
        if (subscribed) 
        {
            System.out.println("Accessing the New York Times online...");
            try 
            {
                // Open the New York Times website using the default browser
                String url = "https://www.nytimes.com/ca/";
                String os = System.getProperty("os.name").toLowerCase();
                if (os.contains("win")) 
                {
                    // For Windows
                    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
                } else if (os.contains("mac")) 
                {
                    // For Mac OS
                    Runtime.getRuntime().exec("open " + url);
                } else {
                    // For Linux/Unix
                    Runtime.getRuntime().exec("xdg-open " + url);
                }
            } 
            catch (IOException e) 
            {
                System.err.println("Error: Failed to open the New York Times website.");
                e.printStackTrace();
            }
        } 
        else 
        {
            System.out.println("You need to subscribe to access the New York Times online.");
        }
    }
}