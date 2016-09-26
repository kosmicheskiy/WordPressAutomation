package FrameworkPack.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import FrameworkPack.Navigation.LeftNavigation;
import FrameworkPack.Selenium.Driver;
import Workflows.CategoryTagCreator;

public class ListCategoriesTags {

	public static class Operations {
		private static int lastCount;

		private static int GetCategoryTagCount() {
			String countText = Driver.Instance.findElement(By.className("displaying-num")).getText();
			return Integer.parseInt(countText.split(" ")[0]);

		}

		public static int previousCategoryTagCount() {
			return lastCount;
		}

		public static int CurrentCategoryTagCount() {
			return GetCategoryTagCount();
		}

		public static void StoreCount() {
			lastCount = GetCategoryTagCount();

		}

		public static void GoTo(CategoryType categoryType) {
			switch (categoryType) {
			case Category:
				LeftNavigation.Posts.Categories.Select();
				break;
			case Tag:
				LeftNavigation.Posts.Tags.Select();
				break;
			default:
				break;
			}
		}

		public static void SelectCategoryTag(String title) {
			WebElement categoryTagLink = Driver.Instance.findElement(By.linkText(title));
			categoryTagLink.click();
		}

		// Refactor exeption no such element exeption "tr>th>input"
		public static void DeleteAllCategories(String title) {

			List<WebElement> rows = null;
			rows = Driver.Instance.findElements(By.tagName("tr"));
			for (int i = 1; i < rows.size() - 1; i++) {

				boolean present;
				try {
					WebElement checkBox = Driver.Instance.findElement(By.cssSelector("tr>th>input"));
					// driver.findElement(By.id("logoutLink"));
					present = true;
					if (present) {
						checkBox.click();

						WebElement select = Driver.Instance.findElement(By.id("bulk-action-selector-top"));
						List<WebElement> options = select.findElements(By.tagName("option"));

						for (WebElement option : options) {

							if ("Delete".equals(option.getText().trim()))

								option.click();
						}

						WebElement applyBtn = Driver.Instance.findElement(By.id("doaction"));
						applyBtn.click();
					} else {
						break;
					}
				} catch (NoSuchElementException e) {
					present = false;
					break;
				}

				/*
				 * WebElement checkBox =
				 * Driver.Instance.findElement(By.cssSelector("tr>th>input"));
				 * if (checkBox != null) { checkBox.click();
				 * 
				 * WebElement select =
				 * Driver.Instance.findElement(By.id("bulk-action-selector-top")
				 * ); List<WebElement> options =
				 * select.findElements(By.tagName("option"));
				 * 
				 * for (WebElement option : options) {
				 * 
				 * if("Delete".equals(option.getText().trim()))
				 * 
				 * option.click(); }
				 * 
				 * WebElement applyBtn =
				 * Driver.Instance.findElement(By.id("doaction"));
				 * applyBtn.click(); } else { break; }
				 */

				/*
				 * ArrayList<WebElement> links = (ArrayList<WebElement>)
				 * Driver.Instance.findElements(By.linkText(title)); if
				 * (links.size() > 0) { Actions action = new
				 * Actions(Driver.Instance); System.out.println(links.get(0));
				 * action.moveToElement((WebElement) links.get(0));
				 * action.build().perform(); WebDriverWait wait = new
				 * WebDriverWait(Driver.Instance, 10); WebElement deleteLink =
				 * wait.until(
				 * ExpectedConditions.visibilityOfElementLocated(By.className(
				 * "delete-tag.aria-button-if-js"))); Actions action1 = new
				 * Actions(Driver.Instance); action1.moveToElement(deleteLink);
				 * action1.build().perform(); deleteLink.click(); return;
				 */
			}
		}

		public static void SearchForCategoryTag(String searchString) {

			WebElement searchField = Driver.Instance.findElement(By.id("tag-search-input"));
			searchField.sendKeys(searchString);

			WebElement searchButton = Driver.Instance.findElement(By.id("search-submit"));
			searchButton.click();

		}

		public static void GoToEditModeBySubmenuBtn(String previousName) {

			WebElement previousNameLink = Driver.Instance.findElement(By.linkText(previousName));

			Actions action = new Actions(Driver.Instance);
			action.moveToElement(previousNameLink).build().perform();

			WebDriverWait wait = new WebDriverWait(Driver.Instance, 10);
			WebElement editSubmenuLink = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".edit>a")));

			editSubmenuLink.click();

		}

		public static void GoToEditMode(String previousName) {

			WebElement categoryNameLink = Driver.Instance.findElement(By.linkText(previousName));
			categoryNameLink.click();
		}

		public static void GoToQucikEditModeBySubmenuBtn(String previousName) {

			WebElement previousNameLink = Driver.Instance.findElement(By.linkText(previousName));

			Actions action = new Actions(Driver.Instance);
			action.moveToElement(previousNameLink).build().perform();

			WebDriverWait wait = new WebDriverWait(Driver.Instance, 10);
			WebElement editSubmenuLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quick Edit")));

			editSubmenuLink.click();

		}

		public static void GoToViewModeBySubmenuBtn(String previousName) {

			WebElement previousNameLink = Driver.Instance.findElement(By.linkText(previousName));

			Actions action = new Actions(Driver.Instance);
			action.moveToElement(previousNameLink).build().perform();

			WebDriverWait wait = new WebDriverWait(Driver.Instance, 10);
			WebElement editSubmenuLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("View")));

			editSubmenuLink.click();

		}

		public static void DeleteCategoryBySubmenuBtn(String previousName) {

			WebElement previousNameLink = Driver.Instance.findElement(By.linkText(previousName));
			WebDriverWait wait = new WebDriverWait(Driver.Instance, 10);
			Actions action = new Actions(Driver.Instance);
			action.moveToElement(previousNameLink).build().perform();

			WebElement editSubmenuLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Delete")));

			editSubmenuLink.click();
			Alert alert = Driver.Instance.switchTo().alert();
			alert.accept();
			CategoryTagCreator.CleanUp();

		}

		public static void SelectAllCategories() {
			WebDriverWait wait = new WebDriverWait(Driver.Instance, 10);
			WebElement selectAllCheckBox = wait
					.until(ExpectedConditions.elementToBeClickable(By.id("cb-select-all-1")));
			if (!selectAllCheckBox.isSelected()) {
				selectAllCheckBox.click();
			}
		}

		public static void Trash(String previousName) {

			WebElement post = Driver.Instance.findElement(By.linkText(previousName));
			Actions action = new Actions(Driver.Instance);
			action.moveToElement(post);
			action.build().perform();
			WebDriverWait wait = new WebDriverWait(Driver.Instance, 10);
			WebElement deleteLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Delete")));
			Actions action1 = new Actions(Driver.Instance);
			action1.moveToElement(deleteLink);
			action1.build().perform();
			deleteLink.click();
			Alert alert = Driver.Instance.switchTo().alert();
			alert.accept();

		}
	}

	////////////////

	public static class Assertions {

		public static boolean DoesCategoryTagExistWithTitle(String title) {
			WebElement categoryTagTitle = Driver.Instance.findElement(By.linkText(title));
			if (categoryTagTitle != null) {
				return true;
			}
			return false;
		}

		public static boolean IsAtCategories() {

			WebElement element = Driver.Instance.findElement(By.cssSelector(".wrap.nosubsub>h1"));

			if (element.getText().equals("Categories"))
				return true;

			return false;
		}

		public static boolean IsAtTags() {

			WebElement elem = Driver.Instance.findElement(By.cssSelector(".wrap.nosubsub>h1"));

			if (elem.getText().equals("Tags"))
				return true;

			return false;
		}

		public static boolean IsInEditMode() {

			WebElement editModeHeader = Driver.Instance.findElement(By.cssSelector(".wrap>h1"));
			if (editModeHeader.getText().equals("Edit Category")) {
				return true;
			}

			return false;
		}

		public static boolean IsInQuickEditMode() {

			WebElement quickEditModeHeader = Driver.Instance.findElement(By.cssSelector(".inline-edit-legend"));
			if (quickEditModeHeader.getText().equals("Quick Edit")) {
				return true;
			}

			return false;
		}

		public static boolean IsInViewMode(String previousName) {

			WebElement categoryPreviousName = Driver.Instance.findElement(By.cssSelector("#page-title>span"));
			if (categoryPreviousName.getText().equals(previousName)) {
				return true;
			}
			return false;
		}

		@SuppressWarnings("null")
		public static boolean IsAllCheckboxesChecked() {

			int totalCheckBoxes = 0;
			int totalCheckBoxesChecked = 0;
			List<WebElement> inputs = Driver.Instance.findElements(By.cssSelector("tbody tr input"));
			List<WebElement> checkBoxes = null;
			for (int i = 0; i < inputs.size(); i++) {

				if (inputs.get(i).getAttribute("type") != null
						&& inputs.get(i).getAttribute("type").equals("checkbox")) {
					if (inputs.get(i) == null) {
						continue;
					}
					checkBoxes.add(inputs.get(i));
				}
			}
			totalCheckBoxes = checkBoxes.size();
			for (WebElement checkBox : checkBoxes) {
				if (checkBox.isSelected()) {
					totalCheckBoxesChecked++;
				}
			}

			return (totalCheckBoxes == totalCheckBoxesChecked);
		}

	}

}
