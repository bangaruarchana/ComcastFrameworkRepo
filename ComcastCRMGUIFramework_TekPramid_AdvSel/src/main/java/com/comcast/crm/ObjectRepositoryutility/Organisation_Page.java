package com.comcast.crm.ObjectRepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Organisation_Page {

	WebDriver driver;
	public Organisation_Page(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath = "//img[@title=\"Create Organization...\"]")
	private WebElement createNewOrgBtn;
	
	@FindBy(name="search_text")
	private WebElement searchtxt;
	
	@FindBy(name="search_field")
	private WebElement searchDropdown;
	
	@FindBy(name="submit")
    private WebElement searchNowBtn;
	
	
	public WebElement getSearchtxt() {
		return searchtxt;
	}
	
	public WebElement getSearchDropdown() {
		return searchDropdown;
	}


	public WebElement getSearchNowBtn() {
		return searchNowBtn;
	}


	public WebElement getCreateNewOrgBtn() {
		return createNewOrgBtn;
	}
	
}
