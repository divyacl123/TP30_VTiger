package objectRepo;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtils;

public class LeadConfirmationPage extends WebDriverUtils {
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement confirmMsg;
	
	public WebElement getConfirmMsg() {
		return confirmMsg;
	}
	
	//initialization
	public LeadConfirmationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//business logic
	public void verifyLeadCreated(WebDriver driver, String lastname)
	{
		waitUntilEleToBeVisible(driver, 10, confirmMsg);
		String actualLastName = confirmMsg.getText();
		
		assertTrue(actualLastName.contains(lastname), "-----Lead info not created------");
		/*
		 * if (actualLastName.contains(lastname)) {
		 * System.out.println("Lead info created succefully"); } else {
		 * System.out.println("Lead info not created succefully"); }
		 */
	}
	

}
