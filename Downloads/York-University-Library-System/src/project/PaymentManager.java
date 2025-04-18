package project;

import java.util.Scanner;

public class PaymentManager 
{
	private double balance = 0;
	private double storeCredit = 0;
	
	public void addToBalance(double money)
	{
		balance += money;
	}
	
	public void askUserPaymentType(double money)
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("What payment type do you want to pay with?");
		System.out.println("Debit, Credit, or Cash?");
		
		String paymentString = scanner.nextLine();
		
		do
		{
			if(paymentString.equalsIgnoreCase("Debit"))
			{
				payByDebit(money);
				break;
			}
			else if(paymentString.equalsIgnoreCase("Credit"))
			{
				payByCredit(money);
				break;
			}
			else if(paymentString.equalsIgnoreCase("Cash"))
			{
				payByCash(money);
				break;
			}
			else
			{
				System.out.println("You may have not entered a valid payment type. Try again.");
			}
			
			paymentString = scanner.nextLine();
			
		} while(!paymentString.equalsIgnoreCase("Debit") 
				&& !paymentString.equalsIgnoreCase("Credit")
				&& !paymentString.equalsIgnoreCase("Cash"));
		
		scanner.close();
	}
	
	public void removeFromBalance(double money)
	{
		if(balance >= money)
		{
			balance -= money;
		}
		else
		{
			System.out.println("Balance cannot remove this quantity of money.");
		}
	}
	
	//Returns the balance.
	public double getBalance()
	{
		return balance;
	}
	
	//Adding an amount the user owes as store credit.
	public void payByCredit(double money)
	{
		storeCredit += money;
	}
	
	//Removing store credit.
	public void payOffStoreCredit()
	{
		if(balance >= storeCredit)
		{
			balance -= storeCredit;
			storeCredit = 0;
		}
		else
		{
			System.out.println("Your balance is insufficient to pay off store credit");
		}
	}
	
	public double getStoreCredit()
	{
		return storeCredit;
	}
	
	//Same as removeFromBalance
	public void payByDebit(double money)
	{
		removeFromBalance(money);
	}
	
	//Same as removeFromBalance for now.
	public void payByCash(double money)
	{
		removeFromBalance(money);
	}
}
