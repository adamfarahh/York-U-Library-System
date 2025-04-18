package project;

import java.util.Scanner;

public class UserFactory 
{
    public UserFactory() 
    {
    }

    public User getUser() 
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please enter your email:");
        String email = scanner.nextLine();
        
        boolean passwordValid = false;

        String password;
        do {
            System.out.println("Please enter your password:");
            password = scanner.nextLine();
            
            if (!this.strongPasswordCheck(password)) 
            {
                System.out.println("Your password does not meet the strength requirements. Please try again.");
            } 
            else 
            {
                passwordValid = true;
            }
        } while(!passwordValid);

        System.out.println("Please enter your account type:");
        System.out.println("Options: FACULTY_MEMBER, STUDENT, or NON_FACULTY_MEMBER");
        
        String userType = scanner.nextLine();
        
        if(checkIfValidUserType(userType))
        {
            if(!checkIfVisitor(userType)) //Only register user if they are not visitor
            {
                UserManager.registerUser(email);
                
                System.out.println("You have registered!");
            }
        }
        else
        {
            do
            {
                System.out.println("Your user type was not one of the valid options.");
                System.out.println("Please enter a valid user type.");
                
                userType = scanner.nextLine(); //Use a temporary variable each time.
            }
            while(!checkIfValidUserType(userType));
            
            if(!checkIfVisitor(userType)) //Only register user if they are not visitor
            {
                UserManager.registerUser(email);
                
                System.out.println("You have registered!");
            }
        }
        
        scanner.close();
        
        User user = null;
        if (userType.equalsIgnoreCase("FACULTY_MEMBER")) 
        {
            user = new FacultyMember(email, password);
        } 
        else if (userType.equalsIgnoreCase("STUDENT")) 
        {
            user = new Student(email, password);
        } 
        else if (userType.equalsIgnoreCase("NON_FACULTY_MEMBER"))
{
            user = new NonFacultyMember(email, password);
        }

        if (user != null && !checkIfVisitor(userType)) 
        {
            UserManager.registerUser(email);
        }
        
        return user;
    }

    public boolean strongPasswordCheck(String password)
	{
		boolean hasUpperCase = false;
		boolean hasLowerCase = false;
		boolean hasNumber = false;
		boolean hasSpecial = false;
		
		for(char character : password.toCharArray())
		{
			if(Character.isUpperCase(character))
			{
				hasUpperCase = true;
			}
			else if(Character.isLowerCase(character))
			{
				hasLowerCase = true;
			}
			else if(Character.isDigit(character))
			{
				hasNumber = true;
			}
			else
			{
				hasSpecial = true;
			}
		}
		
		return (hasUpperCase && hasLowerCase && hasNumber && hasSpecial);
	}
    
    public boolean checkIfValidUserType(String userType) 
    {
        for(UserType userTypeEnum : UserType.values())
        {
            //Check to see if the given String is one of the four user types.
            if(userTypeEnum.name().equalsIgnoreCase(userType)) 
            {
                return true;
            }
        }
        
        return false;
    }

    private boolean checkIfVisitor(String userType) 
    {
        return userType.equalsIgnoreCase("VISITOR");
    }
}
