package project;

public class Memento 
{
	private PhysicalItem mementoItem;
	
	public Memento(PhysicalItem itemSave)
	{
		mementoItem = itemSave;
	}
	
	public PhysicalItem getSavedItem()
	{
		return mementoItem;
	}
}
