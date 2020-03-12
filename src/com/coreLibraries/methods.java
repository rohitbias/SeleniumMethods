package com.coreLibraries;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class methods {
	

	public static WebDriver driver = null;
	
	public static WebDriver getDriver() {
		if (driver == null) {
			
			String os = System.getProperty("os.name").toLowerCase();
			if (os.contains("mac")) {
			String exePath = "./Resources/chromedriver";
			System.setProperty("webdriver.chrome.driver", exePath);
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			System.out.println("**Chrome driver initiated**");
			}
			else
			{
				String exePath = "./Resources/chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", exePath);
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				System.out.println("**Chrome driver initiated**");
			}
			return driver;
		} else {
			System.out.println("**Chrome driver already instantiated**");
			return driver;
		}
	}
	
	public static void openBrowser(String url)
	{
		getDriver().get(url);
	}
	
	public static void closeBrowser()
	{
		driver.quit();
	}
	}

