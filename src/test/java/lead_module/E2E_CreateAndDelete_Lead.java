package lead_module;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import genericUtilities.ExcelUtils;
import genericUtilities.FileUtils;
import genericUtilities.JavaUtils;
import genericUtilities.WebDriverUtils;
import objectRepo.CreateNewLeadPage;
import objectRepo.HomePage;
import objectRepo.LeadConfirmationPage;
import objectRepo.LeadsPage;
import objectRepo.LoginPage;

@Listeners(genericUtilities.ListenerImplementationClass.class)
public class E2E_CreateAndDelete_Lead extends BaseClass {
	
	String explastName;
	//@Test(priority = -1, groups = "lead")
	@Test(groups = "lead")
	public void  create_Lead_Test() throws IOException {

		// Creating object for utility classes

		// reading property file using utility class

		// Reading data from excel using utility class method
		explastName = eLib.readSingleDataFromExcel("Leads", 0, 2) + jLib.getRandomNo();
		String companyName = eLib.readSingleDataFromExcel("Leads", 1, 2);
		String leadSourceDD = eLib.readSingleDataFromExcel("Leads", 2, 2);
		String industryDD = eLib.readSingleDataFromExcel("Leads", 3, 1);

		// Launching browser
		
		// maximize window

		// wait foe page load


		// Login to application
		

		// Click on 'LEADS' link
		HomePage hp = new HomePage(driver);
		hp.clickOnLeadsLink();

		// Click on 'Create Lead' lookup image
		LeadsPage ldp = new LeadsPage(driver);
		ldp.clickOnCreateLeadLkpImage();

		// Enter all mandatory fields
		CreateNewLeadPage clp = new CreateNewLeadPage(driver);
		clp.createLead(explastName, companyName, leadSourceDD, industryDD);
		
		// verification in leads table
		LeadConfirmationPage lcp = new LeadConfirmationPage(driver);
		//explastName = "HELLO Listener";
		
		lcp.verifyLeadCreated(driver, explastName);

		// Signout
		System.out.println("=======lead created========");
		
	}
		
	
	@Test(groups = "lead", dependsOnMethods = "create_Lead_Test", retryAnalyzer = genericUtilities.IRetryImplementation.class)
	public void  delete_Lead_Test() throws IOException, InterruptedException {
			
			// Creating object for utility classes
			

			// reading property file using utility class
			

			// Launching browser
			

			// maximize window

			// wait for page load


			// Login to application
			

			// Click on 'LEADS' link
			HomePage hp = new HomePage(driver);
			hp.clickOnLeadsLink();

			// Search for created lead and delete
			LeadsPage ldp = new LeadsPage(driver);
			
			//Assert.fail();
			//delete the lead
			ldp.deleteLead(explastName, driver, wLib);
			Thread.sleep(3000);
			
			//close the browser
			System.out.println("=== lead deleted===");
		}

}
