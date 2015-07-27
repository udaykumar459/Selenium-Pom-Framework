package com.page.data;

import com.testDataManager.TestDataManager;

public class RockWellData
{
	static TestDataManager tdManager = new TestDataManager("TestData");
	public static String ROCKWELL_URL = tdManager.getPropertyValue("test.URL");
	public static String ROCKWELL_TITLE = tdManager.getPropertyValue("test.TITLE");
}
