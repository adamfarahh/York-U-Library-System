package project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CsvHandler 
{
	   private String filePath;
	   
	   public CsvHandler(String filePath) 
	   {
		   this.filePath = filePath;
	   }

	    public void readCsv() 
	    {
	        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) 
	        {
	            String line;
	            
	            while ((line = br.readLine()) != null) 
	            {
	                // Process the line
	                System.out.println(line);
	            }
	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
	    }

	    public void writeCsv(String data) 
	    {
	        try (FileWriter writer = new FileWriter(filePath, true)) 
	        {
	            writer.append(data);
	            writer.append("\n");
	            writer.flush();
	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
	    }
}
