package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewProductPage {
	
	@FindBy(name = "productname")
	private WebElement prodNameTxtField;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	public WebElement getProdNameTxtField() {
		return prodNameTxtField;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	//initialization
	public CreateNewProductPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//business logic
	public void createProduct(String productName) {
		prodNameTxtField.sendKeys(productName);
		saveBtn.click();
	}

}
