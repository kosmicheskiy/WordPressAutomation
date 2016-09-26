package WordPressJUnitTests.SmokeTest;

import org.junit.Assert;

import org.junit.Test;

import FrameworkPack.Pages.LoginDirectory.LoginPage;
import FrameworkPack.Pages.PostsDirectory.NewPostPage;
import FrameworkPack.Utilities.ListPostPage;
import FrameworkPack.Utilities.PostType;
import WordPressJUnitTests.Utilities.BaseAllTestsSettingsClass;

public class PageTest extends BaseAllTestsSettingsClass {

	@Test
	public void CanEditAPageTest() {

		ListPostPage.Operations.GoTo(PostType.Page);
		ListPostPage.Operations.SelectPost("Test Page");

		Assert.assertTrue(NewPostPage.IsInEditMode());
		Assert.assertTrue(NewPostPage.Title().equals("Test Page"));
	}

}
