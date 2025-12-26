package organization_module;

import static org.testng.Assert.*;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import genericUtilities.BaseClass;
import genericUtilities.ExcelUtils;
import genericUtilities.FileUtils;
import genericUtilities.JavaUtils;
import genericUtilities.ListenerImplementationClass;
import genericUtilities.WebDriverUtils;
import objectRepo.CreateNewOrgPage;
import objectRepo.HomePage;
import objectRepo.LoginPage;
import objectRepo.OrganizationConfirmationPage;
import objectRepo.OrganizationPage;

@Listeners(ListenerImplementationClass.class)
public class Create_ORG_Test_UsingPOM extends BaseClass {

@Test(groups = "organization")
public  void createOrgWithIndType() throws IOException, InterruptedException {
		
		//create obeject for utility classes
		
		
		//reading property file using utility class
		
		
		// Reading data from excel using utility class method
		String OrgName = eLib.readSingleDataFromExcel("Organizations", 0, 2)+jLib.getRandomNo();
		String industryType = eLib.readSingleDataFromExcel("Organizations", 1, 1);
		
		//Launching chrome browser
		
		
		//maximize
		
		//driver.manage().window().maximize();
		
		//wait for load
		
		//navigate to application
		
		//		step1: Login to application
		
		
		//		step2: Click on 'ORGANIZATION' link
		//driver.findElement(By.linkText("Organizations")).click();
		HomePage hp =  new HomePage(driver);
		hp.clickOnOrgaizationLink();
		
		//		step3: Click on 'Create Organization' lookup image
		//driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		OrganizationPage org =new OrganizationPage(driver);
		org.clickOnCreateOrgLkpImage();
		
		//		step4: Enter mandatory fields with valid data and // step5: select industry type
		CreateNewOrgPage cop = new CreateNewOrgPage(driver);
		cop.createOrganization(OrgName, industryType);	
		
		
		//Verification
		//Implementing Assert here
		WebElement confirm =  driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		wLib.waitUntilEleToBeVisible(driver, 10, confirm);
		String confirmOrg = confirm.getText();
		
		assertTrue(confirmOrg.contains(OrgName), "---Organization has not been created---");
		
		/*OrganizationConfirmationPage confirmOrg = new OrganizationConfirmationPage(driver);
		confirmOrg.verifyOrgCreated(OrgName);*/
		
		ListenerImplementationClass.extentTest.get().log(Status.PASS, "----Org has been created---");
		
		// Signout

		//close browser
		System.out.println("==========compeleted with org creation with ind dropdown========");
		
	}

@Test(retryAnalyzer = genericUtilities.IRetryImplementation.class, groups = "organization")
public  void createOrgWithMandFields() throws IOException, InterruptedException {
		
		//create obeject for utility classes
	
		//reading property file using utility class
		
		// Reading data from excel using utility class method
		String OrgName = eLib.readSingleDataFromExcel("Organizations", 0, 2)+jLib.getRandomNo();
		
		//Launching chrome browser
		
		//maximize
		
		//wait for load
		
		//navigate to application
		
		//		step1: Login to application
		
		
		//		step2: Click on 'ORGANIZATION' link
		//driver.findElement(By.linkText("Organizations")).click();
		HomePage hp =  new HomePage(driver);
		hp.clickOnOrgaizationLink();
		
		//		step3: Click on 'Create Organization' lookup image
		//driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		OrganizationPage org =new OrganizationPage(driver);
		org.clickOnCreateOrgLkpImage();
		
		//		step4: Enter mandatory fields with valid data and // step5: select industry type
		//driver.findElement(By.name("accountname")).sendKeys(OrgName);
		CreateNewOrgPage cop = new CreateNewOrgPage(driver);
		//Assert.fail("Listener testing for org with mand fields");
		cop.createOrganization(OrgName);	
		Thread.sleep(4000);
		
		//Verification
		//Assert.fail(); 	---> to try retryAnalyzer
		OrganizationConfirmationPage confirmOrg = new OrganizationConfirmationPage(driver);
		confirmOrg.verifyOrgCreated(OrgName, driver);
		
		// Signout
		
		System.out.println("==========compeleted with org creation with mandatory fields========");
		
	}

}
