package testCases;

import org.testng.annotations.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import pageObjects.TodoSearchBox;
import testBase.BaseClass;

public class TC003_RadioBtnTest extends BaseClass{
	
	@Test(groups = {"Datadriven"})
	public void radioBtnTest() {
		TodoSearchBox search = new TodoSearchBox(driver);
		try {
		String[] searchData = {"Test UAT scenarios", "Check production tickets priority wise", "Attend daily status meeting"};
		for (String data : searchData) {
			search.setSearch(data);
			
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ENTER).perform();
			
		}
		
		logger.info("Validating the search item message after adding the list of items");
		
		search.getRadioBtnList().get(1).click();
		Assert.assertEquals(search.getSearch(), "3 items left!");
		
		logger.info("Deleting the search item");
		
		search.clickDeleteBtn().get(1).click();
		Assert.assertEquals(search.getSearch(), "2 items left!");
		
		logger.info("Clicking all radio buttons");
		
		search.clickAll();
		Assert.assertEquals(search.getSearch(), "2 items left!");
		
		logger.info("Clicking Active radio buttons");
		
		search.clickActive();
		Assert.assertEquals(search.getSearch(), "2 items left!");
		
		logger.info("Clicking completed radio buttons");
		
		search.clickCompleted();
		Assert.assertEquals(search.getSearch(), "2 items left!");
		
		logger.info("Clicking clear completed radio buttons");
		
		search.clickclearCompleted();
		Assert.assertEquals(search.getSearch(), "2 items left!");
		}
		catch(Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		
		
	}


}
