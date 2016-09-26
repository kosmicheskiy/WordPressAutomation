package FrameworkPack.Pages.PostsDirectory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import FrameworkPack.Navigation.LeftNavigation;
import FrameworkPack.Selenium.Driver;

public class NewPostPage {

	public static void GoTo() {

		LeftNavigation.Posts.AddNew.Select();

	}
	
	public static CreatePostCommand CreatePostWithCategory(String title) {
		return new CreatePostCommand(title);
	}

	public static CreatePostCommand CreatePost(String title) {
		return new CreatePostCommand(title);
	}

	public static void GoToNewPost() {
		//"View post" message which show's up after publishing new post
		WebElement message = Driver.Instance.findElement(By.cssSelector("#message>p>a"));
		WebElement newPosLink = Driver.Instance.findElement(By.linkText("View post"));
		newPosLink.click();
	}

	public static boolean IsInEditMode() {
		WebElement editPageTitle = Driver.Instance.findElement(By.cssSelector(".wrap>h1"));
		return editPageTitle != null;

	}

	public static String Title() {
		WebElement postPageTitle = Driver.Instance.findElement(By.id("title"));
		if (postPageTitle.getText() != null) {
			return postPageTitle.getAttribute("value");
		}
		return "";
	}

}
