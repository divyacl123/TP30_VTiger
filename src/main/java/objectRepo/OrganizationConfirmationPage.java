package objectRepo;

import static org.testng.Assert.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtils;

public class OrganizationConfirmationPage extends WebDriverUtils {
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement confirmMsg;

	public WebElement getConfirmMsg() {
		return confirmMsg;
	}
	
	public OrganizationConfirmationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//business logic
	public void verifyOrgCreated(String org, WebDriver driver)
	{
		waitUntilEleToBeVisible(driver, 10, confirmMsg);
		String actual = confirmMsg.getText();
		
		assertTrue(actual.contains(org), "---Org has not been created with mandatory fields---");
//		if(actual.contains(org)) {
//			
//			System.out.println("organization created successfully");
//		}
//		else {
//			System.out.println("organization not created successfully");
//		}
	}

}
