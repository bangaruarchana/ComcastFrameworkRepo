package practice_Test;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.comcast.crm.baseTest.BaseClass;



public class InvoiceTest extends BaseClass {

		@Test
		public void createInvoiceTest() {
			Reporter.log("Execute createInvoiceTest",true);
			String actTitle=driver.getTitle();
			Assert.assertEquals(actTitle,"Login");
			System.out.println("Step-1");
			System.out.println("Step-2");
			System.out.println("Step-3");
			System.out.println("Step-4");

		
		}
		
		@Test
		public void createInvoiceWithContactTest() {
			Reporter.log("Execute createInvoiceWithContactTest",true);
			System.out.println("Step-1");
			System.out.println("Step-2");
			System.out.println("Step-3");
			System.out.println("Step-4");


		}
}
