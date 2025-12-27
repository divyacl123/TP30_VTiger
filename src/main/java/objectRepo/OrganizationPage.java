package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.*;
public class OrganizationPage {
	
	@FindBy(xpath = "//img[@title='Create Organization...']")
	private WebElement orgLookupImg;
	
	@FindBy(xpath = "//table[@class='lvt small']//tr[@class='lvtColData']/td/a[@title='Organizations']")
	private List<WebElement> orgList;

	public WebElement getOrgLookupImg() {
		return orgLookupImg;
	}

	public void setOrgLookupImg(WebElement orgLookupImg) {
		this.orgLookupImg = orgLookupImg;
	}
	
	//initialization
	public OrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//business logic
	public void clickOnCreateOrgLkpImage()
	{
		orgLookupImg.click();
	}
}
