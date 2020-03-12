package com.coreLibraries;

public class FirstTestCase extends methods{
	public static void main(String args[])
	{
		openBrowser("https://www.facebook.com");
		System.out.println(getDriver().getTitle());
		closeBrowser();
		
	}

}
