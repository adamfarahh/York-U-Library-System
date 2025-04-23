package project;

import java.util.ArrayList;
import java.util.List;

public class BookSearch 
{
	private static List<Book> bookList = new ArrayList<Book>();
	
	//Method to add a book to the list
	public static void addBook(Book book)
	{
		bookList.add(book);
	}
	
	//Method to search for a book by name
	public static Book searchBook(String bookName)
	{
		for(Book book : bookList)
		{
			if(book.getName().equalsIgnoreCase(bookName))
			{
				return book;
			}
		}
		
		return null;
	}
	
	//Method to find similar books based on text similarity of titles
    public static List<Book> findSimilarBooks(Book book) 
    {
        List<Book> similarBooks = new ArrayList<>();
        
        String title = book.getName().toLowerCase(); // Convert title to lowercase for comparison
        String[] titleWords = title.split("\\s+"); // Split the title into words

        for (Book otherBook : bookList) 
        {
            if (!otherBook.equals(book)) // Exclude the same book from similar books
            {
                String otherTitle = otherBook.getName().toLowerCase();
                String[] otherTitleWords = otherTitle.split("\\s+");
            
                // Check if any word in the other book's title matches with any word in the original book's title
                for (String word : titleWords) 
                {
                    for (String otherWord : otherTitleWords) 
                    {
                        if (word.equals(otherWord)) 
                        {
                            similarBooks.add(otherBook);
                            break; // No need to continue checking for this book
                        }
                    }
                }
            }
        }
        
        return similarBooks;
    }

    public static List<Book> getBookSearch()
    {
    	return bookList;
    }
}
