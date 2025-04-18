package projectTesting;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.*;


public class NonFacultyMemberTesting {

    private NonFacultyMember nonFacultyMember;
    private Book book;
    private Book book2;
    private Book book3;

    @BeforeEach
    public void setUp() 
    {
        nonFacultyMember = new NonFacultyMember("nonfaculty@example.com", "Password123!");
        book = new Book("Introduction to Java", "Library Shelf A");
        book.setPurchasePrice(25.99); 
        book2 = new Book("Data Structures and Algorithms", "Library Shelf B"); 
        book2.setDiscounted(true);
        book2.setPurchasePrice(29.99); 
        book3 = new Book("Introduction to Nothing", "Library Shelf Not here");
    }
    
    @Test
    void nonFacultyMemberGetEmail() 
    {
        assertEquals("nonfaculty@example.com", nonFacultyMember.getEmail());
    }

    @Test
    void nonFacultyMemberLoginSuccess() 
    {
        assertEquals("Login successful.", nonFacultyMember.login("nonfaculty@example.com", "Password123!"));
        assertTrue(nonFacultyMember.isLoggedIn());
    }

    @Test
    void nonFacultyMemberLoginUnsuccessful_InvalidEmail() 
    {
        assertEquals("Your email was invalid", nonFacultyMember.login("nonfaculty21d03@example.com", "Password123!"));
        assertFalse(nonFacultyMember.isLoggedIn());
    }

    @Test
    void nonFacultyMemberLoginUnsuccessful_IncorrectPassword() 
    {
        assertEquals("Your password was incorrect", nonFacultyMember.login("nonfaculty@example.com", "WrongPassword"));
        assertFalse(nonFacultyMember.isLoggedIn());
    }

    @Test
    void nonFacultyMemberLoginUnsuccessful_AlreadyLoggedIn() 
    {
        nonFacultyMember.login("nonfaculty@example.com", "Password123!");
        assertEquals("You are already logged in.", nonFacultyMember.login("nonfaculty@example.com", "Password123!"));
        assertTrue(nonFacultyMember.isLoggedIn());
    }

    @Test
    void nonFacultyMemberLoginUnsuccessful_EmptyCredentials() 
    {
        assertEquals("Your email and password were incorrect", nonFacultyMember.login("", ""));
        assertFalse(nonFacultyMember.isLoggedIn());
    }

    @Test
    void testRentItemAndViewBorrowedItems() 
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        nonFacultyMember.rentItem(book);

        nonFacultyMember.viewBorrowedItems();

        System.setOut(System.out);

        String output = outputStream.toString();
        assertTrue(output.contains("Title: Introduction to Java"));
        assertTrue(output.contains("Location: Library Shelf A"));
    }
    
    @Test
    void testReturnItem() 
    {
        nonFacultyMember.rentItem(book2);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        nonFacultyMember.returnItem(book2);

        nonFacultyMember.viewBorrowedItems();

        System.setOut(System.out);

        String output = outputStream.toString();
        assertFalse(output.contains("Title: Data Structures and Algorithms"));
    }
    
    @Test
    void testPurchaseItem_WithSufficientFunds() 
    {
        nonFacultyMember.getBalance().addToBalance(30.0);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        PurchasePhysical.purchase(book, nonFacultyMember);

        System.setOut(System.out);

        String output = outputStream.toString();
        assertTrue(output.contains("You have purchased the item at normal price."));
    }

    @Test
    void testPurchaseItem_WithInsufficientFunds() 
    {
        nonFacultyMember.getBalance().addToBalance(20.0);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        PurchasePhysical.purchase(book, nonFacultyMember);

        System.setOut(System.out);

        String output = outputStream.toString();
        assertTrue(output.contains("You have insufficient funds to purchase the item."));
    }

    @Test
    void testPurchaseItem_Discounted() 
    {
        nonFacultyMember.getBalance().addToBalance(22.0);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        PurchasePhysical.purchase(book2, nonFacultyMember);

        System.setOut(System.out);

        String output = outputStream.toString();
        assertTrue(output.contains("You have purchased the item at a discount."));
        assertEquals("You have purchased the item at a discount.", output.trim());
    }

    @Test
    void testRefundItem_Purchased() 
    {
        nonFacultyMember.getBalance().addToBalance(30.0);
        PurchasePhysical.purchase(book, nonFacultyMember);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        PurchasePhysical.refundItem(book, nonFacultyMember);

        System.setOut(System.out);

        String output = outputStream.toString();
        assertTrue(output.contains("You have successfully refunded the purchased item."));
    }

    void testRefundItem_NotPurchased() 
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        PurchasePhysical.refundItem(book3, nonFacultyMember);

        System.setOut(System.out);

        String output = outputStream.toString();
        assertEquals("Sorry this item was not purchased here.", output.trim());
    }  
}
