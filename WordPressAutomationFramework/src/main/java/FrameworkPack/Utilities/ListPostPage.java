package FrameworkPack.Utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import FrameworkPack.Navigation.LeftNavigation;
import FrameworkPack.Selenium.Driver;
import Workflows.PostCreator;

public class ListPostPage {

	public static By postState = By.className("post-state");
	public static By quickEditUpdateButton = By.id("bulk_edit");
	public static By undoLinkAfterDropPost = By.linkText("Undo");
	public static By draftPostStateLink = By.cssSelector(".draft>a");
	public static By pendingStateLink = By.cssSelector(".pending>a");
	public static By binStateLink = By.cssSelector(".trash>a");

	public static class Operations {
		
		private static int lastCount;
		private static String testTag = "Test-tag";
				
		private static int GetPostCount() {
			String countText = Driver.Instance.findElement(By.className("displaying-num")).getText();
			return Integer.parseInt(countText.split(" ")[0]);

		}

		public static int previousPostCount() {
			return lastCount;
		}

		public static int CurrentPostCount() {
			return GetPostCount();
		}

		public static void StoreCount() {
			if (!Assertions.IsAtPosts()) {
				ListPostPage.Operations.GoTo(PostType.Posts);
			}
			lastCount = GetPostCount();

		}

		public static void GoTo(PostType postType) {
			switch (postType) {
			case Page:
				LeftNavigation.Pages.AllPages.Select();
				break;
			case Posts:
				LeftNavigation.Posts.AllPosts.Select();
				break;
			default:
				break;
			}
		}

		public static void SelectPost(String title) {
			WebElement postLink = Driver.Instance.findElement(By.linkText(title));
			postLink.click();
		}
		
		public static void Trash(String title) {

			if (!Assertions.IsAtPosts()) {
				ListPostPage.Operations.GoTo(PostType.Posts);
			}

			List<WebElement> rows = null;
			rows = Driver.Instance.findElements(By.tagName("tr"));
			for (WebElement row : rows) {
				ArrayList<WebElement> links = (ArrayList<WebElement>) Driver.Instance.findElements(By.linkText(title));
				if (links.size() > 0) {
					Actions action = new Actions(Driver.Instance);
					action.moveToElement((WebElement) links.get(0));
					action.build().perform();
					WebDriverWait wait = new WebDriverWait(Driver.Instance, 10);
					WebElement binLink = wait
							.until(ExpectedConditions.visibilityOfElementLocated(By.className("submitdelete")));
					Actions action1 = new Actions(Driver.Instance);
					action1.moveToElement(binLink);
					action1.build().perform();
					binLink.click();
					return;
				}
			}
			PostCreator.CleanUp();
		}

		public static void SearchForPost(String searchString) {

			if (!Assertions.IsAtPosts()) {
				ListPostPage.Operations.GoTo(PostType.Posts);
			}

			WebElement searchField = Driver.Instance.findElement(By.id("post-search-input"));
			searchField.sendKeys(searchString);

			WebElement searchButton = Driver.Instance.findElement(By.id("search-submit"));
			searchButton.click();

		}	
		

		public static void SelectPostsCheckbox(String title) {

			if (!Assertions.IsAtPosts()) {
				ListPostPage.Operations.GoTo(PostType.Posts);
			}

			List<WebElement> rows = null;
			rows = Driver.Instance.findElements(By.tagName("tr"));
			for (WebElement row : rows) {
				ArrayList<WebElement> links = (ArrayList<WebElement>) row.findElements(By.linkText(title));
				if (links.size() > 0) {

					row.findElement(By.cssSelector("input")).click();
				}
			}
		}

		public static void MoveToBinApply() {

			List<WebElement> dropDownOptions = Driver.Instance
					.findElements(By.cssSelector("#bulk-action-selector-top option"));

			for (WebElement option : dropDownOptions) {
				if (option.getAttribute("value").equals("trash")) {
					option.click();
					break;
				}
			}

			WebElement applyBtn = Driver.Instance.findElement(By.id("doaction"));
			applyBtn.click();
		}

		public static void EditApply() {
			List<WebElement> dropDownOptions = Driver.Instance
					.findElements(By.cssSelector("#bulk-action-selector-top option"));

			for (WebElement option : dropDownOptions) {
				if (option.getAttribute("value").equals("edit")) {
					option.click();
					break;
				}
			}

			WebElement applyBtn = Driver.Instance.findElement(By.id("doaction"));
			applyBtn.click();

		}

		public static void SelectMultiplePosts() {

			if (!Assertions.IsAtPosts()) {
				ListPostPage.Operations.GoTo(PostType.Posts);
			}

			WebElement check1 = Driver.Instance.findElement(By.cssSelector("#post-35 input"));
			check1.click();

			WebElement check2 = Driver.Instance.findElement(By.cssSelector("#post-33 input"));
			check2.click();

			ListPostPage.Operations.EditApply();
		}
		
		public static void SelectAllPosts() {

			if (!Assertions.IsAtPosts()) {
				ListPostPage.Operations.GoTo(PostType.Posts);
			}

			WebElement selectAllCheckBox = Driver.Instance.findElement(By.cssSelector("#cb #cb-select-all-1"));
			selectAllCheckBox.click();
		}
		
		public static void RestorePostFromBin(String previousTitle) {
			WebElement post = Driver.Instance.findElement(By.cssSelector("td>strong"));
			Actions action = new Actions(Driver.Instance);
			action.moveToElement(post);
			action.build().perform();
			WebDriverWait wait = new WebDriverWait(Driver.Instance, 10);
			WebElement quickEditLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Restore")));
			Actions action1 = new Actions(Driver.Instance);
			action1.moveToElement(quickEditLink);
			action1.build().perform();
			quickEditLink.click();
		}
		
		public static void DeletePermanentlyPostFromBin(String previousTitle) {
			WebElement post = Driver.Instance.findElement(By.cssSelector("td>strong"));
			Actions action = new Actions(Driver.Instance);
			action.moveToElement(post);
			action.build().perform();
			WebDriverWait wait = new WebDriverWait(Driver.Instance, 10);
			WebElement quickEditLink = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Delete Permanently")));
			Actions action1 = new Actions(Driver.Instance);
			action1.moveToElement(quickEditLink);
			action1.build().perform();
			quickEditLink.click();
		}
		
		public static void ViewPost(String previousTitle) {
			if (!Assertions.IsAtPosts()) {
				ListPostPage.Operations.GoTo(PostType.Posts);
			}
			
			WebElement post = Driver.Instance.findElement(By.linkText(previousTitle));
			Actions action = new Actions(Driver.Instance);
			action.moveToElement(post);
			action.build().perform();
			WebDriverWait wait = new WebDriverWait(Driver.Instance, 10);
			WebElement quickEditLink = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("View")));
			Actions action1 = new Actions(Driver.Instance);
			action1.moveToElement(quickEditLink);
			action1.build().perform();
			quickEditLink.click();

		}
		
		public static void FilterByCategory(String previousName) {
			if (!Assertions.IsAtPosts()) {
				ListPostPage.Operations.GoTo(PostType.Posts);
			}
			
			WebElement category = Driver.Instance.findElement(By.linkText(previousName));
			if (category != null) {
				category.click();
			}		
		}
		
		public static void AddTagToPost(String previousTitle, String previousTagName) {
			if (!Assertions.IsAtPosts()) {
				ListPostPage.Operations.GoTo(PostType.Posts);
			}

			WebElement post = Driver.Instance.findElement(By.linkText(previousTitle));
			Actions action = new Actions(Driver.Instance);
			action.moveToElement(post);
			action.build().perform();
			WebDriverWait wait = new WebDriverWait(Driver.Instance, 10);
			WebElement quickEditLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Quick Edit")));
			Actions action1 = new Actions(Driver.Instance);
			action1.moveToElement(quickEditLink);
			action1.build().perform();
			quickEditLink.click();

			WebElement tagInput = Driver.Instance.findElement(By.cssSelector(".tax_input_post_tag"));
			tagInput.sendKeys(previousTagName);

			ElementsUtils.ClickOnElement(By.cssSelector(".button-primary.save.alignright"));
		}
		
		public static void FilterbyDate() {

			WebElement check1 = Driver.Instance.findElement(By.cssSelector("#post-35 input"));
			check1.click();
			WebElement check2 = Driver.Instance.findElement(By.cssSelector("#post-33 input"));
			check2.click();

			List<WebElement> dropDownOptions = Driver.Instance.findElements(By.cssSelector("#filter-by-date option"));

			for (WebElement option : dropDownOptions) {
				if (option.getAttribute("value").equals("201607")) {
					option.click();
					break;
				}
			}

			WebElement applyBtn = Driver.Instance.findElement(By.id("post-query-submit"));
			applyBtn.click();

		}

		public static void ChangeStateTo(PostState draft, String previousTitle) {
			if (!Assertions.IsAtPosts()) {
				ListPostPage.Operations.GoTo(PostType.Posts);
			}

			WebElement post = Driver.Instance.findElement(By.linkText(previousTitle));
			Actions action = new Actions(Driver.Instance);
			action.moveToElement(post);
			action.build().perform();
			WebDriverWait wait = new WebDriverWait(Driver.Instance, 10);
			WebElement quickEditLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Quick Edit")));
			Actions action1 = new Actions(Driver.Instance);
			action1.moveToElement(quickEditLink);
			action1.build().perform();
			quickEditLink.click();

			List<WebElement> postStateOptions = Driver.Instance
					.findElements(By.cssSelector(".inline-edit-status.alignleft>select option"));

			if (draft == PostState.Draft) {
				for (WebElement option : postStateOptions) {
					if (option.getAttribute("value").equals("draft")) {
						option.click();
						break;
					}
				}
			}

			if (draft == PostState.PendingReview) {
				for (WebElement option : postStateOptions) {
					if (option.getAttribute("value").equals("pending")) {
						option.click();
						break;
					}
				}
			}

			if (draft == PostState.Published) {
				for (WebElement option : postStateOptions) {
					if (option.getAttribute("value").equals("publish")) {
						option.click();
						break;
					}
				}
			}

			ElementsUtils.ClickOnElement(By.cssSelector(".button-primary.save.alignright"));

		}
	}
	
	
	
	
	
	
	
	
	
	public static class Assertions {
		public static boolean DoesPostExistWithTitle(String title) {

			if (!Assertions.IsAtPosts()) {
				ListPostPage.Operations.GoTo(PostType.Posts);
			}

			try {
				WebElement pageTitle = Driver.Instance.findElement(By.linkText(title));
				if (pageTitle != null) {
					return true;
				}
			} catch (NoSuchElementException ex) {
				return false;
			}
			return false;

		}
		
		public static boolean CheckBoxSelected(String title) {

			if (!Assertions.IsAtPosts()) {
				ListPostPage.Operations.GoTo(PostType.Posts);
			}

			List<WebElement> rows = null;
			rows = Driver.Instance.findElements(By.cssSelector("tbody tr"));
			for (WebElement row : rows) {
				ArrayList<WebElement> links = (ArrayList<WebElement>) row.findElements(By.linkText(title));
				if (links.size() > 0) {
					return row.findElement(By.cssSelector("input")).isSelected();
				}
			}
			return false;
		}
		
		public static boolean IsAtPosts() {
			WebElement elem = Driver.Instance.findElement(By.cssSelector(".wrap>h1"));

			if (elem.getText().equals("Posts"))
				return true;

			return false;
		}

		private static boolean IsAtPages() {
			WebElement elem = Driver.Instance.findElement(By.cssSelector(".wrap>h1"));

			if (elem.getText().equals("Pages"))
				return true;

			return false;
		}
		
		public static boolean IsInMultipleEditingMode(ArrayList<String> postsTitles) {
			if (postsTitles == null) {
				return false;
			}
			List<WebElement> postsInEditing = Driver.Instance.findElements(By.cssSelector("#bulk-titles div "));

			return (postsInEditing.size() == postsTitles.size());
		}
		
		public static boolean IsAllCheckboxesChecked() {
			int countCheckBoxes = 0;
			List<WebElement> listCheckBoxes = Driver.Instance
					.findElements(By.cssSelector("tbody tr th input[type='checkbox']"));

			for (WebElement option : listCheckBoxes) {
				if (option.isSelected()) {
					countCheckBoxes++;
				}
			}

			return (countCheckBoxes == listCheckBoxes.size());
		}
		
		

				
		public static boolean PostIsInDraftStatus() {
			WebElement postStatus = Driver.Instance.findElement(By.className("post-state"));
			if (postStatus == null) {
				return false;
			}
			if (postStatus.getText().equals("Draft")) {
				return true;
			}
			return false;
		}

		public static boolean PostIsInPendingReviewStatus() {
			WebElement postStatus = Driver.Instance.findElement(By.className("post-state"));
			if (postStatus == null) {
				return false;
			}
			if (postStatus.getText().equals("Pending")) {
				return true;
			}
			return false;
		}

		public static boolean HasPostTag(String previousTitle, String previousTagName) {

			List<WebElement> rows = Driver.Instance.findElements(By.cssSelector("tbody tr"));
			for (WebElement row : rows) {
				WebElement post = Driver.Instance.findElement(By.linkText(previousTitle));
				if (post != null) {				
					WebElement tag = Driver.Instance.findElement(By.cssSelector(".tags.column-tags>a"));
					if (tag.getText().equals(previousTagName))					
						return true;
				}
			}
			return false;
		}
		
		public static boolean IsPostRestoredFromBin() {
			WebElement restoredText = Driver.Instance.findElement(By.cssSelector("#message>p"));
			if (restoredText == null) {
				return false;
			}
			if (restoredText.getText().equals("1 post restored from the Bin.")) {
				return true;
			}
			return false;
		}
		
		public static boolean IsPostDeletedpermanentlyFromBin() {
			WebElement deletedText = Driver.Instance.findElement(By.cssSelector("#message>p"));
			if (deletedText == null) {
				return false;
			}
			if (deletedText.getText().equals("1 post permanently deleted.")) {
				return true;
			}
			return false;
		}

		public static boolean IsViewingAPost(String previousTitle) {		
			if (previousTitle == null) {
				return false;
			}
			WebElement title = Driver.Instance.findElement(By.className("entry-title"));
			if (title == null) {
				return false;
			}
			if (title.getText().equals(previousTitle)){
				return true;
			}
			return false;
		}

	}
	
	


	

	

	

	

	
	

	

	
	

	

	

	

	

	

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
