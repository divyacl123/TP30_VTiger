package genericUtilities;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentTest;
import com.beust.jcommander.Parameter;

import objectRepo.HomePage;
import objectRepo.LoginPage;

public class BaseClass {
	
	//we have to give public visibility for the object reference variable  to access from outside the package
	public DatabaseUtils dLib = new DatabaseUtils();
	public FileUtils fLib = new FileUtils()	;
	public ExcelUtils eLib = new ExcelUtils();
	public JavaUtils jLib  = new JavaUtils();
	public WebDriverUtils wLib = new WebDriverUtils();
	
	public WebDriver driver;
	//public static WebDriver sdriver;
	
	public static ThreadLocal<WebDriver> wdriver = new ThreadLocal<WebDriver>();
	
	@BeforeSuite(alwaysRun = true)
	//@BeforeSuite(groups = {"regression", "smoke"})
	public void connecToDB() throws SQLException {
		dLib.connectToDB();
		System.out.println("connected to DB");
	}
	
	//@Parameters("BROWSER")
	@BeforeClass(alwaysRun = true)
	//@BeforeClass(groups = {"regression", "smoke"})
	public void launchBrowser() throws IOException {
		//to use @Optional, it's mandatory to declare @Parameters
	//public void launchBrowser(@Optional("chrome") String BROWSER) throws IOException {
		String BROWSER = fLib.readDataFromPropertyFile("browser");
		String URL = fLib.readDataFromPropertyFile("url");
		
		if(BROWSER.equalsIgnoreCase("chrome")) {
			driver =  new ChromeDriver();
		}
		
		else if(BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		else if(BROWSER.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "C:\\Drivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		
		
		System.out.println("opened the browser");
		//maximize window
		wLib.maximizeWindow(driver);
		wdriver.set(driver);
		//sdriver = driver;
		
		//navigate to application
		driver.get(URL);
		
		//wait For Page Load
		wLib.waitForPageLoad(driver, 10);
		
	}
	
	@BeforeMethod(alwaysRun = true)
	//@BeforeMethod(groups = {"regression", "smoke"})
	public void loginToApp() throws IOException, InterruptedException {
		String USERNAME = fLib.readDataFromPropertyFile("username");
		String PASSWORD = fLib.readDataFromPropertyFile("password");
		
		LoginPage lp = new LoginPage(driver);
		lp.loginToApplication(USERNAME, PASSWORD);
		
		System.out.println("logged into application");
	}
	
	@AfterMethod(alwaysRun = true)
	//@AfterMethod(groups = {"regression", "smoke"})
	public void logoutApp() throws InterruptedException {
		HomePage hp = new HomePage(driver);
		
		hp.signOutApplication(wLib, driver);
		
		System.out.println("logout from application");
	}
	
	@AfterClass(alwaysRun = true)
	//@AfterClass(groups = {"regression", "smoke"})
	public void closeBrowser() {
		if(driver!=null) {
		driver.quit();
		}
		System.out.println("closed the browser");
	}
	
	@AfterSuite(alwaysRun = true)
	//@AfterSuite(groups = {"regression", "smoke"})
	public void disconnectDB() throws SQLException {
		
		dLib.disconnectDB();
		System.out.println("disconnected from DB");
	}
	
}
