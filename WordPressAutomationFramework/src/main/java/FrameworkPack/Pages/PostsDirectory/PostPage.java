package FrameworkPack.Pages.PostsDirectory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import FrameworkPack.Selenium.Driver;

public class PostPage {

	public static String Title() {
		WebElement postPageTitle = Driver.Instance.findElement(By.className("entry-title"));
		if (postPageTitle.getText() != null){			
			return postPageTitle.getText();
		}
		return "";
	}

}
