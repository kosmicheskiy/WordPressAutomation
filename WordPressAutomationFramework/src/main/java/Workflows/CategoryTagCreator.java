package Workflows;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import FrameworkPack.Pages.PostsDirectory.CategoriesPage;
import FrameworkPack.Pages.PostsDirectory.NewPostPage;
import FrameworkPack.Selenium.Driver;
import FrameworkPack.Utilities.ListCategoriesTags;
import FrameworkPack.Utilities.ListPostPage;

public class CategoryTagCreator {

	public static String PreviousName;
	public static String PreviousSlug;
	public static String PreviousDescription;
	
	public static String PrevioustTagName;

	public static void CreateCategoryTag() {			
		
		Initialize();

		PreviousName = CreateName();
		PreviousSlug = CreateSlug();
		PreviousDescription = CreateDescription();

		CategoriesPage.CreateCategory(PreviousName).WithSlug(PreviousSlug).WithDescription(PreviousDescription)
				.Publish();	
		
	}

	private static String CreateName() {

		return (CreateRandomText() + "name");
	}

	private static String CreateSlug() {

		return (CreateRandomText() + "slug");
	}

	private static String CreateDescription() {

		return (CreateRandomText() + "description");
	}

	private static String CreateRandomText() {

		StringBuilder s = new StringBuilder();

		String[] Name = { "sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday"};
		String[] Slug = { "the", "a", "an", "of", "to", "it", "as" };
		String[] Description = { "january", "february", "march", "april", "may", "june", "july", "august", "september",
				"october", "november", "december" };

		for (int i = 0; i < Math.random() * 3; i++) {
			int idx = new Random().nextInt(Name.length);
			String random = (Name[idx]);
			s.append(random);
			s.append(" ");

			int idx1 = new Random().nextInt(Slug.length);
			String random1 = (Slug[idx1]);
			s.append(random1);
			s.append(" ");
		}
		return s.toString();

	}

	public static void Initialize() {
		PreviousName = null;
		PreviousSlug = null;
		PreviousDescription = null;
	}

	public static void CleanUp() {
		if (CreatedCategoryTag()) {
			TrashCategoryTag();
		}
	}

	private static void TrashCategoryTag() {
		ListCategoriesTags.Operations.DeleteCategoryBySubmenuBtn(PreviousName);
		Initialize();

	}

	private static boolean CreatedCategoryTag() {

		return !(PreviousName != null && PreviousName.isEmpty());

	}

	public static void SearchForCategoryTag(String searchString) {

		WebElement searchField = Driver.Instance.findElement(By.id("tag-search-input"));
		searchField.sendKeys(searchString);

		WebElement searchButton = Driver.Instance.findElement(By.id("search-submit"));
		searchButton.click();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
