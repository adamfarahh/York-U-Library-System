package project;

public interface User 
{
    String email = null;
    String password = null;
    UserType type = null;
    boolean loggedIn = false;
    PaymentManager balance = null;
    SystemDirectory directory = null;

    String login(String email, String password);
    void logout();
    void rentItem(PhysicalItem physical);
    void viewBorrowedItems();
    void returnItem(PhysicalItem physical);
    void purchaseItem(PhysicalItem physical);
    void refundItem(PhysicalItem physical);
    PaymentManager getBalance();
    void subscribeToNewsLetter();
    void accessNewsLetter();
    void unsubscribeFromNewsletter();
    String getEmail();
    String getPassword();
    UserType getType();
    BookRequest requestBook(String bookTitle, String requestType); 
}