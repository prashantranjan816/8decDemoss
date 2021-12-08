package org.cynergy.automation.baselib;

import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ScreenShotLib extends BaseLib {

	/**
	 * <h1>Method for the</h1> <b>setup</b> method for take the screenshot.
	 * @return void
	 * @param NO
	 * @author sumit.nagar1000066
	 * @since 21-11-2021
	 */

	public String getScreenshot(RemoteWebDriver driver, String FileName, ExtentTest logger) {

		String screenShotFilePath = propertiesFile().getProperty("Screenshotfolder");
		String screenShotFolderName;

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		List<String> DateFormat = Arrays.asList(dateFormat.format(date).split(" "));

		String name = (DateFormat.get(0) + "(" + DateFormat.get(1) + ")").replace(":", "-");
		screenShotFolderName = screenShotFilePath + "\\" + "screenShots-" + DateFormat.get(0);
		
		String destPath = screenShotFolderName + "\\" + name + "-" + FileName + ".png";

		File file = new File(screenShotFolderName);
		if (!file.isDirectory()) {
			boolean created = file.mkdirs();
			if (created) {
			//	System.out.println("ScreenShot folder is created !");
			} else {
				System.out.println("Unable to create ScreenShot");
			}
		} else {
			//System.out.println("ScreenShot folder already created !");
		}

		File destfile = null;
		try {
			EventFiringWebDriver efw = new EventFiringWebDriver(driver);
			File srcfile = efw.getScreenshotAs(OutputType.FILE);
			destfile = new File(destPath);

			FileUtils.copyFile(srcfile, destfile);
		} catch (IOException e) {
			System.out.println(e);

			e.printStackTrace();
		}

		return destfile.getAbsolutePath();

	}
}
