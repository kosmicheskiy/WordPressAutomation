package LoginTests;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

import com.google.common.collect.Table.Cell;

public class SpreadsheetData {
	{
	try {
        // Open the Excel file
        FileInputStream fis = new FileInputStream("Z:\\Documents\\Bas\\blog\\datasources\\testdata.xls");
        // Access the required test data sheet
        HSSFWorkbook wb = new HSSFWorkbook(fis);
        HSSFSheet sheet = wb.getSheet("testdata");
        // Loop through all rows in the sheet
        // Start at row 1 as row 0 is our header row
        for(int count = 1;count<=sheet.getLastRowNum();count++){
            HSSFRow row = sheet.getRow(count);
            System.out.println("Running test case " + row.getCell(0).toString());
            // Run the test for the current test data row
            //runTest(row.getCell(1).toString(),row.getCell(2).toString());
        }
        fis.close();
    } catch (IOException e) {
        System.out.println("Test data file not found");
    }   
}
	}
