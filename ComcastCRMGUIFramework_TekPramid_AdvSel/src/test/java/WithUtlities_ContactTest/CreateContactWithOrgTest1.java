package WithUtlities_ContactTest;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import generic.fileUtility.ExcelUtility;
import generic.fileUtility.FileUtility;
import generic.webdriverUtility.JavaUtility;
import generic.webdriverUtility.WebDriverUtility;

public class CreateContactWithOrgTest1 {	
		@Test
		public void testcase1() throws Exception {
			/*create object of FileUtility for propertyFile And ExcelFile*/
			FileUtility fLib= new FileUtility();
			String Browser=	fLib.getDatafromPropertiesFile("browser");
			String Url=fLib.getDatafromPropertiesFile("url");
			String Username=fLib.getDatafromPropertiesFile("username");
			String Password=fLib.getDatafromPropertiesFile("password");

			// generate random number
			JavaUtility jLib=new JavaUtility();
//			Reading Test Script data(Excel Data) from excel file
			//Fetching the testScript data
			/*create an object of ExcelFile*/
			ExcelUtility eLib=new ExcelUtility();
			
			String orgName = eLib.getDataFromExcel("contact",7,2)+jLib.getRandomNumber();
			String LastName = eLib.getDataFromExcel("contact",7,3)+jLib.getRandomNumber();
			
			WebDriver driver = null;
			if (Browser.equals("chrome")) {
				driver = new ChromeDriver();
			} else if (Browser.equals("firefox")) {
				driver = new FirefoxDriver();
			} else if (Browser.equals("edge")) {
				driver = new EdgeDriver();
			} else {
				driver = new ChromeDriver();
			}

//			1. Login to Application
			driver.manage().window().maximize();
			WebDriverUtility wLib=new WebDriverUtility();
			wLib.waitForPageToLoad(driver);
			driver.get(Url);

			driver.findElement(By.name("user_name")).sendKeys(Username);
			driver.findElement(By.name("user_password")).sendKeys(Password);
			driver.findElement(By.id("submitButton")).click();
			// 2. Navigate to Organization Module
			driver.findElement(By.linkText("Organizations")).click();

//			3. Click on Create Organization button
			driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

//			4. Enter all details and create new organization
			driver.findElement(By.name("accountname")).sendKeys(orgName);
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			
			// verify header phone number info expected result
			String header = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			if (header.contains(orgName)) {
				System.out.println(orgName + "is created and ------->PASS");
			} else {
				System.out.println(orgName + "is  not created and ------->FAIL");
			}
			// step 5 navigate to the contact module
			driver.findElement(By.linkText("Contacts")).click();
			// step 6
			driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

			// step 7 enter the details and create new organisation
			driver.findElement(By.name("lastname")).sendKeys(LastName);
			driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
			// switch to child window
			
			wLib.switchToTabOnURL(driver, "module=Accounts");
			driver.findElement(By.name("search_text")).sendKeys(orgName);
			driver.findElement(By.name("search")).click();
			driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();

			// switch to parent window
			wLib.switchToTabOnURL(driver, "Contacts&action");
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			//verify  header message expected result
			String header1=	driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		//	System.out.println(orgName);
			if(header1.contains(orgName))
			{
				System.out.println(orgName + "is created and ------->PASS");
			}
			else {
				System.out.println(orgName + "is  not created and ------->FAIL");
			}
			
			//verifying Header orgName info Expected Result
			String actorgname=driver.findElement(By.id("mouseArea_Organization Name")).getText();
		//	System.out.println(actorgname);
			if (actorgname.equalsIgnoreCase(actorgname))
			{
				System.out.println(orgName + "information is created==PASS");
			}
			else {
				System.out.println(orgName + "information is not created==FAIL");
			}
			driver.quit();
			

		}
	}

