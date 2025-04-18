package project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BooleanSupplier;

public class FacultyMember implements User 
{
    private String email;
    private String password;
    private UserType type;
    
    private boolean loggedIn;
    
    private PaymentManager balance;
    SystemDirectory directory;
    Subscription subscription;

    public FacultyMember(String email, String password) 
    {
        this.email = email;
        this.password = password;
        this.type = UserType.FACULTY_MEMBER;
        
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
        if (loggedIn) 
        {
            subscription = new Subscription(this.email);
            
            subscription.subscribe();
            
            System.out.println("Subscription request sent successfully!");
        } 
        else 
        {
            System.out.println("You must be logged in to subscribe to the newsletter.");
        }
    }
    
    public void accessNewsLetter()
    {
    	if (loggedIn)
    	{
    		subscription.accessNewYorkTimes();
    	}
    	else
    	{
    		System.out.println("You must be logged in to access the newsletter.");
    	}
    }

    public void unsubscribeFromNewsletter()
    {
        if (loggedIn) 
        {
            subscription = new Subscription(this.email);
            
            subscription.unsubscribe();
            
            System.out.println("Unsubscription request sent successfully");
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

    // Additional methods specific to the FacultyMember class

	Map<String, String> courseInfo = new HashMap<String, String>();
	
	OnlineBook courseTextbook;
	
	//A Mapping from course to textbook. 
	//If there is no textbook in the course, then put null for textbook.
	//EECS3311 -> "Dogs are Cute!"
	
	public void addCourse(String courseName, String textbookName)
	{
		courseInfo.put(courseName, textbookName);
		
		//If the course is given a textbook
		if(!(textbookName == null))
		{
			courseTextbook = new OnlineBook(textbookName);
		}
	}
	
	//Assuming you want to check if a course is in the course map.
	public boolean checkCourse(String target) 
	{
		boolean check = courseInfo.containsKey(target);
		
		return check;
	}
	
	//Returns true or false if there is a set course textbook.
	public boolean checkIfCourseTextbook(String target)
	{
		boolean check = checkCourse(target);
		
		//Check to see if there is a textbook set for this course.
		if(check && !(courseInfo.get(target) == null))
		{
			return true;
		}
		//If there is no textbook set for the course.
		else 
		{
			return false;
		}
	}
	
	public void setCourseDeadline(String courseName, LocalDate courseEndDate)
	{
		if(checkCourse(courseName) && checkIfCourseTextbook(courseName))
		{
			courseTextbook.setCourseEndDate(courseEndDate);
			System.out.println("Changed the course deadline.");
		}
		//If course was inputted but course textbook was not inputted.
		else if(checkCourse(courseName) && !(checkIfCourseTextbook(courseName)))
		{
			System.out.println("This course has not added a textbook yet.");
		}
		else
		{
			System.out.println("This course is not listed.");
		}
	}
	
	public String getCourseDeadline(String courseName)
	{
		if(checkCourse(courseName) && checkIfCourseTextbook(courseName))
		{
			return "" + courseTextbook.getCourseEndDate();
		}
		//If course was inputted but course textbook was not inputted.
		else if(checkCourse(courseName) && !(checkIfCourseTextbook(courseName)))
		{
			return "This course has not added a textbook yet.";
		}
		else
		{
			return "This course is not listed.";
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
	
	public void receiveNotification(String message) 
	{
		System.out.println("Notification for Faculty Member: " + message);
	}

	public boolean isLoggedIn() {
		// TODO Auto-generated method stub
		return loggedIn;
	}
}
