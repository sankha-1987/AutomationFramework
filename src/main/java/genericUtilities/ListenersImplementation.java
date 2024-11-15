package genericUtilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * This class provides implementation to ITestListener Interface of TestNG
 * @author User
 */
public class ListenersImplementation implements ITestListener{

	//Capture the current system date and time for Screenshot name and Report name
	Date d = new Date();
	SimpleDateFormat f = new SimpleDateFormat(" dd-MM-yyyy hh-mm-ss");
	String date = f.format(d);
	
	//For Extent Reports
	ExtentReports report;
	ExtentTest test;
	
	@Override
	public void onTestStart(ITestResult result) {
		
		//Capture the method name of @Test
		String methodName = result.getMethod().getMethodName();
		
		/*@Test execution is started*/
		System.out.println(methodName + "-> Test Script execution started -> [@Listeners]");
		
		/*Intimate Extent Reports for @Test execution*/
		test = report.createTest(methodName);
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		//Capture the method name of @Test
		String methodName = result.getMethod().getMethodName();
		
		/*@Test execution is PASS*/
		System.out.println(methodName + "-> Test Script is PASS -> [@Listeners]");
		
		/*Log the status of test as PASS in Extent Report*/
		test.log(Status.PASS, methodName + "-> Test Script is PASS");
	}

	@Override
	public void onTestFailure(ITestResult result){
		
		//Capture the method name of @Test
		String methodName = result.getMethod().getMethodName();
		
		
		/*@Test execution is FAIL*/
		System.out.println(methodName + "-> Test Script is FAIL -> [@Listeners]");
		
		//Capture the exception
		System.out.println(result.getThrowable());
		
		/*Log the status of test as FAIL in Extent Report*/
		test.log(Status.FAIL, methodName + "-> Test Script is FAIL");
		
		/*Log the exception in Extent Report*/
		test.log(Status.WARNING, result.getThrowable());
		
		/*Capture the Screenshot*/
		SeleniumUtility s = new SeleniumUtility();
		
		//Screenshot name configured
		String screenshotName = methodName + date;
		
		try {	// cannot throw Exception because onTestFailure is overridden method												
			String path = s.captureScreenshot(BaseClass.sDriver, screenshotName);
			
			/*Attach the Screenshot in Extent Report*/
			test.addScreenCaptureFromPath(path);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		//Capture the method name of @Test
		String methodName = result.getMethod().getMethodName();
		
		/*@Test execution is SKIP*/
		System.out.println(methodName + "-> Test Script is SKIP -> [@Listeners]");
		
		//Capture the exception
		System.out.println(result.getThrowable());
		
		/*Log the status of test as SKIP in Extent Report*/
		test.log(Status.SKIP, methodName + "-> Test Script is SKIP");
		
		/*Log the Exception in Extent Report*/
		test.log(Status.WARNING, result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
		
		/*Suite level execution - <suite>*/
		System.out.println("Suite Execution started -> [@Listeners]");
		
		/*Basic Configuration of Extent Report*/
		ExtentSparkReporter esr = new ExtentSparkReporter(".\\ExtentReports\\Report -"+date+".html");
		esr.config().setDocumentTitle("SWAG LABS Execution Report");
		esr.config().setReportName("Execution Build version 1.12");
		esr.config().setTheme(Theme.DARK);
		
		/*Feed the configuration to Extent reports class*/
		report= new ExtentReports();
		report.attachReporter(esr);
		report.setSystemInfo("Base Env", "Test Env");
		report.setSystemInfo("Base Browser", "Microsoft Edge");
		report.setSystemInfo("Base Platform", "Windows Family");
		report.setSystemInfo("Base URL", "TestEnv.com");
		report.setSystemInfo("Reporter Name", "Chaitra");	
	}

	@Override
	public void onFinish(ITestContext context) {
		
		/*Suite level execution - <suite>*/
		System.out.println("Suite Execution finished -> [@Listeners]");
		
		/*Generate the extent Report*/
		report.flush();
	}
}