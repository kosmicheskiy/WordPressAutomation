package LoginTests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import FrameworkPack.Pages.LoginDirectory.LoginPage;
import FrameworkPack.Selenium.Driver;
import FrameworkPack.Utilities.ElementsUtils;

@RunWith(DataProviderRunner.class)
public class FailedLoginUsingDataProviderTest {

	@Before
	public void Init() {
		Driver.Initialize();
		LoginPage.GoTo();

	}

	@DataProvider
	public static Object[][] data() {
		return new Object[][] { 
			{ "abc", "abc" }, 
			{ "cba", "abc" }, 
			};
	}

	@Test
	@UseDataProvider("data")
	public void FailLoginTest(final String login, final String password) {
		LoginPage.LoginAs(login).WithPassword(password).Login();
		Assert.assertTrue("Loged In", ElementsUtils.ElementIsPresent(By.id("login_error")));
	}

	@After
	public void CleanUp() {

		Driver.Close();
	}
}
