package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {
	
	//declaration
	@FindBy(xpath = "//img[@title='Create Contact...']")
	private WebElement contactLkpImg;

	public WebElement getContactLkpImg() {
		return contactLkpImg;
	}
	
	//initialization
	public ContactsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//business logic
	public void clickOnCreateContactLkpImage() {
		contactLkpImg.click();
	}

}
