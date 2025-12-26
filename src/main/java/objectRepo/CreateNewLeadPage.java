package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtils;

public class CreateNewLeadPage extends WebDriverUtils {

	@FindBy(name = "lastname")
	private WebElement lastNameTxtField;
	
	@FindBy(name = "company")
	private WebElement cmpnyNameTxtField;
	
	@FindBy(name = "leadsource")
	private WebElement leadSourceDropDown;
	
	@FindBy(name = "industry")
	private WebElement industryDropDown;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement save;


	public WebElement getLastNameTxtField() {
		return lastNameTxtField;
	}

	public WebElement getCmpnyNameTxtField() {
		return cmpnyNameTxtField;
	}

	public WebElement getLeadSourceDropDown() {
		return leadSourceDropDown;
	}

	public WebElement getIndustryDropDown() {
		return industryDropDown;
	}
	
	public WebElement getSave() {
		return save;
	}
													
	//initialization
	public CreateNewLeadPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//business logic
	public void createLead(String lastName, String company, String leadsourceDD, String industryDD)
	{
		lastNameTxtField.sendKeys(lastName);
		cmpnyNameTxtField.sendKeys(company);
		dropdownUsingContainsVisibleText(leadSourceDropDown, leadsourceDD);
		dropdownUsingContainsVisibleText(industryDropDown, industryDD);
		save.click();
		
	}

	
}
