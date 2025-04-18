package projectTesting;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.*;

public class SystemDirectoryTesting 
{
	private SystemDirectory directory;
	private Student student;
	private PhysicalItem item;
	private LocalDate dueDate;
	
	@BeforeEach
	public void setup()
	{
		student = new Student("testemail@gmail.com", "testPassword123!");
		item = new PhysicalItem("How to find ducks!", "C25");
		dueDate = LocalDate.now().plusMonths(1);
		directory = student.directory;
		
		student.rentItem(item);
	}
	
	@Test
	void checkDueDate()
	{
		assertEquals(dueDate, directory.dueDate(item));
	}
	
	@Test
	void check24HoursFalse()
	{
		assertEquals(false, directory.check24Hours(item));
	}
	
	@Test
	void check24HoursTrue()
	{
		item.setDueDate(dueDate.minusMonths(1));
		assertEquals(true, directory.check24Hours(item));
	}
	
	@Test
	void checkIfOverdueFalse()
	{
		assertEquals(false, directory.checkIfOverdue(item));
	}
	
	@Test
	void checkIfOverdueTrue()
	{
		item.setDueDate(dueDate.minusMonths(1).minusDays(2));
		assertEquals(true, directory.checkIfOverdue(item));
	}
	
	@Test
	void checkIfLostFalse()
	{
		assertEquals(false, directory.checkIfLost(item));
	}
	
	@Test
	void checkIfLostTrue()
	{
		item.setDueDate(dueDate.minusMonths(1).minusDays(16));
		assertEquals(true, directory.checkIfLost(item));
	}
	
	@Test
	void checkPenalty0()
	{
		assertEquals(0, directory.calculatePenalty(item));
	}
	
	@Test
	void calculatePenalty()
	{
		item.setDueDate(dueDate.minusMonths(1).minusDays(2));
		assertEquals(1.0, directory.calculatePenalty(item));
	}
	
	@Test
	void getNumberOfOverdueItems0()
	{
		assertEquals(0, directory.countOfOverdueItems());
	}
	
	@Test
	void getNumberOfOverdueItems()
	{
		item.setDueDate(dueDate.minusMonths(1).minusDays(2));
		assertEquals(1, directory.countOfOverdueItems());
	}
	
	@Test
	void getNumberOfLostItems0()
	{
		assertEquals(0, directory.countOfLostItems());
	}
	
	@Test
	void getNumberOfLostItems()
	{
		item.setDueDate(dueDate.minusMonths(1).minusDays(16));
		assertEquals(1, directory.countOfOverdueItems());
	}
}
