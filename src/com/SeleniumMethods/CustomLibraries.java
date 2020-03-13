package com.SeleniumMethods;

import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.Color;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class CustomLibraries  {
	
	public static WebDriver driver=null;
	static String PARENT_FOLDER="./Resources/";
	public static Properties prop ;
	protected static ThreadLocal<RemoteWebDriver> threadDriver = null;  
	
	
	  
	public CustomLibraries()
	{
	File file = new File("./Resources/config.properties");
	FileInputStream fileInput = null;
	try {
	fileInput = new FileInputStream(file);
	} catch (FileNotFoundException e) {
		System.out.println("Unable to find Config File.Please create config file in below path- \"./Resources/config.properties\" ");
	}
	prop = new Properties();
	//load properties file
	try {
		prop.load(fileInput);
	} catch (IOException e) {
		e.printStackTrace();
	}
	}
	public void loadXMLData(String testDataFile)
	   {
	    String key = "";
	    String value = "";
	     try
	   {
	   System.out.println("------------- Caching Test Data! ------------");
	   DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	   DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
	   Document document = docBuilder.parse(new File(testDataFile));
	   NodeList nodeList = document.getElementsByTagName("*");
	   for (int i = 0; i < nodeList.getLength(); i++)
	   {
	   Node node = nodeList.item(i);
	   if (node.getNodeType() == 1)
	   {
	   int noOfChildNodes = node.getChildNodes().getLength();
	   for (int j = 0; j < noOfChildNodes; j++) {
	   if ((node.getChildNodes().item(j).getNodeType() == 1) && (node.getChildNodes().item(j).getNodeType() != 3) && (node.getChildNodes().item(j).getChildNodes().getLength() <= 1))
	   {
	   key = node.getChildNodes().item(j).getNodeName();
	   value = node.getChildNodes().item(j).getTextContent();
	   System.out.println(key+"----"+value);
	   }
	   }
	   }
	   }
	   //cacheObjRepository();
	   }
	   catch (Exception e)
	   {
	   e.printStackTrace();
	   }
	   }
	
	
	public static void setup() throws MalformedURLException
	{
				
		openBrowser(prop.getProperty("URL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(prop.getProperty("URL"));
		
			
		
	}


	public static WebDriver getDriver() {
		if (driver == null) {

			String os = System.getProperty("os.name").toLowerCase();
			if (os.contains("mac")) {
				String exePath = "./Resources/chromedriver";
				System.setProperty("webdriver.chrome.driver", exePath);
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				System.out.println("**Chrome driver initiated**");
			} else {
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

	public static void openBrowser(String url) {
		getDriver().get(url);
	}

		
	
	public static double randomNumber()
	{
		return Math.random();
	}
	
		
	//Verify attribute value of the element on the page by index.
	public static void verifyAttributeValue(By locator, int index, String attributeName, String value)
	{
		
		String actual=driver.findElements(locator).get(index).getAttribute(attributeName).toString();
		if(actual.equals(value))
		System.out.println("true");
		else
			System.out.println("false");
		
	}
	
	//Verifies the value of an attribute on a specified element.
	public static void verifyAttributeValue(By locator, String attributeName, String value)
	{
		
		String actual=driver.findElement(locator).getAttribute(attributeName).toString();
		if(actual.equals(value))
		System.out.println("true");
		else
		System.out.println("false");
		
	}

	
	//Verifies that the checkbox is checked.
	public static void verifyCheckboxIsChecked(By locator)
	{
		boolean bol=driver.findElement(locator).isSelected();
		if(bol==true)
			System.out.println("true");
		else
			System.out.println("false");	
		
	}
	
	//Verifies that the checkbox is checked.
	public static void verifyCheckboxIsChecked(By locator, int index) 
	{
		boolean bol=driver.findElements(locator).get(index).isSelected();
		if(bol==true)
			System.out.println("true");
		else
			System.out.println("false");
	}

	//Verifies that the checkbox is NOT checked.
	public static void verifyCheckboxIsNotChecked(By locator)
	{
		boolean bol=driver.findElement(locator).isSelected();
		if(bol==true)
			System.out.println("false");
		else
			System.out.println("true");
		
	}
	 
	
	//Verifies that the checkbox is NOT checked.
	public static void verifyCheckboxIsNotChecked(By locator, int index)
	{
		boolean bol=driver.findElements(locator).get(index).isSelected();
		if(bol==false)
			System.out.println("true");
		else
			System.out.println("false");
	}
	 
	//Verifies the URL of the current page. 
	
	public static void verifyTitle(String Title) 
	{
		String actualTitle=driver.getTitle();
		System.out.println(actualTitle);
		if(Title.equals(actualTitle))
			System.out.println("true");
		else
			System.out.println("false");
		
	}
	
	public static void verifyCurrentUrl(String URL) 
	{
		String actualUrl=driver.getCurrentUrl();
		System.out.println(actualUrl);
		if(URL.equals(actualUrl))
			System.out.println("true");
		else
			System.out.println("false");
		
	}
	
	//Verifies that the expected value in a drop-down list is a selected item.
	 public static void highlightElement(By locator)  {
		 
		 WebElement ele=driver.findElement(locator);
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 for (int i = 0; i < 1; i++)
		 {
		 js.executeScript("arguments[0].setAttribute('style', arguments[1]);", ele, "color: ; border: 5px solid Green;");
		 try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		 js.executeScript("arguments[0].setAttribute('style', arguments[1]);", ele, "");
		
		 }}
	 
public static void highlightElement(By locator,int index)  {
		 
		 WebElement ele=driver.findElements(locator).get(index);
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 for (int i = 0; i < 1; i++)
		 {
		 js.executeScript("arguments[0].setAttribute('style', arguments[1]);", ele, "color: ; border: 5px solid Green;");
		 try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		 js.executeScript("arguments[0].setAttribute('style', arguments[1]);", ele, "");
		
		 }}
	 
	 
	 public static void input(By locator,String value)
	 {
		 highlightElement(locator);
		 driver.findElements(locator).clear();
		 driver.findElement(locator).sendKeys(value);
	 }
	 
	 public static void input(By locator,int index,String value)
	 {
		 highlightElement(locator,index);
		 driver.findElements(locator).get(index).clear();
		 driver.findElements(locator).get(index).sendKeys(value);
	 }
	 
	 public static void click(By locator) 
	 
	 {
		 highlightElement(locator);			
		 driver.findElement(locator).click();
			
			
			
		}
 public static void click(By locator,int index) 
	 
	 {
	 	highlightElement(locator,index);	
		 driver.findElement(locator).click();
	 }
 
 public static void waitAWhile(int i)     
 {
     try 
     {
         Thread.sleep(i*1000);
     } 
     catch (InterruptedException ex) 
     {
         System.out.println("Error ID 48: Error while waiting:"+ex.getMessage());
     }
 }
 
 public static void deleteCookies()
 {
     try
     {
         driver.manage().deleteAllCookies();
     }
     catch(Exception ex)
     {
         System.out.println("[Utilities]-Could not delete Cookies<>FAIL");
     }
 }
 public static void exists(By locator) 
 {
     try
     {
         if(driver.findElement(locator).isDisplayed())
         {
             System.out.println("Present");
         }
         else
         {
           //  takeSnapshot("[Utilities]-Element doesnt EXIST<>EXIST-FAIL",WARNING);
        	  System.out.println("Not Present");
         }
     }
     catch(Exception ex)
     {            
         System.out.println("Element doesnt EXIST<>EXIST-FAIL");
         
     }
 } 
 public static void exists(By locator,int index)
 {
     try
     {
         if(driver.findElements(locator).get(index).isDisplayed())
         {
             //addToReport("[Utilities]-"+driver.findElement(by).getText()+" EXISTS<>EXIST-PASS",PASS);                
        	 System.out.println("Present");
         }
         else
         {
        	 System.out.println("Not Present");
             
         }
     }
     catch(Exception ex)
     {            
    	 System.out.println("[Utilities]-Element doesnt EXIST<>EXIST-FAIL");
         
     }
 }
 public static void exists(String text) 
 {
     try
     {
         if(driver.findElement(By.tagName("body")).getText().toLowerCase().contains(text.toLowerCase()))
         {                                
             //addToReport("[Utilities]-"+text+" EXISTS<>EXIST-PASS",PASS);
        	 System.out.println("Present");
         }
         else
         {
             //takeSnapshot("[Utilities]-Element doesnt EXIST<>EXIST-FAIL",WARNING);
        	 System.out.println("Not Present");
         }
     }
     catch(Exception ex)
     {            
    	 System.out.println("[Utilities]-Element doesnt EXIST<>EXIST-FAIL");
         
     }
 }
 public static void hover(By locator)
 {
     try
     {
         
         Robot robot=new Robot();
         robot.mouseMove(0, 0);
         WebElement e=driver.findElement(locator);
         Actions builder = new Actions(driver);
         builder.moveToElement(e).build().perform();                
         waitAWhile(5);
     }
     catch(Exception e)
     {
         System.out.println("Could not perform hover:\t"+e.getMessage());
     }        
 }    
 public static void hoverOverFirstAndClickSecond(By locator1,By locator2)
 {
     try
     {
         
         Actions builder = new Actions(driver);
         builder.moveToElement(driver.findElement(locator1)).build().perform();
         driver.findElements(locator2).get(1).click();
         //waitAWhile(5);
         System.out.println("[Utilities]-Hovered over first & clicked second<>PASS");
     }
     catch(Exception ex)
     {
    	 System.out.println("[Utilities]-Could not perform Hover&Click:\t"+ex+"<>FAIL");
     }
 }
 
 public static void textproperties(By locator,String valuetype)
  {
	 String a=driver.findElement(locator).getCssValue(valuetype);
	 System.out.println(Color.fromString(a).asHex());
	 
 }
 
 public static void textproperties(By locator,int index ,String valuetype)
 {
	 
	 String a=driver.findElements(locator).get(index).getCssValue(valuetype);
			 
	 System.out.println(Color.fromString(a).asHex());
	 
}
 public static void closeBrowser()
 {
	 driver.quit();
 }
 
}	


