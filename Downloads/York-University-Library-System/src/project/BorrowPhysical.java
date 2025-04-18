package project;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class BorrowPhysical
{
    private static Waitlist waitlist = Waitlist.getInstance();
	
	//Remove 1 from quantity in PhysicalItem and add 1 to borrowed in SystemDirectory
	//Update the rentDate and dueDate accordingly in PhysicalItem.
	//Check if user is allowed to borrow items.
		//If the item is available, check if rentable is true in PhysicalItem.
		//If they have less than 10 items.
		//If they have less than 3 overdue books.
	protected static void borrowItem(SystemDirectory directory, PhysicalItem physical, User user)
	{
		
		if(physical.getRentable() && PhysicalItem.quantity > 0)
		{
			if(directory.borrowed < 10)
			{
				if(directory.countOfOverdueItems() < 3)
				{
					directory.physicalRented.add(physical);
					
					directory.borrowed++;
					PhysicalItem.quantity--;
					
					physical.rentDate = LocalDate.now();
					physical.dueDate = LocalDate.now().plusMonths(1);
					
					System.out.println("You have rented out the item.");
				}
				else
				{
					System.out.println("You have 3 or more overdue books.");
				}
			}
			else
			{
				System.out.println("You have reached the borrow limit.");
			}
		}
		else
		{
			System.out.println("This item has been marked non-rentable");
		}
	}
	
	//Add 1 to quantity in PhysicalItem and remove 1 to borrowed in SystemDirectory
	//Check if the item is overdue. If so incur a penalty to their account.
	public static void returnItem(SystemDirectory directory, PhysicalItem physical, User user)
	{
		if(directory.physicalRented.contains(physical))
		{
			if(directory.checkIfOverdue(physical))
			{
				double penalty = directory.calculatePenalty(physical);
				
				user.getBalance().removeFromBalance(penalty);
				
				System.out.println("This item was overdue. A penalty of $" + penalty + " has been incurred");  
				
				directory.physicalRented.remove(physical);
				
				directory.borrowed--;
				PhysicalItem.quantity++;
				
				physical.rentDate = null;
				physical.dueDate = null;
				
				// Attempt to borrow the physical item for the next user on the waitlist
				while (isPhysicalOnWaitlist(physical.name)) 
				{
				    User nextUser = getNextUserFromWaitlist(physical.name);
				    
				    if (nextUser != null && directory.borrowed < 10 && directory.countOfOverdueItems() < 3) 
				    {
				        borrowItem(directory, physical, nextUser);
				        break; // Exit the loop after successfully borrowing the item
				    }
				}
			}
			else
			{
				System.out.println("You have returned the item successfully.");  
				
				directory.physicalRented.remove(physical);
				
				directory.borrowed--;
				PhysicalItem.quantity++;
				
				physical.rentDate = null;
				physical.dueDate = null;
			}
		}
		else
		{
			System.out.println("You are trying to return an item you have not rented.");
		}
	}
	
	public static void joinWaitlist(SystemDirectory directory, PhysicalItem physical, User user) 
	{
	    if (directory.borrowed < 10 && directory.countOfOverdueItems() < 3) 
	    {
	        Waitlist waitlist = Waitlist.getInstance(); // Retrieve the Waitlist instance
	        waitlist.addToWaitlist(physical.name, user);
	        System.out.println(user.getEmail() + " has been added to the waitlist for " + physical.name);
	    } 
	    else 
	    {
	        System.out.println("Cannot join waitlist. Borrowing limit reached or overdue items limit exceeded.");
	    }
	}

	private static boolean isPhysicalOnWaitlist(String itemName) 
	{
        return waitlist.isOnWaitlist(itemName);
    }
	
	private static User getNextUserFromWaitlist(String itemName) 
	{
        return waitlist.getNextUserFromWaitlist(itemName);
    }

	public void request(SystemManager systemManager) 
	{
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the name of the physical item:");
        String itemName = scanner.nextLine();

        System.out.println("Enter the type of the physical item:");
        String itemType = scanner.nextLine();

        // Pass the request information to the SystemManager
        systemManager.handleRequest(itemName, itemType);

        scanner.close();
    }

	public static void checkDueDate(SystemDirectory directory) 
	{
		ArrayList<PhysicalItem> rentedItems = directory.getPhysicalRented();
		
		LocalDate currentDate = LocalDate.now();
		
		for (PhysicalItem item : rentedItems) 
		{
			LocalDate rentDate = item.getRentDate();
			LocalDate dueDate = item.getDueDate();
			
			// Check if the item is rented and due date is not null
			if (rentDate != null && dueDate != null) 
			{
				// difference between current date and due
				long daysDifference = ChronoUnit.DAYS.between(currentDate, dueDate);
				// If the difference is positive, it means the item is still not overdue
				if (daysDifference > 0) 
				{
					System.out.println("Item '" + item.getTitle() + "' is due in " +
					daysDifference + " days.");
				} 
				else 
				{
					// If the difference is negative, it means the item is overdue
					long overdueDays = Math.abs(daysDifference);
					System.out.println("Item '" + item.getTitle() + "' is overdue by " +
					overdueDays + " days.");
					// Here you can implement any action for handling overdue items,
					// such as sending notifications or applying penalties.
				}
			}
		}
	}
}
