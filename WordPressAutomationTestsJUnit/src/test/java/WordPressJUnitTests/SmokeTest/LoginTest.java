package WordPressJUnitTests.SmokeTest;

import org.junit.Assert;

import org.junit.Test;

import FrameworkPack.Pages.DashboardDirectory.DashboardPage;
import FrameworkPack.Pages.LoginDirectory.LoginPage;
import WordPressJUnitTests.Utilities.BaseAllTestsSettingsClass;

public class LoginTest extends BaseAllTestsSettingsClass {

	@Test
	public void RootUserCanLoginTest() {

		Assert.assertTrue("Failed to LogIn", DashboardPage.isAt());
	}

}
