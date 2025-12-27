package genericUtilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.openqa.selenium.io.FileHandler;

public class ListenerImplementationClass implements ITestListener {

	ExtentReports report;
	ExtentTest test;
	
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		test = report.createTest(methodName);
		extentTest.set(test);
		//extentTest.get().log(Status.INFO, methodName + " execution starts");
		extentTest.get().log(Status.INFO, methodName + " execution starts");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		//test.log(Status.PASS, methodName + "----->passed");
		//extentTest.get().log(Status.PASS, methodName + "----->passed");
		extentTest.get().log(Status.PASS, "<b>"+methodName + "----->passed</b>"); //----bold text format
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		//test.log(Status.SKIP, methodName + "---->skipped");
		//extentTest.get().log(Status.SKIP, methodName + "---->skipped");
		//extentTest.get().log(Status.SKIP, methodName + "<i>---->skipped</i>");	//---italic text format
		extentTest.get().log(Status.SKIP, "<i>"+methodName + "---->skipped</i>");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		String fileName = methodName + new JavaUtils().sysDate();
		try {
			
			//TakesScreenshot ts = (TakesScreenshot) BaseClass.sdriver;
			TakesScreenshot ts = (TakesScreenshot) BaseClass.wdriver.get();

			File src = ts.getScreenshotAs(OutputType.FILE);
			File dest = new File(".\\screenshot\\" +fileName+".png");
			
			FileUtils.copyFile(src, dest);
			String path = dest.getAbsolutePath();
			extentTest.get().addScreenCaptureFromPath(path);
			//extent.get().addScreenCaptureFromPath(path); //ataching sceenshot into extent report
			
			extentTest.get().log(Status.FAIL, result.getThrowable());
			//extent.get().log(Status.FAIL, result.getThrowable());
			
			extentTest.get().log(Status.FAIL, "<i>"+methodName + "-----> failed</i>");
			//extent.get().log(Status.FAIL, methodName + "-----> failed");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onStart(ITestContext context) {

		ExtentSparkReporter htmlreport = new ExtentSparkReporter(
				".\\ExtentReport\\report" +new JavaUtils().sysDate()+".html");
		htmlreport.config().setDocumentTitle("TP-30");
		htmlreport.config().setTheme(Theme.DARK);
		
		htmlreport.config().setReportName("Vtiger");

		report = new ExtentReports();
		report.attachReporter(htmlreport);
		report.setSystemInfo("base_browser", "chrome");
		report.setSystemInfo("base_platform", "windows");
		report.setSystemInfo("base_url", "http://localhost:8888");
		report.setSystemInfo("ReporterName", "Divya");

	}

	@Override
	public void onFinish(ITestContext context) {
		report.flush();
	}

}
