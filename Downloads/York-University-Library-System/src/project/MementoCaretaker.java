package project;

import java.util.ArrayList;

//https://www.youtube.com/watch?v=jOnxYT8Iaoo FOR CITATION
//Contains a list of the mementos
public class MementoCaretaker 
{
	ArrayList<Memento> savedItems = new ArrayList<Memento>();
	
	public void addMemento(Memento m)
	{
		savedItems.add(m);
	}
	
	public Memento getMemento(int index)
	{
		return savedItems.get(index);
	}
}
