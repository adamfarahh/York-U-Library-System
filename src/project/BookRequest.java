package project;

public class BookRequest 
{
	private String bookTitle;
	private String requestType;
	private int priority;
	private UserType userRole;
	
	public BookRequest(String bookTitle, String requestType, UserType userRole) 
	{
        this.bookTitle = bookTitle;
        this.requestType = requestType;
        this.userRole = userRole;
        prioritizeRequest(this.requestType);
    }
	
	private void prioritizeRequest(String requestType) 
	{
		this.priority = "textbook".equalsIgnoreCase(requestType) ? 1 : 2;
	}
	
	public String getBookTitle() 
	{
		return bookTitle;
	}

    public String getType() 
    {
        return requestType;
    }

    public void setType(String requestType)
    {
        this.requestType = requestType;
        prioritizeRequest(this.requestType); 
    }
    
    public int getPriority() 
    {
    	return priority;
    }

    @Override
    public String toString() 
    {
        return "BookRequest{" +
                "bookTitle='" + bookTitle + '\'' + 
                ", type='" + requestType + '\'' +
                ", priority=" + priority +
                '}';
    }
}

