package project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.BooleanSupplier;

public class NonFacultyMember implements User 
{
    private String email;
    private String password;
    private UserType type;

    private boolean loggedIn;
   
    private PaymentManager balance;
    SystemDirectory directory;
    Subscription subscription;

    public NonFacultyMember(String email, String password) 
    {
        this.email = email;
        this.password = password;
        this.type = UserType.NON_FACULTY_MEMBER;

        directory = new SystemDirectory();
        balance = new PaymentManager();
        
        UserManager.registerUser(email);
    }

    // Methods from the interface
    public String login(String email, String password) 
    {
        if (loggedIn) 
        {
            UserManager.validateUser(email);
            return "You are already logged in.";
            } 
            else if (this.email.equals(email) && this.password.equals(password)) 
            {
                loggedIn = true;
                return "Login successful.";

            } 
            else if (!this.email.equals(email) && !this.password.equals(password)) 
            {
                return "Your email and password were incorrect";
            } 
            else if (!this.email.equals(email)) 
            {
                return "Your email was invalid";
            } 
            else 
            {
                return "Your password was incorrect";

            }
    }

    public void logout() 
    {
        loggedIn = false;
    }

    public void rentItem(PhysicalItem physical) 
    {
        BorrowPhysical.borrowItem(directory, physical, this);
    }

    public void viewBorrowedItems() 
    {
        ArrayList<PhysicalItem> rentedItems = directory.getPhysicalRented();
        if (rentedItems.isEmpty()) 
        {
            System.out.println("You haven't borrowed any items.");
        } 
        else 
        {
            System.out.println("List of borrowed items:");
            for (PhysicalItem item : rentedItems) 
            {
                System.out.println("Title: " + item.getTitle());
                System.out.println("Location: " + item.getLocation());
                System.out.println("Rent Date: " + item.getRentDate());
                System.out.println("Due Date: " + item.getDueDate());
                System.out.println("------------------------------------");
            }
        }
    }

    public void returnItem(PhysicalItem physical) 
    {
        BorrowPhysical.returnItem(directory, physical, this);
    }

    public void purchaseItem(PhysicalItem physical) 
    {
    	PurchasePhysical.purchase(physical, this);
    }
    
	public void refundItem(PhysicalItem physical) 
	{
		PurchasePhysical.refundItem(physical, this);
	}

    public PaymentManager getBalance() 
    {
        return balance;
    }

    public void subscribeToNewsLetter() 
    {
        if (loggedIn && subscription == null) 
        {
            subscription = new Subscription(this.email);
            
            subscription.subscribe();
            
            System.out.println("Subscription request sent successfully!");
        }
        else if(loggedIn)
        {
        	subscription.subscribe();
        }
        else 
        {
            System.out.println("You must be logged in to subscribe to the newsletter.");
        }
    }
    
    public void accessNewsLetter()
    {
    	if (loggedIn && subscription != null)
    	{
    		subscription.accessNewYorkTimes();
    	}
    	else if(loggedIn)
    	{
    		System.out.println("You must subscribe to access the newsletter.");
    	}
    	else
    	{
    		System.out.println("You must be logged in to access the newsletter.");
    	}
    }

    public void unsubscribeFromNewsletter()
    {
        if (loggedIn && subscription == null) 
        {
            subscription = new Subscription(this.email);
            
            subscription.unsubscribe();
            
            System.out.println("Unsubscription request sent successfully");
        } 
        else if(loggedIn)
        {
        	subscription.unsubscribe();
        }
        else 
        {
            System.out.println("You must be logged in to unsubscribe to the newsletter.");
        }
    }
    
    protected Book searchBook(String bookName) 
    {
        return BookSearch.searchBook(bookName);
    }
    
    protected List<Book> findSimilarBooks(Book book) 
    {
        return BookSearch.findSimilarBooks(book);
    }
    
    @Override
    public String getEmail() 
    {
        return email;
    }

	@Override
	public String getPassword() 
	{
		return password;
	}

	@Override
	public UserType getType() 
	{
		return type;
	}

	public BookRequest requestBook(String bookTitle, String requestType) 
    {
		System.out.println("Your request for this book has gone through successfully");
        return new BookRequest(bookTitle, requestType, this.type);
    }

	public boolean isLoggedIn() {
		// TODO Auto-generated method stub
		return loggedIn;
	}

}
