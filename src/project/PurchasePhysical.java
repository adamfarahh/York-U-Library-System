package project;

public class PurchasePhysical implements Checkout
{
	//Needs to check if user balance is more than price of phyiscal item.
	//Needs either discounted and more than 
		//discountPrice or purchaseable and more than purchasePrice
	//Needs to give discount prices if available.
	
	public static void purchase(PhysicalItem physical, User user)
	{
		double userBalance = user.getBalance().getBalance();
		
		if(userBalance > 0 && 
				((physical.getDiscounted() && userBalance >= physical.discountedPrice) 
						|| physical.getPurchasable() && userBalance >= physical.purchasePrice)) 
		{
			if(physical.getDiscounted())
			{
				user.getBalance().removeFromBalance(physical.discountedPrice);
				PhysicalItem.quantity--;
				System.out.println("You have purchased the item at a discount.");
			}
			else if(physical.getPurchasable() && !(physical.getDiscounted()))
			{
				user.getBalance().removeFromBalance(physical.purchasePrice);
				PhysicalItem.quantity--;
				System.out.println("You have purchased the item at normal price.");
			}
		}
		else
		{
			System.out.println("You have insufficient funds to purchase the item.");
		}
	}
	
	public static void purchaseWithConditions(PhysicalItem physical, User user)
	{
		double userBalance = user.getBalance().getBalance();
		
		if(userBalance > 0 && 
				((physical.getDiscounted() && userBalance >= physical.discountedPrice) 
						|| physical.getPurchasable() && userBalance >= physical.purchasePrice)) 
		{
			if(physical.getDiscounted())
			{
				user.getBalance().askUserPaymentType(physical.discountedPrice);
				PhysicalItem.quantity--;
				System.out.println("You have purchased the item at a discount.");
			}
			else if(physical.getPurchasable() && !(physical.getDiscounted()))
			{
				user.getBalance().askUserPaymentType(physical.purchasePrice);
				PhysicalItem.quantity--;
				System.out.println("You have purchased the item at normal price.");
			}
		}
		else
		{
			System.out.println("You have insufficient funds to purchase the item.");
		}
	}
	
	//The user can get a refund for a purchased item.
	public static void refundItem(PhysicalItem physical, User user)
	{
		if(physical.getDiscounted())
		{
			user.getBalance().addToBalance(physical.discountedPrice);
			
			if(user.getBalance().getStoreCredit()> 0)
			{
				user.getBalance().payOffStoreCredit();
			}
			
			PhysicalItem.quantity++;
			System.out.println("You have successfully refunded the discounted item.");
		}
		else if(physical.getPurchasable())
		{
			user.getBalance().addToBalance(physical.purchasePrice);
			
			if(user.getBalance().getStoreCredit()> 0)
			{
				user.getBalance().payOffStoreCredit();
			}
			
			PhysicalItem.quantity++;
			System.out.println("You have successfully refunded the purchased item.");
		}
		else if(!physical.isPurchasabletobuy() && !physical.isDiscountedtobuy())
		{
			System.out.println("Sorry this item was not purchased here.");
		}
	}
}
