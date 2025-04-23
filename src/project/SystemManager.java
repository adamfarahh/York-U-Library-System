package project;
import java.util.ArrayList;

//import org.apache.logging.log4j.*; 
public class SystemManager 
{
	private ArrayList<String> requestList;
    private FileHandler fileHandler;

    MementoCaretaker caretaker = new MementoCaretaker();
	MementoOriginator originator = new MementoOriginator();
	private int numSaves = 0; //Stores the number of mementos
	PhysicalItem restoredItem; //Restored Physical Item
    
	public SystemManager()
	{
		
	}
	
    public SystemManager(FileHandler fileHandler) 
    {
        this.fileHandler = fileHandler;
    }
    
  //Changes rentable to true in PhysicalItem.
    //An item is now able to be rented.
    public void enable(PhysicalItem physical)
    {
        physical.setRentable(true);
        originator.set(physical);
        caretaker.addMemento(originator.StoreInMemento());
        numSaves++;
        
        System.out.println("Successfully changed item to be rentable.");
    }
    
    //Changes rentable to false in PhysicalItem. 
    //An item is now not able to be rented.
    public void disable(PhysicalItem physical)
    {    
        physical.setRentable(false);
        originator.set(physical);
        caretaker.addMemento(originator.StoreInMemento());
        numSaves++;
        
        System.out.println("Successfully changed item to be not rentable.");
    }
    
    //Changes discoubtable to true in PhysicalItem.
    //An item is now discounted.
    public void setDiscounted(PhysicalItem physical, boolean bool)
    {
        physical.setDiscounted(bool);
        originator.set(physical);
        caretaker.addMemento(originator.StoreInMemento());
        numSaves++;
       
        if(bool)
        {
        	 System.out.println("Successfully changed item to be discounted.");
        }
        else
        {
        	System.out.println("Successfully changed item to be not discounted.");
        }
       
    }
    
    //Changes purchaseable to true in PhysicalItem.
    //An item is now purchaseable.
    public void setPurchasable(PhysicalItem physical, boolean bool)
    {
        physical.setPurchasable(bool);
        originator.set(physical);
        caretaker.addMemento(originator.StoreInMemento());
        numSaves++;
        
        if(bool)
        {
        	 System.out.println("Successfully changed item to be purchasable.");
        }
        else
        {
        	System.out.println("Successfully changed item to be not purchasable.");
        }
    }

    public PhysicalItem undoAction()
    {
        if(numSaves >= 1){
               numSaves--;
               restoredItem = originator.restoreFromMemento(caretaker.getMemento(numSaves));
            return restoredItem;
        }
        else{
            return null;
        }
    }    
	
    public void addBook(String details) {
        fileHandler.writeData("Book," + details);
        System.out.println("Book added: " + details);
    }

    public void addMagazine(String details) {
        fileHandler.writeData("Magazine," + details);
        System.out.println("Magazine added: " + details);
    }

    public void addCD(String details) {
        fileHandler.writeData("CD," + details);
        System.out.println("CD added: " + details);
    }
	
    public void textbookNotAvailable(String textbook) {
        System.out.println("Notification: The textbook " + textbook + " needs to be procured.");
    }
    
    public void handleRequest(String itemName, String itemType) 
	{
        String requestDetails = "Request received for " + itemType + ": " + itemName;
        requestList.add(requestDetails);

        // For demonstration, let's also print the request details
        System.out.println(requestDetails);
    }
}