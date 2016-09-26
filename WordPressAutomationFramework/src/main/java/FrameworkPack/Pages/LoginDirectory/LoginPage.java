package FrameworkPack.Pages.LoginDirectory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import FrameworkPack.Selenium.Driver;

public class LoginPage {

	
	
	public static void GoTo() {
		
		Driver.Instance.navigate().to(Driver.BaseAdress() + "wp-login.php");
		WebDriverWait wait = new WebDriverWait(Driver.Instance, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("user_login")));
		

	}
	
	public static LoginCommand LoginAs(String userName) {
		return new 	LoginCommand(userName);	
	} 
	
}


