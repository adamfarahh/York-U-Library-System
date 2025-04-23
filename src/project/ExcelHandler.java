//package project;
// error due to apache import 
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.Iterator;
//
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//public class ExcelHandler {
//
//
//    private String filePath;
//
//    public ExcelHandler(String filePath) {
//        this.filePath = filePath;
//    }
//
//    public void readExcel() 
//    {
//        try (FileInputStream fis = new FileInputStream(filePath);
//             Workbook workbook = new XSSFWorkbook(fis)) 
//        {
//
//            Sheet sheet = workbook.getSheetAt(0);
//
//            for (Row row : sheet) 
//            {
//                Iterator<Cell> cellIterator = row.cellIterator();
//
//                while (cellIterator.hasNext()) 
//                {
//                    Cell cell = cellIterator.next();
//                    System.out.println(getCellValue(cell));
//                }
//            }
//        } 
//        catch (IOException e) 
//        {
//            e.printStackTrace();
//        }
//    }
//
//    public void writeExcel(String data) 
//    {
//        Workbook workbook = null;
//        FileOutputStream fos = null;
//
//        try 
//        {
//            File file = new File(filePath);
//            if (file.exists() && !file.isDirectory()) 
//            {
//                try (FileInputStream fis = new FileInputStream(file)) 
//                {
//                    workbook = new XSSFWorkbook(fis);
//                }
//            } 
//            else 
//            {
//                workbook = new XSSFWorkbook();
//            }
//            
//            Sheet sheet = workbook.createSheet("Data" + (workbook.getNumberOfSheets() + 1));
//
//            Row row = sheet.createRow(0);
//            Cell cell = row.createCell(0);
//            cell.setCellValue(data);
//
//            fos = new FileOutputStream(filePath);
//            workbook.write(fos);
//
//        } 
//        catch (IOException e) 
//        {
//            e.printStackTrace();
//        } 
//        finally 
//        {
//            try 
//            {
//                if (fos != null) fos.close();
//                if (workbook != null) workbook.close();
//            } 
//            catch (IOException e) 
//            {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private String getCellValue(Cell cell) 
//    {
//        if (cell.getCellType() == CellType.STRING) 
//        {
//            return cell.getStringCellValue();
//        }
//        else if (cell.getCellType() == CellType.NUMERIC) 
//        {
//            return String.valueOf(cell.getNumericCellValue());
//        }
//
//        return "";
//    }
//}