package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.AccountRegistrationPage;
import pageObject.HomePage;
import testBase.BaseClass;

public class TC_001AccountRegTest extends BaseClass{

	@Test(groups={"Regression","Master"})
	public void verify_account_reg()
	{
		logger.info("started TC_001AccountRegTest");
		try {
			
		HomePage hp=new HomePage(driver);
		
		hp.clickMyAccount();
		logger.info("clicked my account");
		
		hp.clickRegister();
		logger.info("clicked on  registration");
		
		AccountRegistrationPage repage = new AccountRegistrationPage(driver);
		
		logger.info("Customer registration details....");
		
		repage.setFirstName(randomeString().toUpperCase());
		repage.setLastName(randomeString().toUpperCase());
		repage.setEmail(randomeString()+"@gmail.com");
		repage.setTelephone(randomeNumber());
		
		
		String password =randomeAlphaNumeric();
		
		repage.setPassword(password);
		repage.setConfirmPassword(password);
		
		repage.setprivacyPolicy();
		repage.clickContinue();
		
		logger.info("expected message");
		
		String confmsg= repage.getConfirmationMsg();
		if(confmsg.equals("Your Account Has Been Created!")){
			Assert.assertTrue(true);
		}
		else {	

			logger.error("Test failed");
			logger.debug("Debug logs");
			Assert.assertTrue(false);
		}
		//Assert.assertEquals(confmsg, "Your Account Has Been Created!!");
		}
		catch(Exception e)
				{

			Assert.fail();
	}
        logger.info("Finished TC_001AccountRegTest");
	}
}
