package FrameworkPack.Pages.PostsDirectory;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;

import FrameworkPack.Selenium.Driver;
import Workflows.CategoryTagCreator;

public class CreatePostCommand {

	private String title;
	private String body;
	private String category = CategoryTagCreator.PreviousName;

	public CreatePostCommand(String title) {
		this.title = title;
	}

	public CreatePostCommand WithBody(String body) {
		this.body = body;
		return this;
	}

	public void Publish() {

		try {
			WebElement titleInput = Driver.Instance.findElement(By.id("title"));
			titleInput.sendKeys(title);

			WebElement bodyInput = Driver.Instance.findElement(By.id("content"));
			bodyInput.sendKeys(body);
		} catch (UnhandledAlertException e) {
			Alert alert = Driver.Instance.switchTo().alert();
			alert.dismiss();
		} finally {
			WebElement publishBtn = Driver.Instance.findElement(By.id("publish"));
			publishBtn.click();
		}

	}

	public void PublishWithCategory() throws InterruptedException {
		try {
			WebElement titleInput = Driver.Instance.findElement(By.id("title"));
			titleInput.sendKeys(title);

			WebElement bodyInput = Driver.Instance.findElement(By.id("content"));
			bodyInput.sendKeys(body);

			List<WebElement> testCategoryCheckBox = Driver.Instance
					.findElements(By.cssSelector("#categorychecklist label"));
			for (WebElement item : testCategoryCheckBox) {
				if (item.getText().equals(category)) {
					item.click();
					Driver.Sleep(2);
					break;
				}
			}
		} catch (UnhandledAlertException e) {
			Alert alert = Driver.Instance.switchTo().alert();
			alert.dismiss();
		} finally {
			WebElement publishBtn = Driver.Instance.findElement(By.id("publish"));
			publishBtn.click();
		}

	}

}
