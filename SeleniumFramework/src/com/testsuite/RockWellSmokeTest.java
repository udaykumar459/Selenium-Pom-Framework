package com.testsuite;
import org.testng.annotations.*;
import com.base.InitializationSetup;
import org.apache.log4j.*;
import com.page.data.RockWellData;
import com.page.objects.RockWellHomePage;

public class RockWellSmokeTest extends InitializationSetup
{
	public RockWellHomePage homePage;
	RockWellData testData ;
	Logger log = Logger.getLogger(getClass());
	
	@BeforeMethod
	public void setUp()
	{
		homePage =  new RockWellHomePage(getDriver());
		testData = new RockWellData();
	}
	
	@Test
	public void tc_01_RockWellTest()
	{
		homePage.navigateToRockWellHomePage();
		homePage.verifyRockWellTitle();
	}
	
	@AfterMethod
	public void tearDown()
	{
		
	}
}
