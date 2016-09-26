package FrameworkPack.Pages.PostsDirectory;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import FrameworkPack.Selenium.Driver;
import FrameworkPack.Utilities.ListPostPage;
import FrameworkPack.Utilities.PostType;

public class PostPageStateMenuSelector {

	public static void Select(By locator) {
		if (!ListPostPage.Assertions.IsAtPosts()) {
			ListPostPage.Operations.GoTo(PostType.Posts);
		}
		WebElement link = Driver.Instance.findElement(locator);
		link.click();

	}

}
