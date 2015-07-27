package com.selenium;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.*;
import com.timingFunctions.TimeDimentions;

public class ElementFunctions implements TimeDimentions
{
	Logger log = Logger.getLogger(getClass());
	public WebDriver driver;
	JavascriptExecutor js;
	String pageLoadStatus = null;
	public ElementFunctions(WebDriver driver)
	{
		this.driver = driver;
	}
	
	/**
	 * Checking whether the element is present in the given time.
	 * @param locator
	 * @param timeToWait
	 * @return
	 */
	public boolean $ElementExists(By locator, int timeToWait)
	{
		boolean isElementExists=false;
		log.info("Checking whether the element with locator  "+locator +" is present ");
		WebElement element = driver.findElement(locator);
		try
		{
			WebDriverWait wait = new WebDriverWait(driver,timeToWait);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			if(element.isDisplayed()||element.isEnabled())
			{
				isElementExists = true;log.info("Element is Present with locator  "+locator);
			}
		}
		catch(NoSuchElementException nse)
		{
			log.info("No Element is Present in the entire Page");
			nse.getMessage();
			nse.printStackTrace();
		}
		catch(StaleElementReferenceException sre)
		{
			log.info("Element is no longer attached or deleted from Source");
			sre.printStackTrace();
		}
		catch(TimeoutException toe)
		{
			log.info("Element cannot be found in given time "+timeToWait );
			toe.printStackTrace();
		}
		catch(Exception e)
		{
			log.error("Exception for Element doesn't exists");
			e.printStackTrace();
		}
		return isElementExists;
	}
	
	/**
	 * To wait until Web Page is loaded till the end.
	 */
	public void $WaitForPageToLoad()
	{
		try
		{
			do
			{
			     js = (JavascriptExecutor) driver;
			     pageLoadStatus = (String)js.executeScript("return document.readyState");
				    
			} while ( !pageLoadStatus.equals("complete") );
		}
		catch(Exception e)
		{
			log.error("Unable to wait for page load");
			e.printStackTrace();
		}
	}
}
