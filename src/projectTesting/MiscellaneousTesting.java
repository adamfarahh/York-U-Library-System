package projectTesting;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.*;

//Testing for classes and methods that are smaller than 10 test cases each.

public class MiscellaneousTesting 
{
	private OnlineBook onlineBook;
	private LocalDate courseEndDate;
	private Student student;
	private PaymentManager studentAccount;
	
	@BeforeEach
	public void setup()
	{
		onlineBook = new OnlineBook("Cat in the very big hat.");
		courseEndDate = LocalDate.now().plusMonths(3);
		student = new Student("testemail@gmail.com", "testPassword123!");
		studentAccount = student.getBalance();
	}
	
	@Test
	void setAndGetCourseEndDate()
	{
		onlineBook.setCourseEndDate(courseEndDate);
		assertEquals(courseEndDate, onlineBook.getCourseEndDate());
	}
	
	@Test
	void checkUserBalance0()
	{
		assertEquals(0, studentAccount.getBalance());
	}
	
	@Test
	void addToUserBalance()
	{
		studentAccount.addToBalance(10);
		assertEquals(10, studentAccount.getBalance());
	}
	
	@Test
	void removeFromUserBalance()
	{
		studentAccount.addToBalance(10);
		studentAccount.removeFromBalance(5);
		assertEquals(5, studentAccount.getBalance());
	}
	
	@Test
	void checkStoreCredit0()
	{
		assertEquals(0, studentAccount.getStoreCredit());
	}
	
	@Test
	void payWithStoreCredit()
	{
		studentAccount.payByCredit(10);
		assertEquals(10, studentAccount.getStoreCredit());
	}
	
	@Test
	void payOffStoreCredit1()
	{
		studentAccount.payByCredit(10);
		studentAccount.addToBalance(10);
		studentAccount.payOffStoreCredit();
		assertEquals(0, studentAccount.getBalance());
	}
	
	@Test
	void payOffStoreCredit2()
	{
		studentAccount.payByCredit(10);
		studentAccount.addToBalance(12);
		studentAccount.payOffStoreCredit();
		assertEquals(2, studentAccount.getBalance());
	}
	
	@Test
	void payByDebit()
	{
		studentAccount.addToBalance(10);
		studentAccount.payByDebit(8);
		assertEquals(2, studentAccount.getBalance());
	}
	
	@Test
	void payByCash()
	{
		studentAccount.addToBalance(10);
		studentAccount.payByCash(8);
		assertEquals(2, studentAccount.getBalance());
	}
	
	
}
