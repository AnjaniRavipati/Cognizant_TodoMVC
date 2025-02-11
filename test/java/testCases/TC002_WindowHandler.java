package testCases;

import org.testng.annotations.Test;
import org.testng.Assert;
import java.util.Set;
import pageObjects.TodoSearchBox;
import testBase.BaseClass;

public class TC002_WindowHandler extends BaseClass {
	
	@Test 
	public void windowHandler() throws Exception {
		// Write code to handle multiple windows	
		
		TodoSearchBox search = new TodoSearchBox(driver);
		
		try {
		
		 search.clickLink();
		 
		 String parent = driver.getWindowHandle();
		
		Set<String> allWindows = driver.getWindowHandles();
		
		int count = allWindows.size();
		
		System.out.println("Total windows"+count);
		
		for(String child:allWindows) 
		{
			if(!parent.equalsIgnoreCase(child))
			{
				driver.switchTo().window(child);
				System.out.println("Child window title is" + driver.getTitle());
				Thread.sleep(3000);
				driver.close();
			}
		}
		
		driver.switchTo().window(parent);
		System.out.println("Parent window title is "+ driver.getTitle());
		}
        catch(Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
	}

}
