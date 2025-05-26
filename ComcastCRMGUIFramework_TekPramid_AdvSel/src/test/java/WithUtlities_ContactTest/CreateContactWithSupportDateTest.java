package WithUtlities_ContactTest;

import org.openqa.selenium.interactions.Actions;

import generic.fileUtility.ExcelUtility;
import generic.fileUtility.FileUtility;
import generic.webdriverUtility.JavaUtility;
import generic.webdriverUtility.WebDriverUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateContactWithSupportDateTest {

	public static void main(String[] args) throws Exception {
		//Fetching the common data from the property file
		/*create object of FileUtility for propertyFile*/
		FileUtility fLib= new FileUtility();
		String Browser=	fLib.getDatafromPropertiesFile("browser");
		String Url=fLib.getDatafromPropertiesFile("url");
		String Username=fLib.getDatafromPropertiesFile("username");
		String Password=fLib.getDatafromPropertiesFile("password");
		//creating Random Number
		JavaUtility jLib=new JavaUtility();
		//Fetching the testScript data
				/*create an object of ExcelFile*/
				ExcelUtility eLib=new ExcelUtility();
				
				String LastName = eLib.getDataFromExcel("contact",1,2)+jLib.getRandomNumber();

		//Achieving polymorphism because during runtime we can able to select the particular browser
		WebDriver driver=null;
		if(Browser.equals("chrome")) {
			driver= new ChromeDriver();
		}
		else if (Browser.equals("firefox")) {
			driver=new FirefoxDriver();
		}
		else if (Browser.equals("edge")) {
			driver=new EdgeDriver();
		}
		else {
			driver=new ChromeDriver();
		}

		//Actual Script Creating Orgnasation
		driver.manage().window().maximize();
		WebDriverUtility wLib=new WebDriverUtility();
		wLib.waitForPageToLoad(driver);
		driver.get(Url);
		driver.findElement(By.name("user_name")).sendKeys(Username);
		driver.findElement(By.name("user_password")).sendKeys(Password);
		driver.findElement(By.id("submitButton")).click();

		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@title=\"Create Contact...\"]")).click();
		driver.findElement(By.name("lastname")).sendKeys(LastName);

		//capture the start Date
		String startDate = jLib.getSystemDateYYYYDDMM();
		String endDate = jLib.getRequireddateYYYYDDMM(30);
		//with support date
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);

		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();


		//Verying Header Message Expected Result with the Actual Result

		String actualLastName = driver.findElement(By.id("dtlview_Last Name")).getText();

		if(actualLastName.equals(LastName)) {
			System.out.println(LastName + " is entered===PASS");
		}
		else {
			System.out.println(LastName + " is not entered===FAIL");
		}
		String ActstartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();

		if(ActstartDate.equals(startDate)) {
			System.out.println(startDate + " is entered===PASS");
		}
		else {
			System.out.println(startDate + " is not entered===FAIL");
		}
		String ActendDate = driver.findElement(By.id("dtlview_Support End Date")).getText();

		if(ActendDate.equals(endDate)) {
			System.out.println(endDate + " is entered===PASS");
		}
		else {
			System.out.println(endDate + " is not entered===FAIL");
		}
		Actions act=new Actions(driver);
		WebElement adminImg = driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
		act.moveToElement(adminImg).click().perform();
		driver.findElement(By.partialLinkText("Sign Out")).click();
		driver.close();
	}
}

