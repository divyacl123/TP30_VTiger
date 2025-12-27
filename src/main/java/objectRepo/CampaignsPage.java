package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignsPage {
	
	@FindBy(xpath = "//img[@title='Create Campaign...']")
	private WebElement createCampaignLkpImg;

	public WebElement getCreateCampaignLkpImg() {
		return createCampaignLkpImg;
	}
	
	//initialization
	public CampaignsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnCreateOrgLkpImage() {
		
		createCampaignLkpImg.click();
	}
}
