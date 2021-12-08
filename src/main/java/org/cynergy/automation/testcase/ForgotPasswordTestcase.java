package org.cynergy.automation.testcase;

import org.cynergy.automation.commonlib.DataFile;
import org.testng.annotations.Test;

public class ForgotPasswordTestcase {

	@Test(dataProviderClass = DataFile.class, dataProvider = "forgotpasswordTestcase")
	public void forgotPassword (String Testcase, String	Username, String Password, String OTP) {
		System.out.println(Testcase);
		System.out.println(Username);
		System.out.println(Password);
		System.out.println(OTP);
	}
	
	
	/*@Test(dataProviderClass = DataFile.class, dataProvider = "forgotpasswordTestcase")
	public void forgotPassword_Bharat (String Testcase, String	Username, String Password, String OTP) {
		System.out.println(Testcase);
		System.out.println(Username);
		//System.out.println(Password);
		System.out.println(OTP);
	}*/
	
	
}
