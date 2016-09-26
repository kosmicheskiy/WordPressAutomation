package FrameworkPack.Pages.PostsDirectory;

import org.openqa.selenium.By;

import FrameworkPack.Navigation.LeftNavigation;
import FrameworkPack.Selenium.Driver;

public class TagsPage {
	public static void GoTo() {
		LeftNavigation.Posts.Tags.Select();
	}

	public static CreateCategoryCommand CreateCategory(String name) {
		return new CreateCategoryCommand(name);
	}
	
	private static int GetTagsCount() {
		String countText = Driver.Instance.findElement(By.className("displaying-num")).getText();		
		return Integer.parseInt(countText.split(" ")[0]);

	}
}
