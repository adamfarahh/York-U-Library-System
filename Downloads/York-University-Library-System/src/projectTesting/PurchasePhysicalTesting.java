package projectTesting;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.*;

public class PurchasePhysicalTesting 
{
	private Student student;
	private PaymentManager studentAccount;
	private PhysicalItem item;
	ByteArrayOutputStream outputStream;
	
	@BeforeEach
	public void setup()
	{
		student = new Student("testemail@gmail.com", "testPassword123!");
		studentAccount = student.getBalance();
		item = new PhysicalItem("Planting strawberries and blueberries.", "AA23");
		
		outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
	}
	
	@Test
	void testPurchaseSuccess()
	{
		studentAccount.addToBalance(10);
		
		item.setPurchasable(true);
		item.setPurchasePrice(8);
		
		student.purchaseItem(item);
		
		System.setOut(System.out);
		
		String output = outputStream.toString();
		
		assertEquals("You have purchased the item at normal price.", output.trim());
	}
	
	@Test
	void testPurchaseFailed1() //Not enough money
	{
		item.setPurchasable(true);
		item.setPurchasePrice(10);
		
		student.purchaseItem(item);
		
		System.setOut(System.out);
		
		String output = outputStream.toString();
		
		assertEquals("You have insufficient funds to purchase the item.", output.trim());
	}
	
	@Test
	void testPurchaseFailed2() //Expensive book
	{
		studentAccount.addToBalance(10);
		
		item.setPurchasable(true);
		item.setPurchasePrice(12);
		
		student.purchaseItem(item);
		
		System.setOut(System.out);
		
		String output = outputStream.toString();
		
		assertEquals("You have insufficient funds to purchase the item.", output.trim());
	}
	
	@Test
	void testDiscountedPurchaseSuccess()
	{
		studentAccount.addToBalance(10);
		
		item.setPurchasable(true);
		item.setPurchasePrice(8);
		
		item.setDiscounted(true);
		item.setDiscountedPrice(6);
		
		student.purchaseItem(item);
		
		System.setOut(System.out);
		
		String output = outputStream.toString();
		
		assertEquals("You have purchased the item at a discount.", output.trim());
	}
	
	@Test
	void testDiscountedPurchaseFailed1() //Not enough money
	{
		studentAccount.addToBalance(4);
		
		item.setPurchasable(true);
		item.setPurchasePrice(8);
		
		item.setDiscounted(true);
		item.setDiscountedPrice(5);
		
		student.purchaseItem(item);
		
		System.setOut(System.out);
		
		String output = outputStream.toString();
		
		assertEquals("You have insufficient funds to purchase the item.", output.trim());
	}
	
	@Test
	void testDiscountedPurchaseFailed2() //Expensive discount
	{
		studentAccount.addToBalance(6);
		
		item.setPurchasable(true);
		item.setPurchasePrice(15);
		
		item.setDiscounted(true);
		item.setDiscountedPrice(10);
		
		student.purchaseItem(item);
		
		System.setOut(System.out);
		
		String output = outputStream.toString();
		
		assertEquals("You have insufficient funds to purchase the item.", output.trim());
	}
	
	@Test
	void refundFullPricedItemSuccess()
	{
		studentAccount.addToBalance(10);
		
		item.setPurchasable(true);
		item.setPurchasePrice(10);
		
		student.purchaseItem(item);
		
		assertEquals(0, studentAccount.getBalance());
		
		student.refundItem(item);
		
		assertEquals(10, studentAccount.getBalance());
	}
	
	@Test
	void refundFullPricedItemFailed()
	{
		studentAccount.addToBalance(10);
		
		item.setPurchasable(true);
		item.setPurchasePrice(10);
		
		student.purchaseItem(item);
		
		assertEquals(0, studentAccount.getBalance());
		
		item.setPurchasable(false); //Would not happen in real life.
		
		student.refundItem(item);
		
		assertEquals(0, studentAccount.getBalance());
	}
	
	@Test
	void refundDiscountedPricedItemSuccess()
	{
		studentAccount.addToBalance(10);
		
		item.setPurchasable(true);
		item.setPurchasePrice(10);
		
		item.setDiscounted(true);
		item.setDiscountedPrice(8);
		
		student.purchaseItem(item);
		
		assertEquals(2, studentAccount.getBalance());

		student.refundItem(item);
		
		assertEquals(10, studentAccount.getBalance());
	}
	
	@Test
	void refundDiscountedPricedItemFailed()
	{
		studentAccount.addToBalance(10);
		
		item.setPurchasable(true);
		item.setPurchasePrice(10);
		
		item.setDiscounted(true);
		item.setDiscountedPrice(8);
		
		student.purchaseItem(item);
		
		assertEquals(2, studentAccount.getBalance());
		
		item.setPurchasable(false); //Would not happen in real life.
		item.setDiscounted(false); //Would not happen in real life.

		student.refundItem(item);
		
		assertEquals(2, studentAccount.getBalance());
	}
}
