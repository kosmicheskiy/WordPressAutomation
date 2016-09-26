package WordPressJUnitTests.SmokeTest;

import org.junit.Assert;

import org.junit.Test;

import FrameworkPack.Pages.LoginDirectory.LoginPage;
import FrameworkPack.Pages.PostsDirectory.NewPostPage;
import FrameworkPack.Pages.PostsDirectory.PostPage;
import WordPressJUnitTests.Utilities.BaseAllTestsSettingsClass;
import Workflows.PostCreator;

public class CreatePostTest extends BaseAllTestsSettingsClass {

	@Test
	public void CanCreateBasicPostTest() {

		//NewPostPage.GoTo();
		PostCreator.CreatePost();
		//NewPostPage.CreatePost("New Post Title").WithBody("New Post Body").Publish();

		NewPostPage.GoToNewPost();

		Assert.assertTrue(PostPage.Title().equals(PostCreator.getPreviousTitle()));
	}

}
