package com.testDataManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import org.testng.Assert;
import org.apache.log4j.*;

public class TestDataManager 
{
	String userDir = System.getProperty("user.dir");
	String fileSeparator = System.getProperty("file.separator");
	private Properties properties = new Properties();
	private String propertyFile="";
	FileInputStream pFile;
	Logger log = Logger.getLogger(getClass());
	
	/**
	 * Parameterized constructor for TestDataManager
	 * @param propertyFileName
	 */
	public TestDataManager(String propertyFileName)
	{
		propertyFile = propertyFileName;
	}
	
	public void loadProprtyFile()
	{
		try 
		{
			pFile = new FileInputStream(userDir+fileSeparator+"Property Files"+fileSeparator+propertyFile.toLowerCase()+".properties");
			properties.load(pFile);
			pFile.close();
		} 
		catch (FileNotFoundException fnfe)
		{
			log.error("Property File cannot be found:  "+propertyFile);
			Assert.fail("Property File cannot be found:  "+propertyFile);
			fnfe.printStackTrace();
		}
		catch(Exception e)
		{
			log.error("Cannot read Property File :  "+propertyFile);
			e.printStackTrace();
		}
		
	}
	public String getPropertyValue(String key)
	{
		String value ="";
		if(key!="")
		{
			loadProprtyFile();
			try
			{
				if(!properties.getProperty(key).trim().isEmpty())
				{
					value= properties.getProperty(key).trim();
				}
			}
			catch(NullPointerException npe)
			{
				log.error("Key  :"+key+"  doesn't exists in the given proprty File : "+propertyFile);
				Assert.fail("Key  :"+key+"  doesn't exists in the given proprty File : "+propertyFile);
			}
		}
		else
		{
			log.error("key should be given");
            Assert.fail("key should be given");  
		}
		return value;
		
	}
}
