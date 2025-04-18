package projectTesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import project.*;

public class Waitlist_VisitorTesting
{
	private Waitlist waitlist;
	private Visitor visitor;
	private User user1;
	private User user2;
	
	@BeforeEach
	public void setUp() 
	{
		waitlist = Waitlist.getInstance();
		
		user1 = new Visitor("test1@example.com", "password1", UserType.VISITOR);
		user2 = new Visitor("test2@example.com", "password2", UserType.VISITOR);
		visitor = new Visitor("test@example.com", "password", UserType.VISITOR);
	}
	
	@Test
	public void testAddToWaitlist() 
	{
		waitlist.addToWaitlist("Book1", user1);
		assertTrue(waitlist.isOnWaitlist("Book1"));
	}
	
	@Test
	public void testIsOnWaitlist() 
	{
		waitlist.addToWaitlist("Book2", user1);
		assertTrue(waitlist.isOnWaitlist("Book2"));
		assertFalse(waitlist.isOnWaitlist("NonExistentBook"));
	}
	@Test
	public void testGetNextUserFromWaitlist() 
	{
		waitlist.addToWaitlist("Book3", user1);
		waitlist.addToWaitlist("Book3", user2);
		assertEquals(user1, waitlist.getNextUserFromWaitlist("Book3"));
	}
	
	@Test
	public void testGetNextUserFromWaitlistWhenEmpty() 
	{
		assertNull(waitlist.getNextUserFromWaitlist("NonExistentBook"));
	}
	
	@Test
	public void testAddToWaitlistMultipleItems() 
	{
		waitlist.addToWaitlist("Book1", user1);
		waitlist.addToWaitlist("Book2", user1);
		assertTrue(waitlist.isOnWaitlist("Book1"));
		assertTrue(waitlist.isOnWaitlist("Book2"));
	}
	
	@Test
	public void testGetEmail() 
	{
		assertEquals("test@example.com", visitor.getEmail());
	}
	
	@Test
	public void testGetPassword() 
	{
		assertEquals("password", visitor.getPassword());
	}
	
	@Test
	public void testGetType() 
	{
		assertEquals(UserType.VISITOR, visitor.getType());
	}
	
	@Test
	public void testRequestBook() 
	{
		assertNull(visitor.requestBook("Java Programming", "Loan"));
	}
	
	@Test
	public void testGetBalance() 
	{
		assertNull(visitor.getBalance());
	}
	
	@Test
	public void testSubscribeToNewsLetter() 
	{
		assertDoesNotThrow(() -> visitor.subscribeToNewsLetter());
	}
	
	@Test
	public void testAccessNewsLetter() 
	{
		assertDoesNotThrow(() -> visitor.accessNewsLetter());
	}
	
	@Test
	public void testUnsubscribeFromNewsletter() 
	{
		assertDoesNotThrow(() ->
		visitor.unsubscribeFromNewsletter());
	}
}