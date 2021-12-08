package org.cynergy.automation.testcase;
import org.cynergy.automation.commonlib.DataFile;
import org.cynergy.automation.screen.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Login_Testcase extends LoginPage {
	
		
	@Test(priority = 1)
	public void Login_TC_0001() throws Exception{
			logger = reports.createTest("TC_0001 Validate User is redirecting to Home Screen ");
			logger.assignDevice("ANDROID");
			logger.assignAuthor("SUMIT");
			logger.assignCategory("UserLogin ");
			ClickGetStarted_Button();
			TextValidate("Log in to view your accounts");	
	}

	@Test(priority = 2 , dependsOnMethods = "Login_TC_0001")
	public void Login_TC_0002() throws Exception{
			logger = reports.createTest("TC_0002 Validate User is redirecting to the Login Screen after clicking on Username and Password  ");
			logger.assignDevice("IOS");
			logger.assignAuthor("BHARATH");
			logger.assignCategory("UserLogin");
			ClickUsernamePassword_Button();
			Assert.assertTrue(ValidateButtonText("Log in to view your accounts",20,"Log in to view your accounts"));
	}

	
	@Test(priority = 3,dataProviderClass = DataFile.class , dataProvider = "LoginTestcase" , dependsOnMethods = "Login_TC_0001")
	public void Login(String row,String tcid ,String desc,  String uid , String pwd, String actual, String expected, String result) throws Exception{
			logger = reports.createTest(tcid+ " "+ desc);
			logger.assignDevice("ANDROID");
			logger.assignAuthor("SUMIT");
			logger.assignCategory("UserLogin ");		
			Username_Enter_Text(uid);
			Password_Enter_Text(pwd);
			Login_Click();			
			Assert.assertTrue(ValidateButtonText(actual, 30, expected, "Login_Testcase",Integer.parseInt(row) ));
			Click_MessagePOPUP();		
	}	
}
