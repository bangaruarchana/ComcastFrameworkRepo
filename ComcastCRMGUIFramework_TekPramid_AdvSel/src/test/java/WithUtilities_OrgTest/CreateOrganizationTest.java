package WithUtilities_OrgTest;
import static org.testng.Assert.assertEquals;

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
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.ObjectRepositoryutility.CreateNewOrganization_Page;
import com.comcast.crm.ObjectRepositoryutility.Home_Page;
import com.comcast.crm.ObjectRepositoryutility.Login_Page;
import com.comcast.crm.ObjectRepositoryutility.Organisation_Page;
import com.comcast.crm.ObjectRepositoryutility.OrganizationInformation_Page;
import com.comcast.crm.baseTest.BaseClass;
import com.comcast.crm.listnerUtlity.listnerImplementClass;

import generic.fileUtility.ExcelUtility;
import generic.fileUtility.FileUtility;
import generic.webdriverUtility.JavaUtility;
import generic.webdriverUtility.UtilityClassObject;
import generic.webdriverUtility.WebDriverUtility;

public class CreateOrganizationTest extends BaseClass {

	@Test
	public void CreateOrganizationTest() throws Exception{	
		
	UtilityClassObject.getTest().log(Status.INFO, "reading data from excel");
		String orgName = eLib.getDataFromExcel("org",1,2)+jLib.getRandomNumber();

		//step 2: navigate to Oraganization module
		Home_Page hp=new Home_Page(driver);
		UtilityClassObject.getTest().log(Status.INFO, "navigate to org page");

		hp.getOrglink().click();

		//step 3: click on "Create Organization" button
		UtilityClassObject.getTest().log(Status.INFO, "click on org link");

		Organisation_Page op=new Organisation_Page(driver);
		op.getCreateNewOrgBtn().click();
		//step 4: enter all the deatils & create new organization
		UtilityClassObject.getTest().log(Status.INFO, "create a new org");

		CreateNewOrganization_Page cnop=new CreateNewOrganization_Page(driver);
		
		cnop.createOrg(orgName);
		UtilityClassObject.getTest().log(Status.INFO, "===>");

		//verify Header msg Expected Result
		OrganizationInformation_Page oip=new OrganizationInformation_Page(driver);
		String actualOrgName= oip.getHeaderMsg().getText();
		boolean status=actualOrgName.contains(orgName);
		Assert.assertEquals(status, true);

	}
	
}
 
