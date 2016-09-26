package FrameworkPack.Pages.LoginDirectory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import FrameworkPack.Selenium.Driver;

public class LoginCommand {

	private String userName;
	private String password;
	public LoginCommand(String userName) {	
		this.userName = userName;
	}
	
	public LoginCommand WithPassword(String password) {
		this.password = password;	
		return this;
	}
	
	public void Login() {
		WebElement loginInput = Driver.Instance.findElement(By.id("user_login"));
		loginInput.sendKeys(userName);
		
		WebElement passwordInput = Driver.Instance.findElement(By.id("user_pass"));
		passwordInput.sendKeys(password);
		
		WebElement logInButton = Driver.Instance.findElement(By.id("wp-submit"));
		logInButton.click();
	}
}
