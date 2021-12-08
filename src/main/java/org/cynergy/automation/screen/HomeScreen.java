package org.cynergy.automation.screen;
import org.cynergy.automation.commonlib.ObjectRpositoryAndroid;
import org.cynergy.automation.commonlib.ObjectRpositoryiOS;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class HomeScreen extends BasePage{
	
	@iOSXCUITFindBy(xpath = ObjectRpositoryiOS.GET_STARTED_BUTTON)
	@AndroidFindBy(xpath = ObjectRpositoryAndroid.GET_STARTED_BUTTON)
	private MobileElement getStartedButton;
	
	
	  public void ClickGetStarted_Button() throws Exception
	    {		  
		  ClickElement( getStartedButton, 20, "Get Started Button");
	        
	    }
	  
	  @iOSXCUITFindBy(xpath = ObjectRpositoryiOS.ENTER_USERNAME_PASSWORD)
		@AndroidFindBy(accessibility = ObjectRpositoryAndroid.ENTER_USERNAME_PASSWORD)
		private MobileElement enterUsernameandPasswordButton;
		
		  public void ClickUsernamePassword_Button() throws Exception
		    {		  
			  ClickElement( enterUsernameandPasswordButton, 25," Username and Password button");
				        
		    }
		  
		  
		  @iOSXCUITFindBy(xpath ="")
		  @AndroidFindBy(xpath = "//android.widget.TextView[@text ='Log in to view your accounts']")
		  private MobileElement textLogintoViewYourAccounts;
		 
		  public void TextValidate(String text) {		  
			  ValidateText(textLogintoViewYourAccounts, 25, text);
		  
		  }
		  
		  
		  
		  
		 		  
}
