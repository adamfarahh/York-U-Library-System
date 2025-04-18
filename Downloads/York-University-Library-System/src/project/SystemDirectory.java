package project;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class SystemDirectory 
{
	protected int itemID = 0; //Iterator pattern here.
	protected int borrowed; //The 10 physical items a user can borrow.
	protected String location; 
	ArrayList<PhysicalItem> physicalRented;
	
	public SystemDirectory()
	{
		itemID++;
		borrowed = 0;
		physicalRented = new ArrayList<PhysicalItem>();
	}
	
	//Return the due date for any item.
	//Change return type.
	public LocalDate dueDate(PhysicalItem physical) 
	{
		return physical.dueDate;
	}
	
	//Return true or false if the item has less than 24 hours till the due date.
	public boolean check24Hours(PhysicalItem physical)
	{
		LocalDateTime currentDateTime = LocalDateTime.now();
		LocalDateTime dueDateTime = LocalDateTime.of(physical.dueDate, LocalTime.MAX);
		
		return currentDateTime.plusHours(24).isAfter(dueDateTime);
	}
	
	//Returns true or false if the item is overdue.
	public boolean checkIfOverdue(PhysicalItem physical)
	{
		LocalDate currentDate = LocalDate.now();
		
		return currentDate.isAfter(physical.dueDate);
	}
	
	//Returns true or false if the item is lost.
	public boolean checkIfLost(PhysicalItem physical)
	{
		LocalDate dueDatePlus15 = physical.dueDate.plusDays(15);
		LocalDate currentDate = LocalDate.now();
		
		return currentDate.isAfter(dueDatePlus15);
	}
	
	//Calculates the penalty that is owed.
	public double calculatePenalty(PhysicalItem physical)
	{
		double penaltyPerDay = 0.5;
		
		if(checkIfOverdue(physical))
		{
			long daysOverdue = ChronoUnit.DAYS.between(physical.dueDate, LocalDate.now());
			
			return daysOverdue * penaltyPerDay;
		}
		else //Item not overdue
		{
			return 0;
		}
	}
	
	//Finds the number of overdue items.
	public int countOfOverdueItems()
	{
		int count = 0;
		
		for(PhysicalItem item : physicalRented)
		{
			if(checkIfOverdue(item))
			{
				count++;
			}
		}
		
		return count;
	}
	
	//Finds the number of lost items.
	public int countOfLostItems()
	{
		int count = 0;
		
		for(PhysicalItem item : physicalRented)
		{
			if(checkIfLost(item))
			{
				count++;
			}
		}
		
		return count;
	}
	
	public ArrayList<PhysicalItem> getPhysicalRented()
	{
		return physicalRented;
	}
}
