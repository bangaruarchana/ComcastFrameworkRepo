package com.comcast.crm.ObjectRepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic.webdriverUtility.WebDriverUtility;
/**
 * 
 * @author Archana Nagindla
 * 
 * contains login page elements and business library like login()
 * 
 */
public class Login_Page extends WebDriverUtility {
	//Rule-1 create a seperate java class
	//Rule -2 Object Creation
	//Rule-3 object initialization
    //Initialization in the class 
	WebDriver driver;//creating global variable
	public Login_Page(WebDriver driver) {
		this.driver=driver;//storing with the driver reference in the class
		PageFactory.initElements(driver, this);
	}
	//Rule-4 Object Encapsulation
	@FindBy(name="user_name")
	private WebElement usernametxtfld;

	@FindBy(name="user_password")
	private  WebElement passwordtxtfld;

	@FindBy(id="submitButton")
	private WebElement loginbtn;

	public WebElement getUsernametxtfld() {
		return usernametxtfld;
	}

	public WebElement getPasswordtxtfld() {
		return passwordtxtfld;
	}

	public WebElement getLoginbtn() {
		return loginbtn;
	}

	//Rule-5 Provide Action// business library when we want to perform so many actions then use this
	
	/**
	 * login to application based on url,username & password aurguments
	 * @param url
	 * @param username
	 * @param password
	 */
	public void LoginToApp(String url,String username,String password) {
		waitForPageToLoad(driver);
		driver.get(url);
		driver.manage().window().maximize();//after creating the global variable variable we are maximizing
		usernametxtfld.sendKeys(username);
		passwordtxtfld.sendKeys(password);
		loginbtn.click();
	}
	
}
