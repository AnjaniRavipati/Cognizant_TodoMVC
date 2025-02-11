package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TodoSearchBox extends BasePage{
	
	public TodoSearchBox(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@class='new-todo']")
	WebElement txtSearch;
	
	@FindBy(xpath ="//main[@class='main']/ul/li/div/input[@type='checkbox']")
	WebElement clkRadiobtn;
	
	@FindBy(xpath ="//span[@class='todo-count']")
	WebElement displayTxt;
	
	@FindBy(xpath ="//div[@class='view']/button[@class='destroy']")
	List<WebElement> clkDelete;
	
	@FindBy(xpath ="//ul[@class='filters'] /li/a[text()='All']")
	WebElement clkAll;
	
	@FindBy(xpath ="//a[text()='Active']")
	WebElement clkActive;
	
	@FindBy(xpath ="//a[@class='selected']")
	WebElement clkCompleted;
	
	@FindBy(xpath ="//button[@class='clear-completed']")
	WebElement clkclearCompleted;
	
	@FindBy(xpath ="//ul[@class='todo-list']/li")
	List<WebElement> radioBtns;
	
	@FindBy(xpath ="//a[@href='https://github.com/tastejs/todomvc/tree/gh-pages/examples/react']")
	WebElement clklink1;
	
	
	
	
	public void setSearch(String usearch)
	{
		txtSearch.sendKeys(usearch);
	}
	
	public String getSearch() {
		return displayTxt.getText();
	}
	
	public List<WebElement> getRadioBtnList()
    {
        return radioBtns;
    }
	
	public List<WebElement> clickDeleteBtn()
    {
        return clkDelete;
    }
	
	public void clickRadioBtn()
	{
		clkRadiobtn.click();
	}
	
	public void clickAll() {
		clkAll.click();
	}
	
	public void clickActive()
	{
		clkActive.click();
	}
	
	public void clickCompleted()
	{
		clkCompleted.click();
	}
	
	public void clickclearCompleted()
	{
		clkclearCompleted.click();
	}
	
	public void clickLink()
	{
		clklink1.click();
	}
}
