package projectTesting;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.*;

public class StudentTesting {

    private Student student;
    private Book book;
    private Book book2;
    private Book book3;

    @BeforeEach
    public void setUp() 
    {
        student = new Student("abhinavsyal2103@gmail.com", "As123!");
        book = new Book("Introduction to Java", "Library Shelf A");
        book.setPurchasePrice(25.99); 
        book2 = new Book("Data Structures and Algorithms", "Library Shelf B"); 
        book2.setDiscounted(true);
        book2.setPurchasePrice(29.99); 
        book3 = new Book("Introduction to Nothing", "Library Shelf Not here");
    }
    
    @Test
    void studentGetEmail() 
    {
        assertEquals("abhinavsyal2103@gmail.com", student.getEmail());
    }

    @Test
    void studentLoginSuccess() 
    {
        assertEquals("Login successful.", student.login("abhinavsyal2103@gmail.com", "As123!"));
        assertTrue(student.isLoggedIn());
    }

    @Test
    void studentLoginUnsuccessful_InvalidEmail() 
    {
        assertEquals("Invalid email address.", student.login("abhinavsyal21d03@gmail.com", "As123!"));
        assertFalse(student.isLoggedIn());
    }

    @Test
    void studentLoginUnsuccessful_IncorrectPassword() 
    {
        assertEquals("Incorrect password.", student.login("abhinavsyal2103@gmail.com", "WrongPassword"));
        assertFalse(student.isLoggedIn());
    }

    @Test
    void studentLoginUnsuccessful_AlreadyLoggedIn() 
    {
        student.login("abhinavsyal2103@gmail.com", "As123!");
        assertEquals("You are already logged in.", student.login("abhinavsyal2103@gmail.com", "As123!"));
        assertTrue(student.isLoggedIn());
    }

    @Test
    void studentLoginUnsuccessful_EmptyCredentials() 
    {
        assertEquals("Invalid email address.", student.login("", ""));
        assertFalse(student.isLoggedIn());
    }

    @Test
    void testRentItemAndViewBorrowedItems() 
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        student.rentItem(book);

        student.viewBorrowedItems();

        System.setOut(System.out);

        String output = outputStream.toString();
        assertTrue(output.contains("Title: Introduction to Java"));
        assertTrue(output.contains("Location: Library Shelf A"));
    }
    
    @Test
    void testReturnItem() 
    {
        student.rentItem(book2);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        student.returnItem(book2);

        student.viewBorrowedItems();

        System.setOut(System.out);

        String output = outputStream.toString();
        assertFalse(output.contains("Title: Data Structures and Algorithms"));
    }
    
    @Test
    void testPurchaseItem_WithSufficientFunds() 
    {
        student.getBalance().addToBalance(30.0);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        PurchasePhysical.purchase(book, student);

        System.setOut(System.out);

        String output = outputStream.toString();
        assertTrue(output.contains("You have purchased the item at normal price."));
    }

    @Test
    void testPurchaseItem_WithInsufficientFunds() 
    {
        student.getBalance().addToBalance(20.0);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        PurchasePhysical.purchase(book, student);

        System.setOut(System.out);

        String output = outputStream.toString();
        assertTrue(output.contains("You have insufficient funds to purchase the item."));
    }

    @Test
    void testPurchaseItem_Discounted() 
    {
        student.getBalance().addToBalance(22.0);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        PurchasePhysical.purchase(book2, student);

        System.setOut(System.out);

        String output = outputStream.toString();
        assertTrue(output.contains("You have purchased the item at a discount."));
        assertEquals("You have purchased the item at a discount.", output.trim());
    }

    @Test
    void testRefundItem_Purchased() 
    {
        student.getBalance().addToBalance(30.0);
        PurchasePhysical.purchase(book, student);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        PurchasePhysical.refundItem(book, student);

        System.setOut(System.out);

        String output = outputStream.toString();
        assertTrue(output.contains("You have successfully refunded the purchased item."));
    }

    void testRefundItem_NotPurchased() 
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        PurchasePhysical.refundItem(book3, student);

        System.setOut(System.out);

        String output = outputStream.toString();
        assertEquals("Sorry this item was not purchased here.", output.trim());
    }  
}

