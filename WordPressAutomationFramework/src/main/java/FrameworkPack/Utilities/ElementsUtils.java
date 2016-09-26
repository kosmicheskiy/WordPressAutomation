package FrameworkPack.Utilities;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import FrameworkPack.Selenium.Driver;

public class ElementsUtils {

	public static void ClickOnElement(By locator) {

		WebElement element = Driver.Instance.findElement(locator);
		element.click();

	}

	public static boolean ElementIsPresent(By locator) {

		try {
			if (locator == null) {
				return false;
			}		
			WebElement element = Driver.Instance.findElement(locator);
			if (element != null) {
				return true;
			}
			return false;
		}
		
		catch(NoSuchElementException e ) {
			return false;
		}
		
		
		

	}

}
