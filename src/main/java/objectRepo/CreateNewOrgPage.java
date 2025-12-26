package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtils;

public class CreateNewOrgPage extends WebDriverUtils {
	
	//declaration
	@FindBy(name = "accountname")
	private WebElement orgName;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement save;
	
	@FindBy(name = "industry")
	private WebElement industry;
	
	//Getters
	public WebElement getOrgName() {
		return orgName;
	}

	public WebElement getSave() {
		return save;
	}

	public WebElement getIndustry() {
		return industry;
	}
	
	//initializtaion
	public CreateNewOrgPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//business logic
	public void createOrganization(String org)
	{
		orgName.sendKeys(org);
		save.click();
	}
	
	public void createOrganization(String org, String text)
	{
		orgName.sendKeys(org);
		//industry.click();
		dropdownUsingContainsVisibleText(industry, text);
		save.click();
	}

}
