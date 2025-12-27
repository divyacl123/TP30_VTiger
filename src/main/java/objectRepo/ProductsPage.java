package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {

	@FindBy(xpath = "//img[@title='Create Product...']")
	private WebElement createProdLkpImg;

	public WebElement getCreateProdLkpImg() {
		return createProdLkpImg;
	}
	
	//initialization
	public ProductsPage( WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//business logic
	public void clickOnCreateProductLkpImage() {
		createProdLkpImg.click();
	}
	
}
