package organization_module;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import genericUtilities.FileUtils;

public class CreateOrgWithMandatoryFieldsTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {

		WebDriver driver = null;
		
		// Create random number
		Random rand = new Random();
		int random = rand.nextInt(500);

		// create object for file utility class
		FileUtils fLib = new FileUtils();
		String BROWSER = fLib.readDataFromPropertyFile("browser");
		String URL = fLib.readDataFromPropertyFile("url");
		String USERNAME = fLib.readDataFromPropertyFile("username");
		String PASSWORD = fLib.readDataFromPropertyFile("password");

//		Properties p = new Properties();

//		String BROWSER = p.getProperty("browser");
//		String URL = p.getProperty("url");
//		String USERNAME = p.getProperty("username");
//		String PASSWORD = p.getProperty("password");

		// Read data from excel
		FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\TP30Testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fi);
		Sheet sh = wb.getSheet("Organizations");

		String OrgName = sh.getRow(0).getCell(2).getStringCellValue() + random;

		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

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

//		step7: verify that you are getting confirmation page

		String actual = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();

		if (actual.contains(OrgName)) {

			System.out.println("organization created successfully");
		} else {
			System.out.println("organization not created successfully");
		}

		// Signout
		// click on Adnministrator
		driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();

		// mouse hover on signout link and click on that
		WebElement elem1 = driver.findElement(By.linkText("Sign Out"));

		Actions act = new Actions(driver);
		act.moveToElement(elem1).click().perform();

		Thread.sleep(3000);
		driver.close();
	}

}
