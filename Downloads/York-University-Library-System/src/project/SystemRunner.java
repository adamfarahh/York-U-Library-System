package project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SystemRunner 
{
	public static void main(String [] args)
	{
		Student testStudent = new Student("testEmail@gmail.com", "As123!");
		
		testStudent.login("testEmail@gmail.com", "As123!");
		
		System.out.println("Student has logged on.");
		
		System.out.println();
		
		//Testing basic student variables.
		System.out.println("The student email is: " + testStudent.getEmail());
		System.out.println("The student password is: " + testStudent.getPassword());
		System.out.println("The student type is: " + testStudent.getType());
		System.out.println("The student account balance is: " + testStudent.getBalance().getBalance());
		
		Book testBook = new Book("How to read", "C45");
		
		System.out.println();
		
		System.out.println("The test book name is: " + testBook.getName());
		System.out.println("The test book location is: " + testBook.getLocation());
		System.out.println("The test book rentable condition is: " + testBook.getRentable());
		System.out.println("The test book purchasable condition is: " + testBook.getPurchasable());
		System.out.println("The test book discounted condition is: " + testBook.getDiscounted());
		
		System.out.println();
		
		testStudent.rentItem(testBook);
		
		System.out.println();
		
		testStudent.viewBorrowedItems();
		
		System.out.println();
		
		System.out.println("The student has borrowed " + testStudent.directory.borrowed + " items.");
		System.out.println("The id of the test book in the system is: " + testStudent.directory.itemID);
		
		System.out.println("The remaining number of books is: " + PhysicalItem.quantity);
		
		System.out.println();
		
		System.out.println("The test book is due " + testStudent.directory.dueDate(testBook));
		System.out.println("Checking to see if the book is due in the next 24 hours: " + testStudent.directory.check24Hours(testBook));
		System.out.println("Checking to see if the book is considered overdue: " + testStudent.directory.checkIfOverdue(testBook));
		System.out.println("Checking to see if the book is considered lost: " + testStudent.directory.checkIfLost(testBook));
		
		System.out.println("The book has incurred $" + testStudent.directory.calculatePenalty(testBook) + " penalty");
		
		System.out.println("The number of books overdue for this student is: " + testStudent.directory.countOfOverdueItems());
		System.out.println("The number of lost books for this student is: " + testStudent.directory.countOfLostItems());
		
		ArrayList<PhysicalItem> testPhysicalRented = testStudent.directory.getPhysicalRented();
		
		System.out.println("The student has borrowed the test book: " + testPhysicalRented.contains(testBook));
		System.out.println("The size of the list holding the test book is: " + testPhysicalRented.size());
		
		testStudent.returnItem(testBook);
		
		System.out.println();
		
		testBook.setPurchasePrice(10);
		
		testStudent.getBalance().addToBalance(35);
		
		testStudent.purchaseItem(testBook);
		
		System.out.println("The student balance is at: " + testStudent.getBalance().getBalance());
		System.out.println("The remaining number of this book left in the library: " + PhysicalItem.quantity);
		
		testStudent.refundItem(testBook);
		
		System.out.println("The student balance is at: " + testStudent.getBalance().getBalance());
		System.out.println("The remaining number of this book left in the library: " + PhysicalItem.quantity);
		
		System.out.println();
		
		testStudent.subscribeToNewsLetter();
		
		//testStudent.accessNewsLetter(); Will open the NYT newsletter when ran.
		
		testStudent.unsubscribeFromNewsletter();
		
		System.out.println();
		
		testStudent.addCourse("EECS3311", "Design Patterns");
		
		System.out.println("The student has this course: " + testStudent.checkCourse("EECS3311"));
		
		System.out.println("The course has this textbook: " + testStudent.checkIfCourseTextbook("EECS3311"));
		
		System.out.println("The name of the textbook is: " + testStudent.getCourseTextbookName("EECS3311"));
		
		System.out.println("The number of textbooks given to the student is: " + testStudent.courseTextbooks.size());
		
		System.out.println();
		
		testStudent.getAccessToOnlineTextbook("Design Patterns");
		
		testStudent.openOnlineTextBook("Design Patterns");
		
		Book testBook2 = new Book("How to cook", "C45");
		
		System.out.println("Checking to see if the book search finds test book: " + BookSearch.getBookSearch().contains(testBook));
		System.out.println("Checking to see if the book search finds test book 2: " + BookSearch.getBookSearch().contains(testBook2));
		
		Book searchedBook = testStudent.searchBook(testBook.name);
		
		System.out.println("The student searched for the book with name: " + searchedBook.name);
		
		List<Book> similarBooks = testStudent.findSimilarBooks(testBook);
		
		for(Book book : similarBooks)
		{
			System.out.println("This book was similar to test book " + book.getName());
		}
		System.out.println("The books similar to test book include test book 2: " + similarBooks.contains(testBook2));
		
		testStudent.logout();
		
		System.out.println("Student has logged off.");
		
		System.out.println();
		
		FacultyMember testFaculty = new FacultyMember("professor123@gmail.com", "Password123!");
		
		testFaculty.login("professor123@gmail.com", "Password123!");
		
		System.out.println("Professor has logged on.");
		
		testFaculty.addCourse("EECS2030", "Book on Coding");
		
		LocalDate currentDate = LocalDate.now();
		
		testFaculty.setCourseDeadline("EECS2030", currentDate);
		
		System.out.println("The course deadline is: " + testFaculty.getCourseDeadline("EECS2030"));
		
		System.out.println("Requesting a new textbook with title Software Engineering 102.");
		testFaculty.requestBook("Software Engineering 102", "textbook");
		
		testFaculty.logout();
		
		System.out.println("Professor has logged off.");
		
		System.out.println();
		
		SystemManager testManager = new SystemManager();
		
		System.out.println("Test manager has logged on.");
		
		testManager.disable(testBook);
		testManager.enable(testBook);
		testManager.setPurchasable(testBook, false);
		testManager.setPurchasable(testBook, true);
		testManager.setDiscounted(testBook, false);
		testManager.setDiscounted(testBook, true);
		
		System.out.println("Test manager has logged off.");
	}
}
