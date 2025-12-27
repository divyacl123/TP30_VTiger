package genericUtilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtils {

	/**
	 * This method is used to maximize window
	 * 
	 * @author Divya C L
	 * @param driver
	 */
	public void maximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

	/**
	 * This method is used to wait for page load
	 * 
	 * @author Divya C L
	 * @param driver
	 * @param seconds
	 */
	public void waitForPageLoad(WebDriver driver, int seconds) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
	}

	/**
	 * This method is used to wait until the element is visible
	 * 
	 * @param driver
	 * @param seconds
	 * @param element
	 */
	public void waitUntilEleToBeVisible(WebDriver driver, int seconds, WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * This method is used wait until the element is clickable
	 * 
	 * @param driver
	 * @param seconds
	 * @param element
	 */
	public void waitUntilEleToBeClickable(WebDriver driver, int seconds, WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	/**
	 * This method is used to create object for Select by passing dropdown web
	 * element
	 * 
	 * @param element
	 * @return
	 */
	public Select dropdown(WebElement element) {

		Select sel = new Select(element);
		return sel;
	}

	/**
	 * This method is used to select dropdown using selectbyValue()
	 * 
	 * @param element
	 * @param value
	 */
	public void dropdownUsingValue(WebElement element, String value) {

		dropdown(element).selectByValue(value);
	}

	/**
	 * This method is used to select dropdown using index
	 * 
	 * @param element
	 * @param index
	 */
	public void dropdownUsingIndex(WebElement element, int index) {
		dropdown(element).selectByIndex(index);
	}

	/**
	 * This method is used to select dropdown using visible text
	 * 
	 * @param element
	 * @param text
	 */
	public void dropdownUsingVisibleText(WebElement element, String text) {
		dropdown(element).selectByVisibleText(text);
	}
	
	public void dropdownUsingContainsVisibleText(WebElement element, String text) {
		dropdown(element).selectByContainsVisibleText(text);
	}
	

	/**
	 * This method is used to create object for Actions class
	 * 
	 * @param driver
	 * @return
	 */
	public Actions actions(WebDriver driver) {

		Actions act = new Actions(driver);
		return act;
	}

	/**
	 * This method is used to move to particular element and mouse hover on it
	 * 
	 * @param driver
	 * @param element
	 */
	public void mouseHover(WebDriver driver, WebElement element) {

		actions(driver).moveToElement(element).perform();
	}

	/**
	 * This method is used to mouse hover and clcik on that particular web element
	 * 
	 * @param driver
	 * @param element
	 */
	public void mouseHoverAndClickOnEle(WebDriver driver, WebElement element) {

		actions(driver).moveToElement(element).click(element).perform();
	}

	/**
	 * This method is used to drag and drop the element from src to dest elemnt
	 * 
	 * @param driver
	 * @param src
	 * @param dest
	 */
	public void dragAndDrop(WebDriver driver, WebElement src, WebElement dest) {

		actions(driver).dragAndDrop(src, dest).perform();
	}

	// scrollToEle
	/**
	 * This method is used to scroll by x and y axes of web page
	 * 
	 * @param driver
	 * @param x
	 * @param y
	 */
	public void scrollWebPage(WebDriver driver, int x, int y) {
		actions(driver).scrollByAmount(x, y).perform();

	}

	/**
	 * This method is used to scroll till that element
	 * 
	 * @param driver
	 * @param element
	 */
	public void scrollToEle(WebDriver driver, WebElement element) {

		actions(driver).scrollToElement(element).perform();
	}

	// doubleClick
	/**
	 * This method is used to double click on web page
	 * 
	 * @param driver
	 */
	public void doubleClickOnWeBPage(WebDriver driver) {
		actions(driver).doubleClick().perform();

	}

	/**
	 * This method is used to double click on element
	 * 
	 * @param driver
	 * @param element
	 */
	public void doubleClickOnEle(WebDriver driver, WebElement element) {
		actions(driver).doubleClick(element).perform();
	}

	/**
	 * This method is used to right click on element
	 * 
	 * @param driver
	 * @param element
	 */
	public void rightClickOnEle(WebDriver driver, WebElement element) {

		actions(driver).contextClick(element).perform();
	}

	/**
	 * This method is used to click and hold an element
	 * 
	 * @param driver
	 * @param element
	 */
	public void clickAndHold(WebDriver driver, WebElement element) {

		actions(driver).clickAndHold(element).perform();
	}

	/**
	 * This method is used to click and hold an element and release the element
	 * 
	 * @param driver
	 * @param element
	 */
	public void clickAndHoldAndRelease(WebDriver driver, WebElement element) {

		actions(driver).clickAndHold(element).release().perform();
	}

	/**
	 * This method is used to switch to child window from the parent window by using
	 * getWindowHandles(traversing)
	 * 
	 * @param driver
	 * @param expTitle
	 */
	public void switchToWindow(WebDriver driver, String expTitle) {

		//String parent = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		while (it.hasNext()) {
			String currentWin = it.next();
			String actualTitle = driver.switchTo().window(currentWin).getTitle();
			if (actualTitle.contains(expTitle)) {
				break;
			}

		}
	}

	/**
	 * This method is used to switch to parent window by passing windowId of parent
	 * window
	 * 
	 * @param driver
	 * @param parentId
	 */
	public void switchToParentWindow(WebDriver driver, String parentId) {

		// String parent = driver.getWindowHandle();
		driver.switchTo().window(parentId);
	}

	/**
	 * This method is used to switch to child frame using index number
	 * 
	 * @param driver
	 * @param index
	 */
	public void switchToChildFrame(WebDriver driver, int index) {

		driver.switchTo().frame(index);
	}

	/**
	 * This method is used to switch to child frame by passing element
	 * 
	 * @param driver
	 * @param element
	 */
	public void switchToChildFrame(WebDriver driver, WebElement element) {

		driver.switchTo().frame(element);
	}

	/**
	 * This method is used to switch to child frame using name/id
	 * 
	 * @param driver
	 * @param name
	 */
	public void switchToChildFrame(WebDriver driver, String name) {

		driver.switchTo().frame(name);
	}

	/**
	 * This method is used to switch to parent frame
	 * 
	 * @param driver
	 */
	public void switchToParentFrame(WebDriver driver) {
		driver.switchTo().parentFrame();
	}

	/**
	 * This method is used to switch back to main frame
	 * 
	 * @param driver
	 */
	public void switchToMainFrame(WebDriver driver) {

		driver.switchTo().defaultContent();
	}

	// Alert pop-ups
	/**
	 * This method is used to accept/click ok on the alert popUp
	 * 
	 * @param driver
	 */
	public void acceptAlertPopup(WebDriver driver) {

		driver.switchTo().alert().accept();
	}

	/**
	 * This method is used cancel/dismiss the alert popUp
	 * 
	 * @param driver
	 */
	public void cancelAlertPopup(WebDriver driver) {

		driver.switchTo().alert().dismiss();
	}

	/**
	 * This method is used get the text from the alert popUp
	 * 
	 * @param driver
	 */
	public void getTextFromAlertPopup(WebDriver driver) {

		driver.switchTo().alert().getText();
	}

	/**
	 * This method is used enter the text field in the alert popUp
	 * 
	 * @param driver
	 * @param value
	 */
	public void enterValueIntoAlertTextField(WebDriver driver, String value) {

		driver.switchTo().alert().sendKeys(value);
	}

	// javascript executor methods
	/**
	 * This method is used to typecast the webdriver object into JavascriptExecutor
	 * 
	 * @param driver
	 * @return
	 */
	public JavascriptExecutor javaScriptExecutor(WebDriver driver) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js;
	}

	/**
	 * This method is used to scroll till end of web page using Javascript executor
	 * 
	 * @param driver
	 */
	public void scrollHeight(WebDriver driver) {

		javaScriptExecutor(driver).executeScript("window.scrollBy(0, document.body.scrollHeight)");
	}

	// scroll till element
	/**
	 * This method is used to scroll upto particular web element using Javascript
	 * executor
	 * 
	 * @param driver
	 * @param element
	 */
	public void scrollUntilEleIsVisibleUsingJSE(WebDriver driver, WebElement element) {

		javaScriptExecutor(driver).executeScript("arguments[0].scrollIntoView()", element);
	}

	// click on element

	/**
	 * This method is used to click on the web element using Javascript executor
	 * 
	 * @param driver
	 * @param element
	 */
	public void clickOnElementUsingJSE(WebDriver driver, WebElement element) {

		javaScriptExecutor(driver).executeScript("arguments[0].click()", element);
	}

	/**
	 * This method is used to enter the text field using Javascript executor
	 * 
	 * @param driver
	 * @param element
	 * @param value
	 */
	public void enterValueIntoTextFieldUsingJSE(WebDriver driver, WebElement element, String value) {

		javaScriptExecutor(driver).executeScript("arguments[0].value=arguments[1]", element, value);
	}

	/**
	 * This method is used to scroll by x and y co-ordinates from the specific
	 * element using javascriptExecutor
	 * 
	 * @param driver
	 * @param element
	 */
	public void scrollToEleUsingXYCoOrdinateUsingJSE(WebDriver driver, WebElement element) {

		Point loc = element.getLocation();
		int x = loc.getX();
		int y = loc.getY();
		javaScriptExecutor(driver).executeScript("window.scrollBy(" + x + "," + y + ")");
	}
	
	
	public static String takesScreenshotWholePage(WebDriver driver, String fileName) throws IOException
	{
		String filepath = ".\\screenshot\\"+fileName+new JavaUtils().sysDate()+".png";
		TakesScreenshot ts  =(TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(filepath);
		
		FileUtils.copyFile(src, dest);
		String path = dest.getAbsolutePath();
		return path;
	}

}
