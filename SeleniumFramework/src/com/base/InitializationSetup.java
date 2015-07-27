package com.base;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;
import org.apache.log4j.*;

public class InitializationSetup 
{
	private WebDriver driver;
	String userDir = System.getProperty("user.dir");
	String fileSeparator = System.getProperty("file.separator");
	String chromeDriverPath = userDir+fileSeparator+"Resources"+fileSeparator+"Drivers"+fileSeparator+"chromedriver.exe";
	String iEDriverPath = userDir+fileSeparator+"Resources"+fileSeparator+"Drivers"+fileSeparator+"IEDriverServer.exe";
	Logger log = Logger.getLogger(getClass());
	
	/**
	 * Setter method to set WebDriver instance
	 * @param driver
	 */
	public void setDriver(WebDriver driver)
	{
		driver = this.driver;
	}
	
	/**
	 * Getter method to get WebDriver instance
	 * @return
	 */
	public WebDriver getDriver()
	{
		return driver;
	}
	
	@BeforeSuite(alwaysRun=true)
	public void beforeSuite()
	{
		log.info("**** Test Suite Started *****");
	}
	
	/**
	 * To initialize the browser setup
	 * @param browser
	 */
	@Parameters("browser")
	@BeforeClass()
	public void setUp(@Optional ("firefox") String browser)
	{
		try
		{
			if(browser.equalsIgnoreCase("firefox"))
			{
				driver= new FirefoxDriver();
			}
			else if(browser.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", chromeDriverPath);
				driver = new ChromeDriver();
			}
			else if(browser.equalsIgnoreCase("ie"))
			{
				System.setProperty("webdriver.ie.driver", iEDriverPath);
				driver = new InternetExplorerDriver();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	
	/**
	 * To close the driver instance
	 */
	@AfterClass
	public void tearDown()
	{
		driver.close();
	}

	/**
	 * To destroy the driver instance
	 */
	@AfterSuite
	public void quitDriver()
	{
		if(driver !=null)
		{
			driver.quit();
		}
	}
}
