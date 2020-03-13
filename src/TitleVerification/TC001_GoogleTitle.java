package TitleVerification;
import java.net.MalformedURLException;

import org.testng.annotations.*;
import org.testng.annotations.Test;

import com.SeleniumMethods.CustomLibraries;
import com.SeleniumMethods.ObjectRepository;


public class TC001_GoogleTitle extends CustomLibraries implements ObjectRepository {
	
	@BeforeTest
	public void start() throws MalformedURLException
	{
	setup();	
	}
	@Test
	public void VerifyingGoogleTitle()
	{
		verifyTitle("Google");
		
	}
	@AfterTest
	public void endTest()
	{
	closeBrowser();	
	}
	

}
