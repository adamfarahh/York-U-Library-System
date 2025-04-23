package project;

import java.time.LocalDate;

public class OnlineBook 
{
	String onlineBookName;
	LocalDate courseEndDate;
	
	public OnlineBook(String onlineBookName)
	{
		this.onlineBookName = onlineBookName;
	}
	
	public void setCourseEndDate(LocalDate endDate)
	{
		this.courseEndDate = endDate;
	}
	
	public LocalDate getCourseEndDate()
	{
		return courseEndDate;
	}
	
	public static OnlineBook giveAccessToOnlineBook(String onlineBookName)
	{
		return new OnlineBook(onlineBookName);
	}
	
	public void removeAccessToOnlineBook(Student student, OnlineBook tempOnlineBook)
	{
		LocalDate currentDate = LocalDate.now();
		
		if(student.courseTextbooks.contains(tempOnlineBook) && currentDate.isAfter(courseEndDate))
		{
			student.courseTextbooks.remove(tempOnlineBook);
		}
	}
	
	public static void openOnlineBook(String bookName)
	{
		System.out.println("Online book " + bookName + " is open");
	}
}
