package projectTesting;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.*;

public class FacultyMemberTesting 
{
    private FacultyMember facultyMember;
    private Book book;
    private Book book2;
    private Book book3;

    @BeforeEach
    public void setUp() 
    {
        facultyMember = new FacultyMember("faculty@example.com", "Password123!");
        book = new Book("Introduction to Java", "Library Shelf A");
        book.setPurchasePrice(25.99); 
        book2 = new Book("Data Structures and Algorithms", "Library Shelf B"); 
        book2.setDiscounted(true);
        book2.setPurchasePrice(29.99); 
        book3 = new Book("Introduction to Nothing", "Library Shelf Not here");
    }
    
    @Test
    void facultyMemberGetEmail() 
    {
        assertEquals("faculty@example.com", facultyMember.getEmail());
    }

    @Test
    void facultyMemberLoginSuccess() 
    {
        assertEquals("Login successful.", facultyMember.login("faculty@example.com", "Password123!"));
        assertTrue(facultyMember.isLoggedIn());
    }
    
    @Test
    void logout()
    {
    	assertEquals("Login successful.", facultyMember.login("faculty@example.com", "Password123!"));
        assertTrue(facultyMember.isLoggedIn());
        
        facultyMember.logout();
        assertEquals(false, facultyMember.isLoggedIn());
    }

    @Test
    void facultyMemberLoginUnsuccessful_InvalidEmail() 
    {
        assertEquals("Your email was invalid", facultyMember.login("faculty21d03@example.com", "Password123!"));
        assertFalse(facultyMember.isLoggedIn());
    }

    @Test
    void facultyMemberLoginUnsuccessful_IncorrectPassword() 
    {
        assertEquals("Your password was incorrect", facultyMember.login("faculty@example.com", "WrongPassword"));
        assertFalse(facultyMember.isLoggedIn());
    }

    @Test
    void facultyMemberLoginUnsuccessful_AlreadyLoggedIn() 
    {
        facultyMember.login("faculty@example.com", "Password123!");
        assertEquals("You are already logged in.", facultyMember.login("faculty@example.com", "Password123!"));
        assertTrue(facultyMember.isLoggedIn());
    }

    @Test
    void facultyMemberLoginUnsuccessful_EmptyCredentials()
    {
        assertEquals("Your email and password were incorrect", facultyMember.login("", ""));
        assertFalse(facultyMember.isLoggedIn());
    }
    
    @Test
    void testReturnItem() 
    {
        facultyMember.rentItem(book2);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        facultyMember.returnItem(book2);

        facultyMember.viewBorrowedItems();

        System.setOut(System.out);

        String output = outputStream.toString();
        assertFalse(output.contains("Title: Data Structures and Algorithms"));
    }
    
    @Test
    void testPurchaseItem_WithSufficientFunds() 
    {
        facultyMember.getBalance().addToBalance(30.0);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        PurchasePhysical.purchase(book, facultyMember);

        System.setOut(System.out);

        String output = outputStream.toString();
        assertTrue(output.contains("You have purchased the item at normal price."));
    }

    @Test
    void testPurchaseItem_WithInsufficientFunds() 
    {
        facultyMember.getBalance().addToBalance(20.0);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        PurchasePhysical.purchase(book, facultyMember);

        System.setOut(System.out);

        String output = outputStream.toString();
        assertTrue(output.contains("You have insufficient funds to purchase the item."));
    }

    @Test
    void testPurchaseItem_Discounted() 
    {
        facultyMember.getBalance().addToBalance(22.0);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        PurchasePhysical.purchase(book2, facultyMember);

        System.setOut(System.out);

        String output = outputStream.toString();
        assertTrue(output.contains("You have purchased the item at a discount."));
        assertEquals("You have purchased the item at a discount.", output.trim());

    }

    @Test
    void testRefundItem_Purchased() {
        facultyMember.getBalance().addToBalance(30.0);
        PurchasePhysical.purchase(book, facultyMember);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        PurchasePhysical.refundItem(book, facultyMember);

        System.setOut(System.out);

        String output = outputStream.toString();
        assertTrue(output.contains("You have successfully refunded the purchased item."));
    }

    @Test
    void getPassword()
    {
    	assertEquals("Password123!", facultyMember.getPassword());
    }
    
    @Test
    void getType()
    {
    	assertEquals(UserType.FACULTY_MEMBER, facultyMember.getType());
    }
    
    @Test
    void searchBook()
    {
    	Book testBook = new Book("Book Title", "AA65");
    	
    	BookSearch.addBook(testBook);
    	Book testBook2 = facultyMember.searchBook("Book Title");
    	assertEquals(testBook2.getName(), testBook.getName());
    }
    
    @Test
    void similarBooks()
    {
    	Book testBook = new Book("Book Title", "AA65");
    	
    	Book testBook2 = new Book("Book Title 2", "AA66");
    	BookSearch.addBook(testBook);
    	BookSearch.addBook(testBook2);
    	
    	List<Book> bookList = facultyMember.findSimilarBooks(testBook);
    	
    	assertEquals(true, bookList.contains(testBook2));
    }
    
    @Test
    void requestBook()
    {
    	facultyMember.requestBook("I want this book", "textbook");
    }
    
    @Test
    void addCourse()
    {
    	LocalDate date = LocalDate.now();
    	
    	facultyMember.addCourse("This course", "This textbook");
    	
    	facultyMember.setCourseDeadline("This course", date);
    	
    	assertEquals("" + date, facultyMember.getCourseDeadline("This course"));
    }
    
    @Test
    void addCourse2()
    {
    	facultyMember.addCourse("This course", null);
    	
    	assertEquals(false, facultyMember.checkIfCourseTextbook("This course"));
    }
    
    @Test
    void courseManager()
    {
    	SystemManager systemManager = new SystemManager();
    	
    	CourseManager courseManager = new CourseManager(facultyMember, systemManager);
    	
    	courseManager.addTextbookForCouse("This course", "This textbook");
    	
    	courseManager.notifyFaculty();
    	
    	courseManager.checkAndNotifyTextbookAvailability();
    }
    
    @Test
    void purchaseItem()
    {
    	facultyMember.purchaseItem(book);
    }
    
    @Test
    void refundItem()
    {
    	facultyMember.purchaseItem(book);
    	facultyMember.refundItem(book);
    }
    
    
    
}
