package com.comcast.crm.baseTest;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.ObjectRepositoryutility.Home_Page;
import com.comcast.crm.ObjectRepositoryutility.Login_Page;

import generic.databaseUtility.DataBaseUtility;
import generic.fileUtility.ExcelUtility;
import generic.fileUtility.FileUtility;
import generic.webdriverUtility.JavaUtility;
import generic.webdriverUtility.WebDriverUtility;

public class BaseClass {
	public  WebDriver driver =null;
	public  static WebDriver sdriver =null;
	public  DataBaseUtility dLib=new DataBaseUtility();
	public  WebDriverUtility wLib=new WebDriverUtility();
	public  FileUtility fLib= new FileUtility();
	public  ExcelUtility eLib=new ExcelUtility();
	public   JavaUtility jLib=new JavaUtility();
	public  Home_Page hp=new Home_Page(driver);

	@BeforeSuite
	public void ConncetDB() throws SQLException {
		System.out.println("===Connect to DataBase, Report Config===");
		dLib.getDBConnection();
		

}
	@BeforeClass
	public void LaunchBrowser() throws Exception {
		System.out.println("===Launch the Browser===");
		//String Browser=	fLib.getDatafromPropertiesFile("browser");
		
		String Browser=System.getProperty("browser",fLib.getDatafromPropertiesFile("browser"));
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
		sdriver=driver;
	}
	@BeforeMethod
	public void Login() throws Exception {
		System.out.println("===Login===");
		Login_Page lp=new Login_Page(driver);
//		String Url=fLib.getDatafromPropertiesFile("url");
//		String Username=fLib.getDatafromPropertiesFile("username");
//		String Password=fLib.getDatafromPropertiesFile("password");
		
		String Url=System.getProperty("url",fLib.getDatafromPropertiesFile("url"));
		String Username=System.getProperty("username",fLib.getDatafromPropertiesFile("username"));
		String Password=System.getProperty("Password",fLib.getDatafromPropertiesFile("Password"));
		lp.LoginToApp(Url,Username, Password);
	}

	@AfterMethod
	public void Logout() {
		System.out.println("===Logout===");
		hp.LogOut();
	}
	@AfterClass
	public void ClosetheBrowser() {
		System.out.println("===Close the Browser===");
		driver.quit();
	}
	@AfterSuite
	public void DiscconnectToDB() throws Exception {
		System.out.println("===Close Data Base,Report backUp===");
		dLib.DbCloseConnection();
	
	}
}
