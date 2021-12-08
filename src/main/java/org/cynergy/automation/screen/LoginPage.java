package org.cynergy.automation.screen;

import org.cynergy.automation.commonlib.ObjectRpositoryAndroid;
import org.cynergy.automation.commonlib.ObjectRpositoryiOS;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class LoginPage extends HomeScreen{

	@iOSXCUITFindBy(xpath =ObjectRpositoryiOS.USERNAME_TEXT_FIELD)
	@AndroidFindBy(xpath = ObjectRpositoryAndroid.USERNAME_TEXT_FIELD)
	private MobileElement username;
	
	public void Username_Enter_Text(String text) {
	 EnterText(username, 20, text);
	}
	
	
	@iOSXCUITFindBy(xpath =ObjectRpositoryiOS.PASSWORD_TEXT_FIELD)
	@AndroidFindBy(xpath = ObjectRpositoryAndroid.PASSWORD_TEXT_FIELD)
	private MobileElement password;
	public void Password_Enter_Text(String text) {
		 EnterText(password, 20, text);
		}
	
	@iOSXCUITFindBy(xpath = ObjectRpositoryiOS.LOGIN_BUTTON)
	@AndroidFindBy(xpath = ObjectRpositoryAndroid.LOGIN_BUTTON)
	private MobileElement loginButton;
	
	public void Login_Click() {
		 ClickElement(loginButton, 20, "Login Button");
		}
	
	
	@iOSXCUITFindBy(xpath ="")
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc='Close']")
	private MobileElement closepopup;
	
	public void Click_MessagePOPUP() {
		ClickElement(closepopup, 20, "Close Icon");
	}
	
	
}
