package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups= {"Datadriven"})
	public void verify_loginDDT(String email, String pwd, String exp) throws InterruptedException {
		logger.info("** starting TC_003_loginDDT **");
		try {
			// Home Page
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			// Login
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(pwd);
			lp.clickLogin();

			// My Account
			MyAccountPage macc = new MyAccountPage(driver);
			boolean targetPage = macc.isMyAccountPageExists();

			/*
			 * Data is valid -login success-test pass-logout -login fail-test fail
			 * 
			 * Data is invalid -login success-test failed-logout - login fail-test pass
			 * 
			 */
			if (exp.equalsIgnoreCase("Valid")) {
				if (targetPage == true) {
					macc.clickLogout();
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}

			}

			if (exp.equalsIgnoreCase("Invalid")) {
				if (targetPage == true) {
					macc.clickLogout();
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
			Assert.fail();
		}
Thread.sleep(3000);
		logger.info("** Finished TC_003_LoginDDT **");

	}
}
