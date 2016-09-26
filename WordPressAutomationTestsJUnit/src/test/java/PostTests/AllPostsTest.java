package PostTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import FrameworkPack.Navigation.LeftNavigation;
import FrameworkPack.Pages.PostsDirectory.CategoriesPage;
import FrameworkPack.Pages.PostsDirectory.PostPageStateNavigation;
import FrameworkPack.Selenium.Driver;
import FrameworkPack.Utilities.CategoryType;
import FrameworkPack.Utilities.ElementsUtils;
import FrameworkPack.Utilities.ListCategoriesTags;
import FrameworkPack.Utilities.ListPostPage;
import FrameworkPack.Utilities.PostState;
import FrameworkPack.Utilities.PostType;
import WordPressJUnitTests.Utilities.BaseAllTestsSettingsClass;
import Workflows.CategoryTagCreator;
import Workflows.PostCreator;

public class AllPostsTest extends BaseAllTestsSettingsClass {
	
	
	ArrayList<String> postsTitles = new ArrayList<String>();

	// Click a checkbox by post name
	@Test
	public void CanSelectPostsCheckBoxByTitleTest() {
		// Go to post page and create a post
		ListPostPage.Operations.GoTo(PostType.Posts);
		PostCreator.CreatePost();
		// Checkbox is unchecked
		Assert.assertFalse(ListPostPage.Assertions.CheckBoxSelected(PostCreator.PreviousTitle));
		// Select checkbox
		ListPostPage.Operations.SelectPostsCheckbox(PostCreator.PreviousTitle);
		// Checkbox is checked
		Assert.assertTrue(ListPostPage.Assertions.CheckBoxSelected(PostCreator.PreviousTitle));
		// Checkbox unchecked after left the page
		Assert.assertFalse(ListPostPage.Assertions.CheckBoxSelected(PostCreator.PreviousTitle));
		// Trash the post
		ListPostPage.Operations.Trash(PostCreator.PreviousTitle);
	}

	// Can filter posts by category
	@Test
	public void CanFilterPostByCategoryTest() throws InterruptedException {
		
		ListPostPage.Operations.GoTo(PostType.Posts);
		PostCreator.CreatePost();
		ListCategoriesTags.Operations.GoTo(CategoryType.Category);
		CategoryTagCreator.CreateCategoryTag();
		PostCreator.CreatePostWithCategory();
		ListPostPage.Operations.StoreCount();
		ListPostPage.Operations.FilterByCategory(CategoryTagCreator.PreviousName);
		Assert.assertEquals("Were not filtered bu category", ListPostPage.Operations.previousPostCount() - 1,
				ListPostPage.Operations.CurrentPostCount());
		ListPostPage.Operations.GoTo(PostType.Posts);
		ListPostPage.Operations.SelectAllPosts();
		ListPostPage.Operations.MoveToBinApply();
		ListCategoriesTags.Operations.GoTo(CategoryType.Category);
		ListCategoriesTags.Operations.Trash(CategoryTagCreator.PreviousName);

	}

	// Can filter posts by tags
	@Test
	public void CanFilterPostByTagTest() {
		
		ListPostPage.Operations.GoTo(PostType.Posts);
		PostCreator.CreatePost();
		ListCategoriesTags.Operations.GoTo(CategoryType.Tag);
		CategoryTagCreator.CreateCategoryTag();
		
		ListPostPage.Operations.StoreCount();
		ListPostPage.Operations.FilterByCategory(CategoryTagCreator.PreviousName);
		Assert.assertEquals("Were not filtered bu category", ListPostPage.Operations.previousPostCount() - 1,
				ListPostPage.Operations.CurrentPostCount());
		ListPostPage.Operations.GoTo(PostType.Posts);
		ListPostPage.Operations.SelectAllPosts();
		ListPostPage.Operations.MoveToBinApply();
		ListCategoriesTags.Operations.GoTo(CategoryType.Tag);
		ListCategoriesTags.Operations.Trash(CategoryTagCreator.PreviousName);
			
	}

	// Can Add tag to post
	@Test
	public void CanAddTagToPostTest() {

		ListPostPage.Operations.GoTo(PostType.Posts);
		PostCreator.CreatePost();
		ListCategoriesTags.Operations.GoTo(CategoryType.Tag);
		CategoryTagCreator.CreateCategoryTag();			
		ListPostPage.Operations.AddTagToPost(PostCreator.PreviousTitle, CategoryTagCreator.PreviousName);
		Assert.assertTrue("Post has no test-tag", ListPostPage.Assertions.HasPostTag(PostCreator.PreviousTitle, CategoryTagCreator.PreviousName));
		ListPostPage.Operations.Trash(PostCreator.PreviousTitle);
		ListCategoriesTags.Operations.GoTo(CategoryType.Tag);
		//ListCategoriesTags.Trash(CategoryTagCreator.PreviousName);
		ListCategoriesTags.Operations.DeleteCategoryBySubmenuBtn(CategoryTagCreator.PreviousName);
	}

	// Can delete permanently post from bin
	@Test
	public void CanDeletePermanentlyPostFromBin() {

		ListPostPage.Operations.GoTo(PostType.Posts);
		PostCreator.CreatePost();
		ListPostPage.Operations.Trash(PostCreator.PreviousTitle);
		try {
			PostPageStateNavigation.Bin.Select();
		} catch (NoSuchElementException e) {
			fail("Bin link was not found");
		}
		ListPostPage.Operations.DeletePermanentlyPostFromBin(PostCreator.PreviousTitle);
		Assert.assertTrue("Post was deleted permanently from bin", ListPostPage.Assertions.IsPostDeletedpermanentlyFromBin());

	}

	// Can Add tag to post
	@Test
	public void CanMovePostToBin() {

		ListPostPage.Operations.GoTo(PostType.Posts);
		PostCreator.CreatePost();
		Assert.assertFalse("Draft link is present", ElementsUtils.ElementIsPresent(ListPostPage.binStateLink));
		ListPostPage.Operations.Trash(PostCreator.PreviousTitle);
		Assert.assertTrue("Draft link is present", ElementsUtils.ElementIsPresent(ListPostPage.binStateLink));
		try {
			PostPageStateNavigation.Bin.Select();
		} catch (NoSuchElementException e) {
			fail("Bin link was not found");
		}
		ListPostPage.Operations.RestorePostFromBin(PostCreator.PreviousTitle);
		Assert.assertTrue("Post was not restored from bin", ListPostPage.Assertions.IsPostRestoredFromBin());
		ListPostPage.Operations.GoTo(PostType.Posts);
		ListPostPage.Operations.Trash(PostCreator.PreviousTitle);
	}

	// Change post state from published to draft. assert "Draft" link shows up
	@Test
	public void DraftPostStateLinkShowsUp() {

		ListPostPage.Operations.GoTo(PostType.Posts);
		PostCreator.CreatePost();
		ListPostPage.Operations.GoTo(PostType.Posts);
		Assert.assertFalse("Draft link is present", ElementsUtils.ElementIsPresent(ListPostPage.draftPostStateLink));
		ListPostPage.Operations.ChangeStateTo(PostState.Draft, PostCreator.PreviousTitle);
		Driver.RefreshPage();
		try {
			PostPageStateNavigation.Draft.Select();
		} catch (NoSuchElementException e) {
			fail("Draft post state link was not found");
		}
		Assert.assertTrue("Is not in Draft state menu", ElementsUtils.ElementIsPresent(ListPostPage.draftPostStateLink));
		ListPostPage.Operations.ChangeStateTo(PostState.Published, PostCreator.PreviousTitle);
		ListPostPage.Operations.GoTo(PostType.Posts);
		ListPostPage.Operations.Trash(PostCreator.PreviousTitle);

	}

	// Change post state from published to pending review. assert "Pending" link
	// shows up
	@Test
	public void PendingReviewPostStateLinkShowsUp() {

		ListPostPage.Operations.GoTo(PostType.Posts);
		PostCreator.CreatePost();
		ListPostPage.Operations.GoTo(PostType.Posts);
		Assert.assertFalse("Pending link is present", ElementsUtils.ElementIsPresent(ListPostPage.pendingStateLink));
		ListPostPage.Operations.ChangeStateTo(PostState.PendingReview, PostCreator.PreviousTitle);
		Driver.RefreshPage();
		try {
			PostPageStateNavigation.Pending.Select();
		} catch (NoSuchElementException e) {
			fail("pending post state link was not found");
		}
		Assert.assertTrue("Is not in pending state menu", ElementsUtils.ElementIsPresent(ListPostPage.pendingStateLink));
		ListPostPage.Operations.ChangeStateTo(PostState.Published, PostCreator.PreviousTitle);
		ListPostPage.Operations.GoTo(PostType.Posts);
		ListPostPage.Operations.Trash(PostCreator.PreviousTitle);

	}

	// Change post state from Published to Draft
	@Test
	public void CanChangePostStateToDraftTest() {

		ListPostPage.Operations.GoTo(PostType.Posts);
		PostCreator.CreatePost();
		ListPostPage.Operations.ChangeStateTo(PostState.Draft, PostCreator.PreviousTitle);
		Assert.assertTrue("Is not in draft", ListPostPage.Assertions.PostIsInDraftStatus());
		ListPostPage.Operations.Trash(PostCreator.PreviousTitle);
	}

	// Change post state from Published to Pending Review
	@Test
	public void CanChangePostStateToPendingReviewTest() {

		ListPostPage.Operations.GoTo(PostType.Posts);
		PostCreator.CreatePost();
		ListPostPage.Operations.ChangeStateTo(PostState.PendingReview, PostCreator.PreviousTitle);
		Assert.assertTrue("Is not in pending review", ListPostPage.Assertions.PostIsInPendingReviewStatus());
		ListPostPage.Operations.Trash(PostCreator.PreviousTitle);
	}

	// Change post state from Pending Review to Published
	@Test
	public void CanChangePostStateToPublishedTest() {

		ListPostPage.Operations.GoTo(PostType.Posts);
		PostCreator.CreatePost();
		ListPostPage.Operations.ChangeStateTo(PostState.PendingReview, PostCreator.PreviousTitle);
		Assert.assertTrue("Is not in pending review", ListPostPage.Assertions.PostIsInPendingReviewStatus());
		ListPostPage.Operations.ChangeStateTo(PostState.Published, PostCreator.PreviousTitle);
		Assert.assertFalse("Is not in draft", ListPostPage.Assertions.PostIsInPendingReviewStatus());
		Assert.assertFalse("Is not in draft", ListPostPage.Assertions.PostIsInDraftStatus());
		ListPostPage.Operations.Trash(PostCreator.PreviousTitle);
	}

	// Select all checkboxes test
	@Test
	public void CanSelectAllPostsCheckBoxesTest() {
		// Go to post page and click select all checkbox
		ListPostPage.Operations.SelectAllPosts();
		Assert.assertTrue("Checkboxes are not checked", ListPostPage.Assertions.IsAllCheckboxesChecked());
	}

	// Can filter posts by date
	@Test
	public void CanFilterPostsByDateTest() {
		ListPostPage.Operations.StoreCount();
		ListPostPage.Operations.FilterbyDate();

		Assert.assertEquals("Posts were not filtered", ListPostPage.Operations.previousPostCount() - 3,
				ListPostPage.Operations.CurrentPostCount());
	}

	// Can view a post
	@Test
	public void CanViewAPostTest() {
		ListPostPage.Operations.GoTo(PostType.Posts);
		PostCreator.CreatePost();
		ListPostPage.Operations.ViewPost(PostCreator.PreviousTitle);

		Assert.assertTrue("Could not view a post", ListPostPage.Assertions.IsViewingAPost(PostCreator.PreviousTitle));
		Driver.MoveBack();
		ListPostPage.Operations.Trash(PostCreator.PreviousTitle);
	}

	// Delete post by name by checking checkbox, select dropdown "Move to bin"
	// option, click "Apply" button
	@Test
	public void TrashPostByChosingDropDownMenuMoveToBinOptionTest() {
		// Go to post page and create a post, store count
		ListPostPage.Operations.GoTo(PostType.Posts);
		PostCreator.CreatePost();
		ListPostPage.Operations.StoreCount();
		// Select checkbox
		ListPostPage.Operations.SelectPostsCheckbox(PostCreator.PreviousTitle);
		// Select delete option from dropdown list and click apply
		ListPostPage.Operations.MoveToBinApply();
		ListPostPage.Operations.GoTo(PostType.Posts);
		Assert.assertEquals("Could not delete post", ListPostPage.Operations.previousPostCount() - 1,
				ListPostPage.Operations.CurrentPostCount());
	}

	// Showing post edit options, by checking checkbox, select dropdown "Edit"
	// option, click "Apply" button
	@Test
	public void EditPostByChosingDropDownMenuEditOptionTest() {
		// Go to post page and create a post
		ListPostPage.Operations.GoTo(PostType.Posts);
		PostCreator.CreatePost();
		// Select checkbox
		ListPostPage.Operations.SelectPostsCheckbox(PostCreator.PreviousTitle);
		// Select edit option from dropdown list and click apply
		ListPostPage.Operations.EditApply();
		Assert.assertTrue("edit post options were not available",
				ElementsUtils.ElementIsPresent(ListPostPage.quickEditUpdateButton));

		ListPostPage.Operations.Trash(PostCreator.PreviousTitle);
	}

	// Check "Undo" link, to return post after moving to bin
	@Test
	public void UndoLinkWorksAfterMovingPostToBinTest() {
		// Go to post page and create a post
		ListPostPage.Operations.GoTo(PostType.Posts);
		// Add a new post
		PostCreator.CreatePost();
		// Go to post
		ListPostPage.Operations.GoTo(PostType.Posts);
		// Check for post
		Assert.assertTrue(ListPostPage.Assertions.DoesPostExistWithTitle(PostCreator.PreviousTitle));
		// Store count and trash the post, check the count
		ListPostPage.Operations.StoreCount();
		ListPostPage.Operations.Trash(PostCreator.PreviousTitle);
		Assert.assertEquals("Could not delete post", ListPostPage.Operations.previousPostCount() - 1,
				ListPostPage.Operations.CurrentPostCount());
		ListPostPage.Operations.StoreCount();
		ElementsUtils.ClickOnElement(ListPostPage.undoLinkAfterDropPost);
		Assert.assertEquals("Post was not restored", ListPostPage.Operations.previousPostCount() + 1,
				ListPostPage.Operations.CurrentPostCount());
		ListPostPage.Operations.Trash(PostCreator.PreviousTitle);
	}

	@Test
	// Can add a new post
	public void AddedPostShowUpTest() {
		ListPostPage.Operations.StoreCount();
		// Add a new post
		PostCreator.CreatePost();
		// Go to post, get new post count
		ListPostPage.Operations.GoTo(PostType.Posts);
		Assert.assertEquals("", ListPostPage.Operations.previousPostCount() + 1, ListPostPage.Operations.CurrentPostCount());
		// Check for post
		Assert.assertTrue(ListPostPage.Assertions.DoesPostExistWithTitle(PostCreator.PreviousTitle));
		// Trash post (clean up)
		ListPostPage.Operations.Trash(PostCreator.PreviousTitle);
		Assert.assertEquals("Could not delete post", ListPostPage.Operations.previousPostCount(), ListPostPage.Operations.CurrentPostCount());

	}

	@Test
	// Can search a post
	public void CanSearchPostsTest() {
		// Create a new post
		PostCreator.CreatePost();
		// Search for the post
		ListPostPage.Operations.SearchForPost(PostCreator.PreviousTitle);
		// Check that the post shows up in results
		Assert.assertTrue("Could not search a post", ListPostPage.Assertions.DoesPostExistWithTitle(PostCreator.PreviousTitle));
	}

	// Editing multiple posts
	@Test
	public void CanEditMultiplePostsTest() {
		postsTitles.clear();
		for (int i = 0; i < 3; i++) {
			PostCreator.CreatePost();
			postsTitles.add(PostCreator.PreviousTitle);
		}
		ListPostPage.Operations.SelectAllPosts();
		ListPostPage.Operations.EditApply();
		Assert.assertTrue("Is not in multiple post edit mode", ListPostPage.Assertions.IsInMultipleEditingMode(postsTitles));
		ListPostPage.Operations.SelectAllPosts();
		ListPostPage.Operations.MoveToBinApply();
	}

}
