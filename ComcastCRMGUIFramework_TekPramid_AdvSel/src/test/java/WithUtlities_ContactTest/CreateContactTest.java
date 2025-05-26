package WithUtlities_ContactTest;

import org.openqa.selenium.interactions.Actions;

import com.comcast.crm.ObjectRepositoryutility.CreateNewContact_Page;
import com.comcast.crm.ObjectRepositoryutility.Home_Page;
import com.comcast.crm.baseTest.BaseClass;

import generic.fileUtility.ExcelUtility;
import generic.fileUtility.FileUtility;
import generic.webdriverUtility.JavaUtility;
import generic.webdriverUtility.WebDriverUtility;

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

public class CreateContactTest extends BaseClass {

	public static void main(String[] args) throws Exception {

		String LastName = eLib.getDataFromExcel("contact",1,2)+jLib.getRandomNumber();
		//navigate to contact page
		Home_Page hp=new Home_Page(driver);
		hp.getContactlink().click();
		
		//click on create contack contact button
		CreateNewContact_Page cnp=new CreateNewContact_Page(driver);
		//Verying Header Message Expected Result with the Actual Result
		String actualLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		if(actualLastName.equals(LastName)) {
			System.out.println(LastName + " is entered===PASS");
		}
		else {
			System.out.println(LastName + " is not entered===FAIL");
		}
		
	}
}

