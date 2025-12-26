package objectRepo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import genericUtilities.WebDriverUtils;

public class HomePage {

	@FindBy(linkText = "Organizations")
	private WebElement organizationsLink;

	@FindBy(linkText = "Contacts")
	private WebElement contactsLink;

	@FindBy(linkText = "Leads")
	private WebElement leadsLink;

	@FindBy(linkText = "Products")
	private WebElement productsLink;

	@FindBy(linkText = "More")
	private WebElement moreLink;

	@FindBy(linkText = "Campaigns")
	private WebElement capaignsLink;

	@FindBy(id = "qccombo")
	private WebElement quickCreate;

	@FindBy(name  = "campaignname")
	private WebElement campNameField;
	
	@FindBy(xpath = "//img[@alt='Select']")
	private WebElement productSelectLkp;
	
	public WebElement getOrganizationsLink() {
		return organizationsLink;
	}

	public WebElement getContactsLink() {
		return contactsLink;
	}

	public WebElement getLeadsLink() {
		return leadsLink;
	}

	public WebElement getProductsLink() {
		return productsLink;
	}

	public WebElement getMoreLink() {
		return moreLink;
	}

	public WebElement getCapaignsLink() {
		return capaignsLink;
	}

	public WebElement getProductSelectLkp() {
		return productSelectLkp;
	}

	public WebElement getSerachField() {
		return serachField;
	}

	public WebElement getSerachBtn() {
		return serachBtn;
	}

	public WebElement getExpectedCloseDate() {
		return expectedCloseDate;
	}

	@FindBy(id = "search_txt")
	private WebElement serachField;
	
	@FindBy(name = "search")
	private WebElement serachBtn;
	
	@FindBy(id = "jscal_trigger_closingdate")
	private WebElement expectedCloseDate;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement administrator;

	@FindBy(linkText = "Sign Out")
	private WebElement signOut;

	public WebElement getOrganizations() {
		return organizationsLink;
	}

	public WebElement getContacts() {
		return contactsLink;
	}

	public WebElement getLeads() {
		return leadsLink;
	}

	public WebElement getProducts() {
		return productsLink;
	}

	public WebElement getMore() {
		return moreLink;
	}

	public WebElement getCapaigns() {
		return capaignsLink;
	}

	public WebElement getQuickCreate() {
		return quickCreate;
	}

	public WebElement getCampNameField() {
		return campNameField;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public WebElement getAdministrator() {
		return administrator;
	}

	public WebElement getSignOut() {
		return signOut;
	}

	// initialization

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);

	}

	// business logic
	public void clickOnOrgaizationLink() {
		organizationsLink.click();
	}

	public void clickOnContactsLink() {
		contactsLink.click();
		
	}

	public void clickOnLeadsLink() {
		leadsLink.click();
	}

	public void clickOnProductsLink() {
		productsLink.click();
	}

	public void clickOnMoreLink() {
		moreLink.click();
	}

	public void clickOnCampaignsLink() {
		capaignsLink.click();
	}

	public void clickOnQuickCreate() {
		quickCreate.click();
	}

	public void signOutApplication(WebDriverUtils wLib, WebDriver driver) throws InterruptedException {
		administrator.click();
		//Thread.sleep(4000);
		wLib.waitUntilEleToBeVisible(driver, 10, signOut);
		
		//wLib.mouseHoverAndClickOnEle(driver, administrator);
		wLib.clickOnElementUsingJSE(driver, signOut);
	}
	
	public void createCampQuickCreateDD(WebDriverUtils wLib, String qcOption, String campName, String expProdName, String closeDate, String day, WebDriver driver) throws InterruptedException
	{
		
		wLib.dropdownUsingContainsVisibleText(quickCreate, qcOption);
		Thread.sleep(5000);
		campNameField.sendKeys(campName);
		productSelectLkp.click();
		wLib.switchToWindow(driver, "Products&action");

		driver.findElement(By.id("search_txt")).sendKeys(expProdName);
		driver.findElement(By.name("search")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText(expProdName)).click();
		Thread.sleep(3000);
		wLib.switchToWindow(driver, "Home&action");
		expectedCloseDate.click();
		Thread.sleep(3000);
		
		for (;;) {

			try {

				//driver.findElement(By.xpath("//td[@class='title' and text()='" +expectedCloseDate+"']/ancestor::div[@class='calendar']/descendant::td[@class='day' and text()='"+day+"']")).click();
				driver.findElement(By.xpath("//div[@class='calendar']/table/thead/tr/td[.='"+closeDate+"']/../../following-sibling::tbody/tr/td[text()='"+day+"']")).click();
				System.out.println(closeDate+" "+day+ " clicked");
				break;
			} catch (Exception e) {
				driver.findElement(By.xpath("//div[@class='calendar']/table/thead/tr[@class='headrow']/td[4]")).click();
				//driver.findElement(RelativeLocator.with(By.xpath("//tr[@class='headrow']/td[@class='button nav']")).toRightOf(By.xpath("//tr[@class='headrow']/td[@class='button' and text()='Today']"))).click();
				//System.out.println("Not clicked");

			}

		}
		
		saveBtn.click();
	}

}
