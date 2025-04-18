package project;
//https://www.youtube.com/watch?v=jOnxYT8Iaoo FOR CITATION

public class MementoOriginator 
{
    private PhysicalItem currItem;

    public void set(PhysicalItem newItem)
    {
        currItem = newItem;
    }

	public Memento StoreInMemento()
	{
		return new Memento(currItem);
	}

	public PhysicalItem restoreFromMemento(Memento memento)
	{
		currItem = memento.getSavedItem();
        System.out.println("From MementoOriginator: Restoring Previous Item Saved to Memento.\n");
		return currItem;
	}
}
