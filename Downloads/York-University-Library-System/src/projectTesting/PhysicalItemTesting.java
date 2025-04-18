package projectTesting;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.*;

public class PhysicalItemTesting 
{
	private PhysicalItem item;
	private LocalDate rentDate;
	private LocalDate dueDate;
	
	@BeforeEach
	public void setup()
	{
		item = new PhysicalItem("How to find ducks!", "C25");
		rentDate = LocalDate.now();
		dueDate = LocalDate.now().plusMonths(1);
	}
	
	@Test
	void getTitle()
	{
		 assertEquals("How to find ducks!", item.getTitle());
	}
	
	@Test 
	void getLocation()
	{
		assertEquals("C25", item.getLocation());
	}
	
	@Test
	void setAndGetRentDate()
	{
		item.setRentDate(rentDate);
		assertEquals(rentDate, item.getRentDate());
	}
	
	@Test
	void setAndGetDueDate()
	{
		item.setDueDate(dueDate);
		assertEquals(dueDate, item.getDueDate());
	}
	
	@Test
	void getPurchasable()
	{
		assertEquals(true, item.getPurchasable());
	}
	
	@Test
	void setPurchasable()
	{
		item.setPurchasable(false);
		assertEquals(false, item.getPurchasable());
	}
	
	@Test
	void getRentable()
	{
		assertEquals(true, item.getRentable());
	}
	
	@Test
	void setRentable()
	{
		item.setRentable(false);
		assertEquals(false, item.getRentable());
	}
	
	@Test
	void getDiscounted()
	{
		assertEquals(false, item.getDiscounted());
	}
	
	@Test
	void setDiscounted()
	{
		item.setDiscounted(true);
		assertEquals(true, item.getDiscounted());
	}
}
