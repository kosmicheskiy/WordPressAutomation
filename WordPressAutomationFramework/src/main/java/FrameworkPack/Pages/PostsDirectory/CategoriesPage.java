package FrameworkPack.Pages.PostsDirectory;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import FrameworkPack.Navigation.LeftNavigation;
import FrameworkPack.Selenium.Driver;

public class CategoriesPage {

	public static void GoTo() {
		LeftNavigation.Posts.Categories.Select();
	}

	public static CreateCategoryCommand CreateCategory(String name) {
		return new CreateCategoryCommand(name);
	}
	
	private static int GetCategoriesCount() {
		String countText = Driver.Instance.findElement(By.className("displaying-num")).getText();		
		return Integer.parseInt(countText.split(" ")[0]);
	}


	
}
