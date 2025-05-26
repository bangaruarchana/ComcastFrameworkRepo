package generic.webdriverUtility;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.comcast.crm.baseTest.BaseClass;

public class UtilityClassObject {

	public static ThreadLocal<ExtentTest> test= new ThreadLocal<ExtentTest>();
	public static ThreadLocal<WebDriver> driver= new ThreadLocal<WebDriver>();
	//public static ThreadLocal<BaseClass> base=new ThreadLocal<BaseClass>();

	public static ExtentTest getTest() {//getters method for Extent test
		return test.get();

	}
	public static void setTest(ExtentTest actTest) {//setters method for extenttest
		test.set(actTest);
	}
	public static WebDriver getDriver() {
		return driver.get();//getters for webdriver
	}

	public static void setDriver(WebDriver actdriver) {
		driver.set(actdriver);
	}


}
