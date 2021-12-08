package org.cynergy.automation.baselib;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.cynergy.automation.utility.AppiumServer;
import org.cynergy.automation.utility.PropertyFileReader;
import org.cynergy.automation.utility.Xls_Reader;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseLib extends ExtentReportLib {
	public ExtentTest logger;
	public WebDriverWait wait;
	public AppiumDriver<MobileElement> driver;
	public 	Xls_Reader xlsfile = new Xls_Reader(".\\Test data\\Testdata.xlsx");;
	PropertyFileReader prop = new PropertyFileReader();
	public AppiumServer appiumServer;
	protected ScreenShotLib sLib;
	private int port = Integer.parseInt(prop.readPropFile("PORT"));
	private String server = prop.readPropFile("SERVER");
	

	/**
	 * <h1>Method for the</h1> <b>setup</b> method for start the appium or check the
	 * appium srever is running or not.
	 *
	 * @return void
	 * @param NO
	 * @author sumit.nagar1000066
	 * @throws MalformedURLException
	 * @since 21-11-2021
	 */

	//mention the comments why we are not returning driver  .
	
		
//	@BeforeSuite
	public AppiumDriver<MobileElement> setup() throws MalformedURLException {
		// Start Appium Server
		appiumServer = new AppiumServer(server,port);
		if (!appiumServer.checkIfServerIsRunnning()) {
			appiumServer.startServer();
			// appiumServer.stopServer();
		} else {
			System.out.println("Appium Server already running on Port - " + port);
		}
		// Appium Setup
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Samsung");
		capabilities.setCapability("udid", prop.readPropFile("realdevice")); // DeviceId from "adb devices" command//-3642d788
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "10");
		capabilities.setCapability("skipUnlock", "true");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 3600);
		capabilities.setCapability(MobileCapabilityType.NO_RESET,"false");
		capabilities.setCapability("appPackage", prop.readPropFile("appPackage"));
		capabilities.setCapability("appActivity", prop.readPropFile("appActivity"));
		capabilities.setCapability("noReset", false);
		//System.out.println(("http://"+server+":"+port+"/wd/hub"));
		driver = new AppiumDriver<MobileElement>(new URL("http://"+server+":"+port+"/wd/hub"), capabilities);
		System.out.println("Appium Session ID is  " + driver.getSessionId());
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//System.out.println(driver);
		sLib = new ScreenShotLib();
		return driver;
	}
	
	@AfterMethod
	public void postCondition(ITestResult result) throws IOException {
		
		try {
			Thread.sleep(2000);
			String filename = result.getMethod().getMethodName();
			if (result.getStatus() == ITestResult.SUCCESS) {
				logger.log(Status.PASS, "The Test Case named as : " + result.getName() + " is Passed");
				logger.addScreenCaptureFromPath(sLib.getScreenshot(driver, filename, logger));
			} else if (result.getStatus() == ITestResult.FAILURE) {
				logger.log(Status.FAIL, "The Test Case named as : " + result.getName() + " is Failed");
				logger.log(Status.FAIL, "Test Failure : " + result.getThrowable());
				System.out.println("TESTCASE FAILED =>" + result.getName());
				System.out.println(result.getThrowable());
				logger.addScreenCaptureFromPath(sLib.getScreenshot(driver, filename, logger));

			} else if (result.getStatus() == ITestResult.SKIP) {
				logger.log(Status.SKIP, "The Test Case named as : " + result.getName() + " is Skipped");

			}
		} catch (Exception e) {
			System.out.println(e);
			//reports.flush();
				
		}
		
		reports.flush();// this is neccessary for creating the extends reports.
	}
	
	
	@AfterSuite
	public void teardown() {
		driver.quit();
		try {
		appiumServer.stopServer();
		}catch(NullPointerException e) {
		
		System.out.println("Appium Server is not closed, Kindly stop manually ");
		}
	//	System.out.println(" OPEN THE COMMAND PROMPT");
	//	System.out.print("Enter the below command");
	//	System.out.println("get ID by command" +"netstat  -ano  |  findstr  {PORT_NO}");
	//	System.out.println("taskkill  /F  /PID  {ID}");
	}

}
