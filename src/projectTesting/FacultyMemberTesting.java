package projectTesting;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
    void testRentItemAndViewBorrowedItems() 
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        facultyMember.rentItem(book);

        facultyMember.viewBorrowedItems();

        System.setOut(System.out);

        String output = outputStream.toString();
        assertTrue(output.contains("Title: Introduction to Java"));
        assertTrue(output.contains("Location: Library Shelf A"));
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

    void testRefundItem_NotPurchased() 
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        PurchasePhysical.refundItem(book3, facultyMember);

        System.setOut(System.out);

        String output = outputStream.toString();
        assertEquals("Sorry this item was not purchased here.", output.trim());
    }  
}
