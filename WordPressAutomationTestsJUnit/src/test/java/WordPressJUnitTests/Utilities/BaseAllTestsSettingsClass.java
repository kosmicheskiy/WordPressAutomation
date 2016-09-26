package WordPressJUnitTests.Utilities;

import org.junit.After;
import org.junit.Before;

import FrameworkPack.Pages.LoginDirectory.LoginPage;
import FrameworkPack.Selenium.Driver;
import Workflows.PostCreator;

public class BaseAllTestsSettingsClass {
	@Before
	public void Init() {
		Driver.Initialize();				
		LoginPage.GoTo();
		LoginPage.LoginAs("root").WithPassword("root").Login();
	}

	@After
	public void CleanUp() {	
		
		Driver.Close();
	}

}
