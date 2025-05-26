package createOrganization;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.comcast.crm.ObjectRepositoryutility.CreateNewOrganization_Page;
import com.comcast.crm.ObjectRepositoryutility.Home_Page;
import com.comcast.crm.ObjectRepositoryutility.Organisation_Page;
import com.comcast.crm.ObjectRepositoryutility.OrganizationInformation_Page;
import generic.webdriverUtility.UtilityClassObject;
/**
 * 
 * @author Archana Nagindla
 */
import io.github.bonigarcia.wdm.WebDriverManager;
@Listeners

public class createOrganizationTest extends com.comcast.crm.baseTest.BaseClass{
	

	
	@Test
	public void createOrg() throws Exception {
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
	@Test 
	public void createOrgWithIndustries() throws Exception{
		UtilityClassObject.getTest().log(Status.INFO, "fetching data from the excel");

		String orgName = eLib.getDataFromExcel("org",4,2)+jLib.getRandomNumber();
		String industry=eLib.getDataFromExcel("org", 4,3);
		String type=eLib.getDataFromExcel("org", 4,4);
		CreateNewOrganization_Page cop=new CreateNewOrganization_Page(driver);
		UtilityClassObject.getTest().log(Status.INFO, "creating organization with industry & type");

		cop.createOrgWithIndustry(orgName, industry,type);
		
		//verify Header msg Expected Result
		OrganizationInformation_Page oip=new OrganizationInformation_Page(driver);
		String actualOrgName= oip.getHeaderMsg().getText();
		String actualIndustryName=oip.getIndustryheaderMsg().getText();
		String actualTypeName=oip.getTypeheaderMsg().getText();
		UtilityClassObject.getTest().log(Status.INFO, "verifying the actual orgname with expected orgname");

		boolean status=actualOrgName.contains(orgName);
		UtilityClassObject.getTest().log(Status.INFO, "verifying the actual industry name with expected industry name");

		boolean status1=actualIndustryName.contains(industry);
		UtilityClassObject.getTest().log(Status.INFO, "verifying the actual type with expected type");

		boolean status2=actualTypeName.contains(type);
		
		UtilityClassObject.getTest().log(Status.PASS, "verified and passed");

		Assert.assertEquals(status, true);
		UtilityClassObject.getTest().log(Status.PASS, "verified and passed");

		Assert.assertEquals(status1, true);
		UtilityClassObject.getTest().log(Status.PASS, "verified and passed");

		Assert.assertEquals(status2, true);

	}

	@Test
	public void createOrgWithPhoneNumber() throws Exception {
		UtilityClassObject.getTest().log(Status.INFO, "fetching data from the excel");

		String orgName = eLib.getDataFromExcel("org",7,2)+jLib.getRandomNumber();
		String phoneNo = eLib.getDataFromExcel("org",7,3)+jLib.getRandomNumber();
		
		CreateNewOrganization_Page cop=new CreateNewOrganization_Page(driver);
        cop.createOrgWithPhoneNumber(orgName, phoneNo);
        OrganizationInformation_Page oip=new OrganizationInformation_Page(driver);
		String actualOrgName= oip.getHeaderMsg().getText();
		 WebElement actualPhoneNo = oip.getPhoneNumberheaderMsg();
		UtilityClassObject.getTest().log(Status.INFO, "verifying the actual orgname with expected orgname");
		boolean status=actualOrgName.contains(orgName);
		UtilityClassObject.getTest().log(Status.INFO, "verifying the actual industry name with expected industry name");

		
        
      

		
		
	}

}
