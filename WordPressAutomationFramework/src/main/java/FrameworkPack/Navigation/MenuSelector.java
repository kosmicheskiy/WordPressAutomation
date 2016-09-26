package FrameworkPack.Navigation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import FrameworkPack.Selenium.Driver;

public class MenuSelector {

	public static void Select(String topLevelMenuId, String subMenuLinkText) {
		WebElement topLevelMenuElement = Driver.Instance.findElement(By.id(topLevelMenuId));
		topLevelMenuElement.click();

		WebDriverWait wait = new WebDriverWait(Driver.Instance, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(subMenuLinkText)));

		WebElement subMenuLinkElement = Driver.Instance.findElement(By.linkText(subMenuLinkText));
		subMenuLinkElement.click();		
	}
	
	public static void Select(String topLevelMenuId) {
		WebElement topLevelMenuElement = Driver.Instance.findElement(By.id(topLevelMenuId));
		topLevelMenuElement.click();
	
	}

}
