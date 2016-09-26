package FrameworkPack.Pages.PostsDirectory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import FrameworkPack.Selenium.Driver;

public class CreateTagCommand {
	private static String name;
	private static String slug;	
	private static String description;
	
	public CreateTagCommand(String name) {
		this.name = name;
	}
	
	public CreateTagCommand WithSlug(String slug){
		this.slug = slug;
		return this;
	}
	
	public CreateTagCommand WithDescription(String description){
		this.description = description;
		return this;
	}
	
	public void Publish() {
		WebElement nameInput =  Driver.Instance.findElement(By.id("tag-name"));	
		nameInput.sendKeys(name);
		
		WebElement slugInput =  Driver.Instance.findElement(By.id("tag-slug"));
		slugInput.sendKeys(slug);
		
		WebElement descriptionInput =  Driver.Instance.findElement(By.id("tag-description"));
		descriptionInput.sendKeys(description);
		
		WebElement addNewCategoryBtn =  Driver.Instance.findElement(By.id("submit"));
		addNewCategoryBtn.click();
		
	}
}
