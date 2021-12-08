package org.cynergy.automation.utility;

import java.time.Duration;

import org.cynergy.automation.baselib.BaseLib;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.aventstack.extentreports.Status;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

/**
 * <h1>GENEREIC FUNCTIONS</h1> The class contains all the generic functions that
 * we would use across the applications
 * 
 * @author sumit Nagar
 * @version 1.0
 * @since 20-11-2021
 */
public class GenericFunctions extends BaseLib {

	/**
	 * Performs swipe from the center of screen
	 * 
	 * @author sumit.nagar1000066
	 * @param dir
	 *            the direction of swipe
	 * @version java-client: 7.3.0
	 * @throws InterruptedException
	 **/
	public void swipeScreen(Direction dir) throws InterruptedException {
		Thread.sleep(5000);
		System.out.println("swipeScreen(): dir: '" + dir + "'"); // always log your actions

		// Animation default time:
		// - Android: 300 ms
		// - iOS: 200 ms
		// final value depends on your app and could be greater
		final int ANIMATION_TIME = 200; // ms

		final int PRESS_TIME = 200; // ms

		int edgeBorder = 10; // better avoid edges
		PointOption pointOptionStart, pointOptionEnd;

		// init screen variables
		Dimension dims = driver.manage().window().getSize();

		// init start point = center of screen
		pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);

		switch (dir) {
		case DOWN: // center of footer
			pointOptionEnd = PointOption.point(dims.width / 2, dims.height - edgeBorder);
			break;
		case UP: // center of header
			pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
			break;
		case LEFT: // center of left side
			pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);
			break;
		case RIGHT: // center of right side
			pointOptionEnd = PointOption.point(dims.width - edgeBorder, dims.height / 2);
			break;
		default:
			throw new IllegalArgumentException("swipeScreen(): dir: '" + dir + "' NOT supported");
		}

		// execute swipe using TouchAction
		try {
			new TouchAction(driver).press(pointOptionStart)
					// a bit more reliable when we add small wait
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME))).moveTo(pointOptionEnd).release()
					.perform();
		} catch (Exception e) {
			System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
			return;
		}

		// always allow swipe action to complete
		try {
			Thread.sleep(ANIMATION_TIME);
		} catch (InterruptedException e) {
			// ignore
		}
	}

	public enum Direction {
		UP, DOWN, LEFT, RIGHT;
	}

	public void ClickElement(MobileElement element, int seconds, String message) {
		wait = new WebDriverWait(driver, seconds);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element)).click();

			System.out.println("Clicked on " + message);
			logger.log(Status.INFO, "Clicked on " + message);
		} catch (Exception e) {
			Assert.fail(message + " Not clickable " + " ERROR MESSAGE " + e.getMessage());
		}
	}

	public void ValidateText(MobileElement element, int seconds, String message) {
		wait = new WebDriverWait(driver, seconds);
		try {
			wait.until(ExpectedConditions.textToBePresentInElement(element, message));
			logger.log(Status.PASS, "Text - ' " + message + " ' available on the screen ");
		} catch (Exception e) {
			Assert.fail(message + " Text Not Present " + " ERROR MESSAGE " + e.getMessage());
		}
	}

	public void EnterText(MobileElement element, int seconds, String text) {
		wait = new WebDriverWait(driver, seconds);
		try {
			wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
			System.out.println("Enter Text " + text);
			logger.log(Status.INFO, "Text ' " + text + " ' entered on the screen ");
		} catch (Exception e) {
			Assert.fail("unable to enter the text in " + element + " ERROR MESSAGE " + e.getMessage());
		}
	}

	public boolean ValidateButtonText(String buttonName, int seconds, String text, String SheetName, int row) {
		wait = new WebDriverWait(driver, seconds);
		try {
			if (driver.findElementByXPath("//android.widget.TextView[@text ='" + buttonName + "']").getText().equalsIgnoreCase(text)) {
				System.out.println("Button " + buttonName + " is Visible");
				logger.log(Status.PASS, buttonName + " Button is visible on the screen ");
				logger.addScreenCaptureFromPath(sLib.getScreenshot(driver, buttonName, logger));
				xlsfile.setCellData("Login_Testcase", "Result", row + 1, "PASS");

			}
			return true;
		} catch (Exception e) {
			Assert.fail("Button " + buttonName + " is not visible " + " ERROR MESSAGE " + e.getMessage());
			logger.addScreenCaptureFromPath(sLib.getScreenshot(driver, buttonName, logger));
			xlsfile.setCellData("Login_Testcase", "Result", row + 1, "FAIL");
			return false;
		}

	}

	public boolean ValidateButtonText(String buttonName, int seconds, String text) {
		wait = new WebDriverWait(driver, seconds);
		try {
			if (driver.findElementByXPath("//android.widget.TextView[@text ='" + buttonName + "']").getText()
					.equalsIgnoreCase(text)) {
				System.out.println("Button " + buttonName + " is Visible");
				logger.log(Status.PASS, buttonName + " Button is visible on the screen ");
				logger.addScreenCaptureFromPath(sLib.getScreenshot(driver, buttonName, logger));

			}
			return true;
		} catch (Exception e) {
			Assert.fail("Button " + buttonName + " is not visible " + " ERROR MESSAGE " + e.getMessage());
			logger.addScreenCaptureFromPath(sLib.getScreenshot(driver, buttonName, logger));
			return false;
		}

	}

}
