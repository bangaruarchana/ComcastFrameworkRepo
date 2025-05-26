package practice_Test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import generic.fileUtility.ExcelUtility;
import generic.webdriverUtility.WebDriverUtility;

public class GetProductInfoFromAmazonTest {

	@Test(dataProvider = "getdata")
	public void getproductinfoTest(String brandName,String productName) {
		WebDriver driver=new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/");

		//search product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("vivo",Keys.ENTER);

		//capture product info
		String xpath="//span[text()='"+productName+"']/ancestor::div[@class=\"a-section a-spacing-small a-spacing-top-small\"]/descendant::span[@class=\"a-price-whole\"]";
		String price= driver.findElement(By.xpath(xpath)).getText();
		System.out.println("price of the product is : "+price);

        driver.close();

	}
	@DataProvider
	public Object[][] getdata() throws Exception {
	ExcelUtility eLib=new ExcelUtility();
	int rowCount = eLib.getRowCount("product");
		Object[][]data=new Object[rowCount][2];
		for(int i=0;i<rowCount;i++) {
		data[i][0]=eLib.getDataFromExcel("product", i+1, 0);
		data[i][1]=eLib.getDataFromExcel("product",i+1, 1);
		
		}
		return data;
		
	}
}
