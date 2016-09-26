package PostTests;

import org.junit.Assert;
import org.junit.Test;

import FrameworkPack.Selenium.Driver;
import FrameworkPack.Utilities.CategoryType;
import FrameworkPack.Utilities.ListCategoriesTags;
import FrameworkPack.Utilities.ListPostPage;
import FrameworkPack.Utilities.PostType;
import WordPressJUnitTests.Utilities.BaseAllTestsSettingsClass;
import Workflows.CategoryTagCreator;
import Workflows.PostCreator;

public class AllCategoriesTest extends BaseAllTestsSettingsClass {

	// Ensure default category with name "Uncategorised" exists
	@Test
	public void DefaulCategoryExistTest() {
		// Go to posts categories page
		ListCategoriesTags.Operations.GoTo(CategoryType.Category);

		Assert.assertTrue(ListCategoriesTags.Assertions.DoesCategoryTagExistWithTitle("Uncategorised"));
	}

	// Add a category, assert it is showing up in list
	@Test
	public void AddeCategoryShowUpTest() {
		// Go to categories, get post count, store
		ListCategoriesTags.Operations.GoTo(CategoryType.Category);
		ListCategoriesTags.Operations.StoreCount();
		// Add a new Category
		CategoryTagCreator.CreateCategoryTag();
		// Go to post, get new post count
		Driver.RefreshPage();
		Assert.assertEquals("", ListCategoriesTags.Operations.previousCategoryTagCount() + 1,
				ListCategoriesTags.Operations.CurrentCategoryTagCount());

		// Check for category
		Assert.assertTrue(ListCategoriesTags.Assertions.DoesCategoryTagExistWithTitle(CategoryTagCreator.PreviousName));
		// Trash category (clean up)
		ListCategoriesTags.Operations.DeleteAllCategories(CategoryTagCreator.PreviousName);

		Driver.RefreshPage();
		Assert.assertEquals("Could not delete post", ListCategoriesTags.Operations.previousCategoryTagCount(),
				ListCategoriesTags.Operations.CurrentCategoryTagCount());

	}

	// Create category, assert searching works
	@Test
	public void CanSearchCategoryTest() {
		ListCategoriesTags.Operations.GoTo(CategoryType.Category);
		// Add a new Category
		CategoryTagCreator.CreateCategoryTag();
		// Search for category
		CategoryTagCreator.SearchForCategoryTag(CategoryTagCreator.PreviousName);
		// Assert category was finded
		Assert.assertTrue(ListCategoriesTags.Assertions.DoesCategoryTagExistWithTitle(CategoryTagCreator.PreviousName));
		// Clean up
		ListCategoriesTags.Operations.DeleteAllCategories(CategoryTagCreator.PreviousName);
	}

	// Assert redirecting to edit mode? if click on category name link
	@Test
	public void CanEditCategoryByClickingItsNameTest() {
		ListCategoriesTags.Operations.GoTo(CategoryType.Category);
		// Add a new Category
		CategoryTagCreator.CreateCategoryTag();
		// Assert redirecting to edit mode
		ListCategoriesTags.Operations.GoToEditMode(CategoryTagCreator.PreviousName);
		Assert.assertTrue("Cant find category", ListCategoriesTags.Assertions.IsInEditMode());
		ListCategoriesTags.Operations.GoTo(CategoryType.Category);
		ListCategoriesTags.Operations.DeleteAllCategories(CategoryTagCreator.PreviousName);
	}

	// Assert redirecting to edit mode if click on edit submenu button
	@Test
	public void CanEditCategoryByClickingEditSubmenuButtonTest() {
		ListCategoriesTags.Operations.GoTo(CategoryType.Category);
		// Add a new Category
		CategoryTagCreator.CreateCategoryTag();
		// Click "Edit" submenu button Assert redirecting to edit mode
		ListCategoriesTags.Operations.GoToEditModeBySubmenuBtn(CategoryTagCreator.PreviousName);

		Assert.assertTrue("Cant find category", ListCategoriesTags.Assertions.IsInEditMode());
		ListCategoriesTags.Operations.GoTo(CategoryType.Category);
		ListCategoriesTags.Operations.DeleteAllCategories(CategoryTagCreator.PreviousName);
	}

	// Assert redirecting to quick edit mode if click on qucik edit submenu
	// button
	@Test
	public void CanQuickEditCategoryByClickingQuickEditSubmenuButtonTest() {
		ListCategoriesTags.Operations.GoTo(CategoryType.Category);
		// Add a new Category
		CategoryTagCreator.CreateCategoryTag();
		// Click "Edit" submenu button Assert redirecting to edit mode
		ListCategoriesTags.Operations.GoToQucikEditModeBySubmenuBtn(CategoryTagCreator.PreviousName);

		Assert.assertTrue("Cant find category", ListCategoriesTags.Assertions.IsInQuickEditMode());
		ListCategoriesTags.Operations.GoTo(CategoryType.Category);
		ListCategoriesTags.Operations.DeleteAllCategories(CategoryTagCreator.PreviousName);
	}

	// Assert redirecting to view mode if click on view submenu button
	@Test
	public void CanViewCategoryByClickingViewSubmenuButtonTest() {
		ListCategoriesTags.Operations.GoTo(CategoryType.Category);
		// Add a new Category
		CategoryTagCreator.CreateCategoryTag();
		// Click "Edit" submenu button Assert redirecting to edit mode
		ListCategoriesTags.Operations.GoToViewModeBySubmenuBtn(CategoryTagCreator.PreviousName);

		Assert.assertTrue("Cant find category", ListCategoriesTags.Assertions.IsInViewMode(CategoryTagCreator.PreviousName));
		Driver.MoveBack();
		ListCategoriesTags.Operations.GoTo(CategoryType.Category);
		ListCategoriesTags.Operations.DeleteAllCategories(CategoryTagCreator.PreviousName);
	}

	// Assert deleting category if click on delete submenu button
	@Test
	public void CanDeleteCategoryByClickingDeleteSubmenuButtonTest() {
		ListCategoriesTags.Operations.GoTo(CategoryType.Category);
		ListCategoriesTags.Operations.StoreCount();
		// Add a new Category
		CategoryTagCreator.CreateCategoryTag();
		Driver.RefreshPage();
		Assert.assertEquals("Category was not created", ListCategoriesTags.Operations.previousCategoryTagCount() + 1,
				ListCategoriesTags.Operations.CurrentCategoryTagCount());

		ListCategoriesTags.Operations.DeleteCategoryBySubmenuBtn(CategoryTagCreator.PreviousName);

		Driver.RefreshPage();
		Assert.assertEquals("Could not delete categoty", ListCategoriesTags.Operations.previousCategoryTagCount(),
				ListCategoriesTags.Operations.CurrentCategoryTagCount());

	}

	// Assert checkbox select all works
	@Test
	public void SelectAllCategoriesCheckboxTest() throws InterruptedException {
		ListCategoriesTags.Operations.GoTo(CategoryType.Category);
		// Add a new Category
		CategoryTagCreator.CreateCategoryTag();
		ListCategoriesTags.Operations.GoTo(CategoryType.Category);
		CategoryTagCreator.CreateCategoryTag();
		ListCategoriesTags.Operations.GoTo(CategoryType.Category);
		CategoryTagCreator.CreateCategoryTag();
		// Select all checkboxes by clicking "Select All" checkbox
		Driver.Sleep(5);
		ListCategoriesTags.Operations.SelectAllCategories();

		Assert.assertTrue("Checkboxes are not checked", ListCategoriesTags.Assertions.IsAllCheckboxesChecked());

		ListCategoriesTags.Operations.DeleteAllCategories(CategoryTagCreator.PreviousName);

	}

	
	
	
	
	
	
	
	
	
	
	
	
	

}
