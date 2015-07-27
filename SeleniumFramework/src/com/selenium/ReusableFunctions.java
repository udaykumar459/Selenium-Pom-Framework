package com.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.apache.log4j.*;

public class ReusableFunctions extends ElementFunctions
{
	public WebDriver driver;
	Logger log = Logger.getLogger(getClass());
	
	public ReusableFunctions(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
	}

	/**
	 * To click on Mobile Element
	 * @param locator
	 * @param timeToWait
	 */
	public void performClick(By locator,int timeToWait)
	{
		try
		{
			WebElement element = driver.findElement(locator);
			if($ElementExists(locator, timeToWait))
			{
				element.click();
			}
		}
		catch(NoSuchElementException noElement)
		{
			log.error("Element is not Present");
			Assert.fail("Element is not Presnt");
		}
		catch(StaleElementReferenceException se)
		{
			log.info("Element is no longer attached to the DOM ");
			se.printStackTrace();
		}
		catch(Exception e)
		{
			log.info("Unable to click on the given element ");
		}
	}
	
	/**
	 * Navigate to a web Page using http Url. 
	 * @param URL
	 */
	public void navigateToUrl(String URL)
	{
		try
		{
			log.info("Navigating to the given URL:  "+URL);
			driver.get(URL);
			log.info("Successfully Navigated to the given URL : "+URL);
		}
		catch(Exception e)
		{
			log.info("Cross check your Http URL again.");
			e.printStackTrace();
		}
	}
	
	/**
	 * To get Title of a current web page
	 * @return title
	 */
	public String getPageTitle()
	{
		String title="";
		try
		{
			title=driver.getTitle();
			log.info("Current Web Page title is : "+title);
		}
		catch(Exception e)
		{
			log.error("The title of a web page could not be found");
			e.printStackTrace();
		}
		return title;
	}
	
	/**
	 * To Refresh current Page
	 */
	public void refreshPage()
	{
		driver.navigate().refresh();
	}
}
