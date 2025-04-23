package project;

public class CsvFileAdapter implements FileHandler 
{
    CsvHandler csvHandler;
    
    public CsvFileAdapter(CsvHandler csvHandler) 
    {
    	this.csvHandler = csvHandler;
    }

	@Override
	public void readData() 
	{
		csvHandler.readCsv();
	}

	@Override
	public void writeData(String Data) 
	{
		csvHandler.writeCsv(Data);
	}
    

}
