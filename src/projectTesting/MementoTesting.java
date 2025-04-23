package projectTesting;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.*;

public class MementoTesting {

    private SystemManager TestSystem;
    private CD testCD;
    private Magazine testMag;
    
    @BeforeEach
    public void setUp() {
        TestSystem = new SystemManager();
        testCD = new CD("Music", "CD Shelf");
    	TestSystem.disable(testCD);
    	testMag = new Magazine("Time Magazine", "Magazine Rack");
    	TestSystem.disable(testMag);
    }	
    
    @Test
    void testCD_Enable() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    	
        TestSystem.enable(testCD);
    	
    	System.setOut(System.out);
    	
    	 String output = outputStream.toString();
         assertEquals("Successfully changed item to be rentable.", output.trim());
    }
    
    @Test
    void testMag_Disable() {
    	 ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
         System.setOut(new PrintStream(outputStream));
    	
    	TestSystem.disable(testCD);
    	

    	System.setOut(System.out);
    	
    	 String output = outputStream.toString();
         assertEquals("Successfully changed item to be not rentable.", output.trim());
    	
    }
    
    @Test
    void testMag_Enable() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    	
        TestSystem.enable(testMag);
    	
    	System.setOut(System.out);
    	
    	 String output = outputStream.toString();
         assertEquals("Successfully changed item to be rentable.", output.trim());
    }
    
    @Test
    void testCD_Disable() {
    	 ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
         System.setOut(new PrintStream(outputStream));
    	
    	TestSystem.disable(testMag);
    	

    	System.setOut(System.out);
    	
    	 String output = outputStream.toString();
         assertEquals("Successfully changed item to be not rentable.", output.trim());
    	
    }
    
    @Test
    void testItem_undo() {
   	 	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
   	 	System.setOut(new PrintStream(outputStream));
     
   	 	TestSystem.undoAction();
    	
   	 	System.setOut(System.out);
 	
   	 	String output = outputStream.toString();
   	 	assertEquals("From MementoOriginator: Restoring Previous Item Saved to Memento.", output.trim());
    }
    
    @Test
    void testCDDiscount_Enable() {
   	 	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
   	 	System.setOut(new PrintStream(outputStream));
     
   	 	TestSystem.setDiscounted(testCD, true);
    	
   	 	System.setOut(System.out);
 	
   	 	String output = outputStream.toString();
   	 	assertEquals("Successfully changed item to be discounted.", output.trim());
    }
    
    @Test
    void testCDDiscount_Disable() {
   	 	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
   	 	System.setOut(new PrintStream(outputStream));
     
   	 	TestSystem.setDiscounted(testCD, false);
    	
   	 	System.setOut(System.out);
 	
   	 	String output = outputStream.toString();
   	 	assertEquals("Successfully changed item to be not discounted.", output.trim());
    }
    
    @Test
    void testMagDiscount_Enable() {
   	 	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
   	 	System.setOut(new PrintStream(outputStream));
     
   	 	TestSystem.setDiscounted(testMag, true);
    	
   	 	System.setOut(System.out);
 	
   	 	String output = outputStream.toString();
   	 	assertEquals("Successfully changed item to be discounted.", output.trim());
    }
    
    @Test
    void testMagDiscount_Disable() {
   	 	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
   	 	System.setOut(new PrintStream(outputStream));
     
   	 	TestSystem.setDiscounted(testMag, false);
    	
   	 	System.setOut(System.out);
 	
   	 	String output = outputStream.toString();
   	 	assertEquals("Successfully changed item to be not discounted.", output.trim());
    }
    
    @Test
    void testCDPurchasable_Enable() {
   	 	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
   	 	System.setOut(new PrintStream(outputStream));
     
   	 	TestSystem.setPurchasable(testCD, true);
    	
   	 	System.setOut(System.out);
 	
   	 	String output = outputStream.toString();
   	 	assertEquals("Successfully changed item to be purchasable.", output.trim());
    }
    
    @Test
    void testCDPurchasable_Disable() {
   	 	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
   	 	System.setOut(new PrintStream(outputStream));
     
   	 	TestSystem.setPurchasable(testCD, false);
    	
   	 	System.setOut(System.out);
 	
   	 	String output = outputStream.toString();
   	 	assertEquals("Successfully changed item to be not purchasable.", output.trim());
    }
    
    @Test
    void testMagPurchasable_Enable() {
   	 	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
   	 	System.setOut(new PrintStream(outputStream));
     
   	 	TestSystem.setPurchasable(testMag, false);
    	
   	 	System.setOut(System.out);
 	
   	 	String output = outputStream.toString();
   	 	assertEquals("Successfully changed item to be not purchasable.", output.trim());
    }
    
    @Test
    void testMagPurchasable_Disable() {
   	 	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
   	 	System.setOut(new PrintStream(outputStream));
     
   	 	TestSystem.setPurchasable(testMag, false);
    	
   	 	System.setOut(System.out);
 	
   	 	String output = outputStream.toString();
   	 	assertEquals("Successfully changed item to be not purchasable.", output.trim());
    }
    
}