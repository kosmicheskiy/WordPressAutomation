package FrameworkPack.Selenium;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {
	
	public static WebDriver Instance;
	
	public static String BaseAdress() {
		return "http://localhost/wordpress/";
	}

	public static WebDriver getInstace() {
		return Instance;
	}

	public static void WebDriver (WebDriver instace) {
		Instance = instace;
	}
	
	public static void Initialize() {
		File resoursesDiv = new File("src/test/resources/ChromeDriver");
		
		File chromeDriver = new File (resoursesDiv, "chromedriver.exe");
		
		System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
		
		Instance = new ChromeDriver();
		TurnOnWait();
	}

	public static void Close() {
		Instance.close();		
	}
	
	public static void TurnOnWait() {
		Instance.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);		
	}
	
	public static void TurnOffWait() {
		Instance.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);		
	}
	
	public static void RefreshPage() {
		Instance.navigate().refresh();	
	}

	public static void MoveBack() {
		Instance.navigate().back();		
	}
	
	public static void MoveForward() {
		Instance.navigate().forward();	
	}

	public static void Sleep(int i) throws InterruptedException {
		Thread.sleep(i * 1000);		
	}
	

}

	

