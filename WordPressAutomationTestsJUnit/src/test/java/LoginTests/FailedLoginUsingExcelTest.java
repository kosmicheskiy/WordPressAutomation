package LoginTests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import FrameworkPack.Pages.LoginDirectory.LoginPage;
import FrameworkPack.Selenium.Driver;
import FrameworkPack.Utilities.ElementsUtils;

public class FailedLoginUsingExcelTest {

	@Before
	public void Init() {
		Driver.Initialize();
		LoginPage.GoTo();

	}

	@Test
	public void FailloginExcelTest() {
		{
			try {
				File resoursesDiv = new File("src/test/resources");

				File excelSheet = new File(resoursesDiv, "LoginFailTestParams.xls");
				// Open the Excel file
				FileInputStream fis = new FileInputStream(excelSheet.getAbsolutePath());
				// Access the required test data sheet
				HSSFWorkbook wb = new HSSFWorkbook(fis);
				int index = wb.getSheetIndex("loginFail");
				HSSFSheet sheet = wb.getSheetAt(index);
				// Loop through all rows in the sheet
				// Start at row 1 as row 0 is our header row
				for (int count = 1; count <= sheet.getLastRowNum(); count++) {
					HSSFRow row = sheet.getRow(count);
					System.out.println("Running test case " + row.getCell(0).toString());
					// Run the test for the current test data row
					LoginPage.LoginAs(row.getCell(0).toString()).WithPassword(row.getCell(1).toString()).Login();
					Assert.assertTrue("Loged In", ElementsUtils.ElementIsPresent(By.id("login_error")));					
				}
				fis.close();
			} catch (IOException e) {
				System.out.println("Test data file not found");
			}
		}
	}

	@After
	public void CleanUp() {

		Driver.Close();
	}
}
