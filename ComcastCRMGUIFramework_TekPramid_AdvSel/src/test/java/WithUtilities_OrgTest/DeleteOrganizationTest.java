package WithUtilities_OrgTest;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
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
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.ObjectRepositoryutility.CreateNewOrganization_Page;
import com.comcast.crm.ObjectRepositoryutility.Home_Page;
import com.comcast.crm.ObjectRepositoryutility.Login_Page;
import com.comcast.crm.ObjectRepositoryutility.Organisation_Page;
import com.comcast.crm.ObjectRepositoryutility.OrganizationInformation_Page;

import generic.fileUtility.ExcelUtility;
import generic.fileUtility.FileUtility;
import generic.webdriverUtility.JavaUtility;
import generic.webdriverUtility.WebDriverUtility;

public class DeleteOrganizationTest {

	public static void main(String[] args) throws Exception {
		//Fetching the common data from the property file

		/*create object of FileUtility for propertyFile And ExcelFile*/
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

		String orgName = eLib.getDataFromExcel("org",10,2)+jLib.getRandomNumber();

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
		WebDriverUtility wLib=new WebDriverUtility();
		wLib.waitForPageToLoad(driver);
		//step 1: Loginto to application
		driver.get(Url);

		Login_Page lp=new Login_Page(driver);
		lp.LoginToApp(Url,Username, Password);

		//step 2: navigate to Oraganization module

		Home_Page hp=new Home_Page(driver);
		hp.getOrglink().click();

		//step 3: click on "Create Organization" button
		Organisation_Page op=new Organisation_Page(driver);
		op.getCreateNewOrgBtn().click();
		//step 4: enter all the deatils & create new organization
		CreateNewOrganization_Page cnop=new CreateNewOrganization_Page(driver);
		cnop.createOrg(orgName);

		//verify Header msg Expected Result

		OrganizationInformation_Page oip=new OrganizationInformation_Page(driver);
		String actualOrgName= oip.getHeaderMsg().getText();

		if(actualOrgName.contains(orgName)) {
			System.out.println(orgName + " name is verified===PASS");
		}
		else {
			System.out.println(orgName + "name is not verified===FAIL ");
		}
		
		//go back to organization page
		hp.getOrglink().click();
		
		//search for the organization
		op.getSearchtxt().sendKeys(orgName);
		//select class
		wLib.selectByText(op.getSearchDropdown(),"Organization Name");
		op.getSearchNowBtn().click();
		
		//In dynamic web table select & delete Org
		driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[.='del']")).click();
		wLib.switchToAlertAndAccept(driver);
		//step 5: Logout
		hp.LogOut();	

	    driver.quit();
	}

}

