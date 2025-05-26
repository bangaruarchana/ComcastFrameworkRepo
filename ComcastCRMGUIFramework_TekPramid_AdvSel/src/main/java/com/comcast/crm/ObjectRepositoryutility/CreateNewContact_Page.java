package com.comcast.crm.ObjectRepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewContact_Page {

	WebDriver driver;
	public CreateNewContact_Page(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(xpath = "//img[@alt=\"Create Contact...\"]")
	private WebElement createContactbtn;
	
	@FindBy(name="lastname")
	private WebElement lastnameTxtfld;
	
	@FindBy(xpath = "//input[@title=\"Save [Alt+S]\"]")
	private WebElement savebtn;

	public WebElement getCreateContactbtn() {
		return createContactbtn;
	}

	public WebElement getLastnameTxtfld() {
		return lastnameTxtfld;
	}

	public WebElement getSavebtn() {
		return savebtn;
	}
	public void createContact(String LastName) {
		createContactbtn.click();
		lastnameTxtfld.sendKeys(LastName);
		savebtn.click();
	}
	
}
