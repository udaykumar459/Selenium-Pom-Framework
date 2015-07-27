package com.page.objects;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.page.data.RockWellData;
import com.page.locators.RockWellLocators;
import com.selenium.ReusableFunctions;

import org.apache.log4j.*;

public class RockWellHomePage extends ReusableFunctions implements RockWellLocators
{
	Logger log = Logger.getLogger(getClass());
	public RockWellHomePage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
	}
	
	/**
	 * To navigate to RockWell Home Page
	 */
	public void navigateToRockWellHomePage()
	{
		navigateToUrl(RockWellData.ROCKWELL_URL);
		$WaitForPageToLoad();
	}
	
	/** To Verify RockWell Home Page Title */
	public boolean verifyRockWellTitle()
	{
		boolean isTitleMaches = false;
		String actualTitle = getPageTitle();
		String expectdTitle= RockWellData.ROCKWELL_TITLE;
		isTitleMaches = actualTitle.equalsIgnoreCase(expectdTitle);
		Assert.assertTrue(isTitleMaches, "Title is not matched.");
		log.info("The Actual Title Matches with the Expected Title");
		return isTitleMaches;
	}
}
