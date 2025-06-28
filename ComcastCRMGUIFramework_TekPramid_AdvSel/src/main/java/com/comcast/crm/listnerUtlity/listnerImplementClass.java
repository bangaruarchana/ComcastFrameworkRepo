package com.comcast.crm.listnerUtlity;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.baseTest.BaseClass;

public class listnerImplementClass  implements ITestListener, ISuiteListener{

	public ExtentSparkReporter spark;
	public  static ExtentTest test;
	public static ExtentReports reports;

	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report Configuration");	
		String time=new Date().toString().replace(" ","_").replace(":", "_");
		ExtentSparkReporter spark=new ExtentSparkReporter("./AdvanceReport/report"+time+".html");
		spark.config().setDocumentTitle("CRM Test Suite Result");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		//add Environment info & create test
		reports=new ExtentReports();
		reports.attachReporter(spark);
		reports.setSystemInfo("Operatig System","windows-10");
		reports.setSystemInfo("Browser", "CHROME-100");
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report backup");
		reports.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("===="+result.getMethod().getMethodName()+"==START==");		
	    test=reports.createTest(result.getMethod().getMethodName());
	    test.log(Status.INFO, result.getMethod().getMethodName()+"====STARTED====");

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("===="+result.getMethod().getMethodName()+"==END==");		
        test.log(Status.INFO,result.getMethod().getMethodName()+"====COMPLETED====");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName=result.getMethod().getMethodName();
		
		TakesScreenshot ts=(TakesScreenshot)BaseClass.sdriver;
		String filePath=ts.getScreenshotAs(OutputType.BASE64);
		
		String time=new Date().toString().replace(" ","_").replace(":", "_");
		test.addScreenCaptureFromBase64String(filePath,testName+" "+time);
		test.log(Status.FAIL, result.getMethod().getMethodName()+"====FAILED====");

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP,result.getMethod().getMethodName()+"====Skipped====" );

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

}
