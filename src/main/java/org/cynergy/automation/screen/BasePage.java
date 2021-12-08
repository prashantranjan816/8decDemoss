package org.cynergy.automation.screen;
import java.net.MalformedURLException;

import org.cynergy.automation.utility.GenericFunctions;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BasePage extends GenericFunctions {
	
	public  BasePage() { 
			try {
				PageFactory.initElements(new AppiumFieldDecorator(setup()), this);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		 
	}

}
