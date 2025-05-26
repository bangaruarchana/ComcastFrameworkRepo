package WithUtlities_ContactTest;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import generic.webdriverUtility.JavaUtility;
import generic.webdriverUtility.WebDriverUtility;

public class CreateContactWithOrgTest {

	public static void main(String[] args) throws Exception {
		//Fetching the common data from the property file
		FileInputStream fis=new FileInputStream("./configAppData/commondata.properties");
		Properties prop= new Properties();
		prop.load(fis);
		String Browser=	prop.getProperty("browser");
		String Url=prop.getProperty("url");
		String Username=prop.getProperty("username");
		String Password=prop.getProperty("password");

		//creating Random Number
          JavaUtility jLib=new JavaUtility();
		//Fetching the testScript data
		FileInputStream fis1=new FileInputStream("./testScriptData/TestScriptdata.xlsx");
		Workbook wb=WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("contact");
		Row row = sh.getRow(7);
		String orgName = row.getCell(2).toString()+jLib.getRandomNumber();
		String conLastName=row.getCell(3).getStringCellValue();
		wb.close();

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
		//step 1: login to application
		driver.manage().window().maximize();
		WebDriverUtility wLib=new WebDriverUtility();
		wLib.waitForPageToLoad(driver);
		driver.get(Url);

		driver.findElement(By.name("user_name")).sendKeys(Username);
		driver.findElement(By.name("user_password")).sendKeys(Password);
		driver.findElement(By.id("submitButton")).click();

		//step 2:navigate to Organisation module
		driver.findElement(By.linkText("Organizations")).click();

		//step 3:click on create organisation button
		driver.findElement(By.xpath("//img[@title=\"Create Organization...\"]")).click();

		//step 4: enter all the details and create new Organisation
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();

		//Verying Header Message Expected Result with the Actual Result
		String headerinfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headerinfo.contains(orgName)) {
			System.out.println(orgName + " header verified===PASS");
		}
		else {
			System.out.println(orgName + " header verified===FAIL");
		}

		//step 5: navigate to contact module
		driver.findElement(By.linkText("Contacts")).click();

		//step 6:click on create contact button
		driver.findElement(By.xpath("//img[@title=\"Create Contact...\"]")).click();

		//step 7:enter all the details and create new contact
		driver.findElement(By.name("lastname")).sendKeys(conLastName);

		driver.findElement(By.xpath("//input[@name=\"account_name\"]/following-sibling::img")).click();

		//switch to child window
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();

		while(it.hasNext()) {
			String windowId = it.next();//capture the data from set collection we will use this
			driver.switchTo().window(windowId);//switching
			String actUrl=driver.getCurrentUrl();
			if(actUrl.contains("module=Accounts")){
				break;
			}
		}
			driver.findElement(By.name("search_text")).sendKeys(orgName);
			driver.findElement(By.name("search")).click();
			driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();

			//switch to parent window
			Set<String> set1 = driver.getWindowHandles();
			Iterator<String> it1 = set1.iterator();

			while(it1.hasNext()) {
				String windowId1=it1.next();

				String actUrl1=driver.getCurrentUrl();
				if(actUrl1.contains("Accounts&action")) {
					break;
				}
			}

			//saving 
			driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();

			//Verying Header Message Expected Result with the Actual Result

			headerinfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			if(headerinfo.contains(orgName)) {
				System.out.println(orgName + " header verified===PASS");
			}
			else {
				System.out.println(orgName + " header verified===FAIL");
			}
			//Verying Header Message & Header orgName info Expected Result with the Actual Result
			String actualOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
			if(actualOrgName.equals(orgName)) {
				System.out.println(orgName + " information is created===PASS");
			}
			else {
				System.out.println(orgName + " information is created===FAIL");
			}

			String actualLastName = driver.findElement(By.id("dtlview_Last Name")).getText();

			if(actualLastName.equals(conLastName)) {
				System.out.println(conLastName + " is entered===PASS");
			}
			else {
				System.out.println(conLastName + " is not entered===FAIL");
			}

			Actions act=new Actions(driver);
			WebElement adminImg = driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
			act.moveToElement(adminImg).click().perform();
			driver.findElement(By.partialLinkText("Sign Out")).click();
			driver.quit();	
		}
	}
