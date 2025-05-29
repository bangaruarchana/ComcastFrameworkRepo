package com.comcast.crm.ObjectRepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home_Page {

	WebDriver driver;
	@FindBy(linkText = "Organizations")
	private WebElement orglink;
	
	@FindBy(linkText = "Products")
	private WebElement productlink;
	
	public WebElement getProductlink() {
		return productlink;
	}

	@FindBy(linkText= "Contacts")
	private WebElement contactlink;
	
	@FindBy(linkText= "More")
	private WebElement morelink;
	
	@FindBy(linkText= "Campaigns")
	private WebElement campaignlink;
	
	@FindBy(xpath = "//img[@src=\"themes/softed/images/user.PNG\"]")
	private WebElement AdminImg;
	
	@FindBy(linkText = "Sign Out")
	private WebElement Logoutlink;

	public WebElement getAdminImg() {
		return AdminImg;
	}

	public WebElement getLogoutlink() {
		return Logoutlink;
	}

	public WebElement getContactlink() {
		return contactlink;
	}


	public WebElement getMorelink() {
		return morelink;
	}


	public WebElement getCampaignlink() {
		return campaignlink;
	}


	public WebElement getOrglink() {
		return orglink;
	}
	
	
	public Home_Page(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	public void navigateToCampaignPage() {
		Actions act= new Actions(driver);
		act.moveToElement(morelink).perform();
		campaignlink.click();
	}

	public void LogOut() {
		Actions act=new Actions(driver);
		act.moveToElement(AdminImg).perform();
		Logoutlink.click();
	}




}
