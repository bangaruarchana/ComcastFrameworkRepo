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
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.baseTest.BaseClass;

import generic.fileUtility.ExcelUtility;
import generic.fileUtility.FileUtility;
import generic.webdriverUtility.JavaUtility;
import generic.webdriverUtility.WebDriverUtility;

public class CreateOrganizationWithInndustriesTest extends BaseClass {

	public static void main(String[] args) throws Exception {
		ExcelUtility eLib=new ExcelUtility();

		String orgName = eLib.getDataFromExcel("org",4,2)+jLib.getRandomNumber();
		String industry=eLib.getDataFromExcel("org", 4,3);
		String type=eLib.getDataFromExcel("org", 4,4);

		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title=\"Create Organization...\"]")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);

		//select the industry and type
		WebElement selindustry = driver.findElement(By.name("industry"));
		Select sel=new Select(selindustry);
		sel.selectByValue("Energy");

		WebElement seltype = driver.findElement(By.name("accounttype"));
		Select sel1=new Select(seltype);
		sel1.selectByValue("Press");
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();

		//Verying the industries and type info
		String actualindustry=driver.findElement(By.id("dtlview_Industry")).getText();
		if(actualindustry.equals(industry)) {
			System.out.println(industry + " industry has been selected===PASS");
		}
		else {
			System.out.println(industry + " industry has been not selected===FAIL");
		}
		String actualtype=driver.findElement(By.id("dtlview_Type")).getText();
		if(actualtype.equals(type)) {
			System.out.println(type + " type has been selected===PASS");
		}
		else {
			System.out.println(type + " type has been not selected===FAIL");
		}

		WebElement element = driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
		 
		driver.findElement(By.partialLinkText("Sign Out")).click();
		driver.close();
	}

}

