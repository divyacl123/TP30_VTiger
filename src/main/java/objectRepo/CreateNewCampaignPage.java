package objectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtils;

public class CreateNewCampaignPage extends WebDriverUtils{

	@FindBy(name = "campaignname")
	private WebElement campaignNameTxtField;

	@FindBy(name = "campaigntype")
	private WebElement camptypeDD;

	@FindBy(xpath = "//img[@title='Select']")
	private WebElement productLkp;

	@FindBy(id = "search_txt")
	private WebElement searchTxtFiled;

	@FindBy(name = "search")
	private WebElement searchBtn;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	public WebElement getCampaignNameTxtField() {
		return campaignNameTxtField;
	}

	public WebElement getCamptypeDD() {
		return camptypeDD;
	}

	public WebElement getProductLkp() {
		return productLkp;
	}

	public WebElement getSearchTxtFiled() {
		return searchTxtFiled;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	// initialization
	public CreateNewCampaignPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//business logic
	public void createCampaign(String campName, String campType, WebDriver driver, String product) throws InterruptedException {
		campaignNameTxtField.sendKeys(campName);
		dropdownUsingContainsVisibleText(camptypeDD, campType);
		productLkp.click();
		Thread.sleep(3000);
		switchToWindow(driver, "Products&action");
		Thread.sleep(3000);
		searchTxtFiled.sendKeys(product);
		searchBtn.click();
		driver.findElement(By.linkText(product)).click();
		switchToWindow(driver, "Campaigns&action");
		saveBtn.click();
	}
}
