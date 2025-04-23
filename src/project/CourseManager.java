package project;

import java.util.HashMap;
import java.util.Map;

public class CourseManager 
{
	private FacultyMember facultyMember;
	private SystemManager systemManager;
	private Map<String, String> courseTextbooks;
	
    public CourseManager(FacultyMember facultyMember, SystemManager systemManager) 
    {
        this.facultyMember = facultyMember;
        this.systemManager = systemManager;
        this.courseTextbooks = new HashMap<>();
    }
    
    public void addTextbookForCouse(String course, String textbook) 
    {
    	courseTextbooks.put(course, textbook);
    }
    
    public void notifyFaculty() 
    {
    	for (Map.Entry<String, String> entry : courseTextbooks.entrySet()) 
    	{
            String course = entry.getKey();
            String textbook = entry.getValue();
            if (textbook == null || textbook.isEmpty()) 
            {
                systemManager.textbookNotAvailable(course);
            } 
            else 
            {
                facultyMember.receiveNotification("Added textbook for your course " + course + " is " + textbook);
            }
    	}
    }
    
    public void checkAndNotifyTextbookAvailability() 
    {
        for (String textbook : courseTextbooks.values()) 
        {
            if (textbook == null || textbook.isEmpty()) 
            {
                systemManager.textbookNotAvailable(textbook);
            }
        }
    }
}