package objectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtils;

public class LeadsPage extends WebDriverUtils {

	//declaration
	@FindBy(xpath = "//img[@title='Create Lead...']")
	private WebElement createLeadLkpImg;
	
	@FindBy(name = "search_text")
	private WebElement searchField;
	
	@FindBy(xpath = "//input[@value=' Search Now ' and @name='submit']")
	private WebElement searchNowBtn;
	
	@FindBy(name = "search_field")
	private WebElement inDropdown;

	//Getters
	
	public WebElement getCreateLeadLkpImg() {
		return createLeadLkpImg;
	}

	public WebElement getSearchField() {
		return searchField;
	}

	public WebElement getSearchNowBtn() {
		return searchNowBtn;
	}

	public WebElement getInDropdown() {
		return inDropdown;
	}

	//initialization
	public LeadsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//business logic
	public void clickOnCreateLeadLkpImage() {
		createLeadLkpImg.click();
	}
	
	//business logic for deleting lead
	public void deleteLead(String leadName, WebDriver driver, WebDriverUtils wLib) throws InterruptedException {
		searchField.sendKeys(leadName);
		wLib.dropdownUsingContainsVisibleText(inDropdown, "Last Name");
		searchNowBtn.click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr/td/span[@vtfieldname ='lastname']/preceding-sibling::a[@title='Leads' and text()='"+leadName+"']/../following-sibling::td/a[.='del']")).click();
		Thread.sleep(3000);
		acceptAlertPopup(driver);
	
	}
	
}
