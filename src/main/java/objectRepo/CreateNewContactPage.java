package objectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtils;

public class CreateNewContactPage extends WebDriverUtils {
	
	@FindBy(name = "lastname")
	private WebElement lastnameTxtField;
	
	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img[@title='Select']")
	private WebElement selectOrg;
	
	@FindBy(id = "search_txt")
	private WebElement serachTxtField;
	
	@FindBy(name = "search")
	private WebElement searchNowbtn;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement save;
	
	
	//Getters
	public WebElement getLastnameTxtField() {
		return lastnameTxtField;
	}

	public WebElement getSelectOrg() {
		return selectOrg;
	}

	public WebElement getSerachTxtField() {
		return serachTxtField;
	}

	public WebElement getSearchNowbtn() {
		return searchNowbtn;
	}
	
	public WebElement getSave() {
		return save;
	}


	//initialization
	
	public CreateNewContactPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	
	//business logic
	public void createContact(String contactName)
	{
		lastnameTxtField.sendKeys(contactName);
		save.click();
	}
	
	public void createContact(String contactName, String org, WebDriver driver)
	{
		lastnameTxtField.sendKeys(contactName);
		selectOrg.click();
		switchToWindow(driver, "Accounts&action");
		serachTxtField.sendKeys(org);
		searchNowbtn.click();
		driver.findElement(By.linkText(org)).click();
		switchToWindow(driver, "Contacts&action");
		save.click();
	}
	
}
