package project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BooleanSupplier;

public class Student implements User 
{
    private String email;
    private String password;
    private UserType type;

    private boolean loggedIn;
    private PaymentManager balance;
    
    public SystemDirectory directory;
    Subscription subscription = null;

    public Student(String email, String password) 
    {
        this.email = email;
        this.password = password;
        this.type = UserType.STUDENT;
        
        directory = new SystemDirectory();
        balance = new PaymentManager();
        
        UserManager.registerUser(email);
    }

    // Methods from the interface
    public String login(String email, String password) {
        if (loggedIn) {
            return "You are already logged in.";
        }

        if (this.email.equals(email) && this.password.equals(password)) {
            loggedIn = true;
            return "Login successful.";
        } else {
            if (!this.email.equals(email)) {
                return "Invalid email address.";
            } else {
                return "Incorrect password.";
            }
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
            	String RESET = "\u001B[0m";
                String RED = "\u001B[31m";
            	
                System.out.println("Title: " + item.getTitle());
                System.out.println("Location: " + item.getLocation());
                System.out.println("Rent Date: " + item.getRentDate());
                
                if(directory.check24Hours(item))
                {
                	System.out.println(RED + "Due Date: " + item.getDueDate() + RESET);
                }
                else
                {
                	System.out.println("Due Date: " + item.getDueDate());
                }
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
    
    @Override
    public String getEmail() 
    {
        return email;
    }
    
    public String getPassword()
    {
    	return password;
    }
    
    public UserType getType()
    {
    	return type;
    }

    // Additional methods specific to the Student class

    Map<String, String> courseInfo = new HashMap<String, String>();

    ArrayList<OnlineBook> courseTextbooks = new ArrayList<OnlineBook>();

    public void addCourse(String courseName, String textbookName) 
    {
        courseInfo.put(courseName, textbookName);
        
        if (textbookName != null) 
        {
            courseTextbooks.add(OnlineBook.giveAccessToOnlineBook(textbookName));
        }
    }

    public boolean checkCourse(String target) 
    {
        return courseInfo.containsKey(target);
    }

    public boolean checkIfCourseTextbook(String target) 
    {
        return checkCourse(target) && (courseInfo.get(target) != null);
    }
    
    public String getCourseTextbookName(String target)
    {
    	if(checkIfCourseTextbook(target))
    	{
    		return courseInfo.get(target);
    	}
    	else
    	{
    		return "This course may not have set a textbook yet.";
    	}
    }
    
    public void getAccessToOnlineTextbook(String textbookName)
    {
    	boolean textbookExists = false;
    	
    	for(OnlineBook onlineBook : courseTextbooks)
    	{
    		if(onlineBook.onlineBookName.equals(textbookName))
    		{
    			textbookExists = true;
    			break;
    		}
    	}
    	
    	if(!textbookExists)
    	{
    		courseTextbooks.add(new OnlineBook(textbookName));
    	}
    }
    
    public void openOnlineTextBook(String textbookName)
    {
    	boolean textbookExists = false;
    	
    	for(OnlineBook onlineBook : courseTextbooks)
    	{
    		if(onlineBook.onlineBookName.equals(textbookName))
    		{
    			textbookExists = true;
    			break;
    		}
    	}
    	
    	if(textbookExists)
    	{
    		OnlineBook.openOnlineBook(textbookName);
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