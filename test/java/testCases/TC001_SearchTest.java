package testCases;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.Keys;

import org.openqa.selenium.interactions.Actions;

import pageObjects.TodoSearchBox;
import testBase.BaseClass;

public class TC001_SearchTest  extends BaseClass{
	
	@Test(groups = {"Sanity","Master"})
	public void searchTest()  {
		
		logger.info("Search Test");
		TodoSearchBox search = new TodoSearchBox(driver);
		try {
		search.setSearch(randomString()); //Random String
		
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).perform();
		
		logger.info("Validating the search item message");
		
		Assert.assertEquals(search.getSearch(), "1 item left!");
		
		logger.info("selecting radio button");
		
		search.clickRadioBtn();
		
		logger.info("Validating the message after selecting radio button");
		
		Assert.assertEquals(search.getSearch(), "0 items left!");
		
		logger.info("adding another search item");
		
		search.setSearch(prop.getProperty("searchText"));
		
		action.sendKeys(Keys.ENTER).perform();
		
		logger.info("Validating the search item message after adding");
		
		Assert.assertEquals(search.getSearch(), "1 item left!");
		}
		catch(Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
            
	}	
	
}
