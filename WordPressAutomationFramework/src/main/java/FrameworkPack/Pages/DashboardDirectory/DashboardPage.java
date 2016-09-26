package FrameworkPack.Pages.DashboardDirectory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import FrameworkPack.Selenium.Driver;


public class DashboardPage {
	
	public static boolean isAt() {				
		WebElement elem = Driver.Instance.findElement(By.cssSelector(".wrap>h1"));
	
		if (elem.getText().equals("Dashboard")) 
			return true;
		
		return false;
	}

	
	
}
