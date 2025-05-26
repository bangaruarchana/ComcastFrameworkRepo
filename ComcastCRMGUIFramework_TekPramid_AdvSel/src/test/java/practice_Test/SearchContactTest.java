package practice_Test;
/**
 * test class for Contact module
 * @author Archana Nagindla
 */

import org.testng.annotations.Test;

import com.comcast.crm.ObjectRepositoryutility.Login_Page;
import com.comcast.crm.baseTest.BaseClass;

public class SearchContactTest extends BaseClass {
	/**
	 * Scenario: login()==>navigateContact==>createcontact()==verify
	 */
	@Test
	public void searchcontactTest() {
		Login_Page lp=new Login_Page(driver);
		lp.LoginToApp("url","username", "password");
	}

	
}
