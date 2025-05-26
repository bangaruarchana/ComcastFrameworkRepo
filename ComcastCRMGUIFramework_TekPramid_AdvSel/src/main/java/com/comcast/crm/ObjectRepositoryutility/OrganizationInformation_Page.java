package com.comcast.crm.ObjectRepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformation_Page {
 
	WebDriver driver;
	public OrganizationInformation_Page(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getIndustryheaderMsg() {
		return industryheaderMsg;
	}


	public WebElement getTypeheaderMsg() {
		return typeheaderMsg;
	}

	@FindBy(xpath = "//span[@class=\"dvHeaderText\"]")
	private WebElement headerMsg;
	
	
	public WebElement getHeaderMsg() {
		return headerMsg;
	}
	
	
	@FindBy(id = "dtlview_Industry")
	private WebElement industryheaderMsg;
	
	
	@FindBy(id = "dtlview_Type")
	private WebElement typeheaderMsg;
	
	@FindBy(id = "dtlview_Phone")
	private WebElement phoneNumberheaderMsg;
	
	
	public WebElement getPhoneNumberheaderMsg() {
		return phoneNumberheaderMsg;
	}
	
	
	
	
}
