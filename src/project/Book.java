package project;

public class Book extends PhysicalItem
{
	public int textbookEdition;
	
	public Book(String name, String location) 
	{
		super(name, location);
		BookSearch.addBook(this);
	}
	
	public String getName()
	{
		return name;
	}
}
