package com.comcast.crm.ObjectRepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewOrganization_Page {
	
	WebDriver driver;
	public CreateNewOrganization_Page(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(name = "accountname")
	private WebElement Orgnametxtfld;
	
	@FindBy(xpath = "//input[@title=\"Save [Alt+S]\"]")
	private WebElement saveBtn;
	
	@FindBy(name = "industry")
	private WebElement industryDropdown;
	

	@FindBy(name="accounttype")
	private WebElement typedropdown; 
	
	@FindBy(id = "id=\"phone\"")
	private WebElement phoneNoTxtFld;


	public WebElement getTypedropdown() {
		return typedropdown;
	}
	public WebElement getIndustryDropdown() {
		return industryDropdown;
	}
	public WebElement getOrgnametxtfld() {
		return Orgnametxtfld;
	}
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public WebElement getPhoneNoTxtFld() {
		return phoneNoTxtFld;
	}
	public WebElement getphoneNoTxtFld() {
		return phoneNoTxtFld;
	}
 
	public void createOrg(String orgName) {
		Orgnametxtfld.sendKeys(orgName);
		saveBtn.click(); 
	}
 
	public void createOrgWithIndustry(String orgName, String industry, String type) {
		Orgnametxtfld.sendKeys(orgName);
		Select sel= new Select(industryDropdown);
		sel.selectByVisibleText(industry);
		Select sel1= new Select(typedropdown);
		sel1.selectByVisibleText(type);
		saveBtn.click();
		
	}
	
	public void createOrgWithPhoneNumber(String orgName,String phoneNumber) {
		Orgnametxtfld.sendKeys(orgName);
		phoneNoTxtFld.sendKeys(phoneNumber);
		saveBtn.click();
		

	}
}
