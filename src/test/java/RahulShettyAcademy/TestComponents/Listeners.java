package RahulShettyAcademy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import RahulShettyAcademy.resources.ReportsUtility;

public class Listeners extends BaseTest implements ITestListener{

	ExtentReports extent = ReportsUtility.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
		@Override
	  	  public void onTestStart(ITestResult result) {
		   test =  extent.createTest(result.getMethod().getMethodName());
		   extentTest.set(test);
		  }

		@Override
	  	  public void onTestSuccess(ITestResult result) {
			extentTest.get().log(Status.PASS, "Test is passed");
		  }

		@Override
	  	  public void onTestFailure(ITestResult result) {
		    extentTest.get().fail(result.getThrowable());
		    String screenshotPath = null;
		    try {
				driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				screenshotPath = screenshotCapture(result.getMethod().getMethodName(), driver);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			extentTest.get().addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
		  }
		  
		@Override
	  	  public void onTestSkipped(ITestResult result) {
		    // not implemented
		  }

		@Override
	  	  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		    // not implemented
		  }

		@Override
	  	  public void onTestFailedWithTimeout(ITestResult result) {
		    
		  }
		  
		@Override
	  	  public void onStart(ITestContext context) {
		    // not implemented
		  }

		@Override
	  	  public void onFinish(ITestContext context) {
			extent.flush();
		    
		  }
}
