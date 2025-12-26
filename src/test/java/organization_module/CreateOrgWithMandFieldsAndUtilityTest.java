package organization_module;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import genericUtilities.ExcelUtils;
import genericUtilities.FileUtils;
import genericUtilities.JavaUtils;
import genericUtilities.WebDriverUtils;

public class CreateOrgWithMandFieldsAndUtilityTest {
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		WebDriver driver = null;
		
		//Creating object for utility classes
		FileUtils fLib = new FileUtils();
		ExcelUtils eLib = new ExcelUtils();
		JavaUtils jLib = new JavaUtils();
		WebDriverUtils wLib = new WebDriverUtils();
		
		//reading property file using utility class
		String BROWSER = fLib.readDataFromPropertyFile("browser");
		String URL = fLib.readDataFromPropertyFile("url");
		String USERNAME = fLib.readDataFromPropertyFile("username");
		String PASSWORD = fLib.readDataFromPropertyFile("password");
		
		// Reading data from excel using utility class method
		String OrgName = eLib.readSingleDataFromExcel("Organizations", 0, 1)+jLib.getRandomNo();
			
		//Launching chrome browser
		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		
		//maximize
		//driver.manage().window().maximize();
		wLib.maximizeWindow(driver);
		
		//wit for load
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wLib.waitForPageLoad(driver, 10);
		
		//navigate to application
		driver.get(URL);

		// step1: Login to application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		// step2: Click on 'ORGANIZATION' link
		driver.findElement(By.linkText("Organizations")).click();
		// Click on 'Create Organization' lookup image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		// Enter mandatory fields with valid data
		driver.findElement(By.name("accountname")).sendKeys(OrgName);

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//step7: verify that you are getting confirmation page
		
		String actual =  driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		//verify
		if(actual.contains(OrgName)) {
			
			System.out.println("organization created successfully");
		}
		else {
			System.out.println("organization not created successfully");
		}
		
		
		//Signout
		//click on Adnministrator
		driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
		
		//mouse hover on signout link and click on that
		WebElement elem1 = driver.findElement(By.linkText("Sign Out"));
		
		wLib.mouseHoverAndClickOnEle(driver, elem1);
	
		Thread.sleep(3000);
		driver.close();
	}

}

		
		
