package WithUtilities_OrgTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import generic.webdriverUtility.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class launch_Browser {

	@Test
	public  void  launchBrowser(){
		WebDriver driver;
		driver=new FirefoxDriver();
		//WebDriverManager.chromedriver().setup();
		WebDriverManager.firefoxdriver().setup();
		driver.quit();
	}
}
