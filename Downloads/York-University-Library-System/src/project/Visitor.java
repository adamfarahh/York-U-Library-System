package project;

import java.util.List;

public class Visitor implements User 
{
	private String email;
    private String password;
    private UserType type;

    private boolean loggedIn;
    private PaymentManager balance;
    SystemDirectory directory;
	
    public Visitor(String email, String password, UserType type) 
	{
	    this.email = email;
	    this.password = password;
	    this.type = type;
	    this.loggedIn = false;
	    this.balance = null;
	    this.directory = null;
	}

    @Override
    public String login(String email, String password) {
		return null;
        // Method intentionally left blank for Visitor
    }

    @Override
    public void logout() {
        // Method intentionally left blank for Visitor
    }

    @Override
    public void rentItem(PhysicalItem physical) {
        // Method intentionally left blank for Visitor
    }

    @Override
    public void viewBorrowedItems() {
        // Method intentionally left blank for Visitor
    }

    @Override
    public void returnItem(PhysicalItem physical) {
        // Method intentionally left blank for Visitor
    }

    @Override
    public void purchaseItem(PhysicalItem physical) {
        // Method intentionally left blank for Visitor
    }
    
    @Override
    public void refundItem(PhysicalItem physical) 
	{
    	// Method intentionally left blank for Visitor
	}

    @Override
    public PaymentManager getBalance() {
        // Method intentionally left blank for Visitor
        return null;
    }

    @Override
    public void subscribeToNewsLetter() {
        // Method intentionally left blank for Visitor
    }

    @Override
    public void accessNewsLetter() {
    	 // Method intentionally left blank for Visitor
    }
    
    @Override
    public void unsubscribeFromNewsletter() {
        // Method intentionally left blank for Visitor
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
	
	@Override
	public BookRequest requestBook(String bookTitle, String requestType)
	{
		// Method intentionally left blank for Visitor
		return null;
	}
}
