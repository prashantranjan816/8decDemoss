package org.cynergy.automation.baselib;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class ExtentReportLib {
	public ExtentReports reports; // Every method append in extent report
	public ExtentSparkReporter htmlReporter;
	 String reportName;

	public String reportFilePath = propertiesFile().getProperty("ReportFolder");
	String reportFolderName;
	public ExtentTest logger = null;
	//public filename;

	@BeforeClass
	public void extendssetup() throws IOException {

		String log4jConfPath = System.getProperty("user.dir") + "\\Configration Files\\log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		List aa = Arrays.asList(dateFormat.format(date).split(" "));

		String name = (aa.get(0) + "(" + aa.get(1) + ")").replace(":", "-");
		reportFolderName = reportFilePath + "\\" + "Extent-Report-" + aa.get(0);
		reportName = reportFolderName + "\\ Extent-Report-" + "" + " - " + name + ".html";
		// System.out.println(reportName);
		File file = new File(reportFolderName);
		if (!file.isDirectory()) {
			boolean created = file.mkdirs();

			if (created) {
				//System.out.println("Extent report folder was created !");
			} else {
			//	System.out.println("Unable to create extent folder");
			}
		} else {

			//System.out.println("Extent report folder already created !");
		}
		htmlReporter = new ExtentSparkReporter(new File(reportName));
		
		htmlReporter.loadXMLConfig(new File(System.getProperty("user.dir") + "\\Configration Files\\extent-config.xml"));
		
		htmlReporter.config().setOfflineMode(true);//.setChartVisibilityOnOpen(true);
		// if (reports == null) {
		//htmlReporter.config()..setAppendExisting(true);
		reports = new ExtentReports();
		reports.setSystemInfo("Environment", propertiesFile().getProperty("ENV"));
		reports.setSystemInfo("Operating System", System.getProperty("os.name"));
		reports.setSystemInfo("Buid Version", propertiesFile().getProperty("BUILD_NO"));
		reports.setSystemInfo("Application ", "CYNERGY");
		reports.setSystemInfo("Developer", "TOP Gear Team");
		
		reports.attachReporter(htmlReporter);
		// }
	}

	/*
	 * @AfterClass public void cleanup() {
	 * 
	 * reports.flush(); }
	 */
	public static Properties propertiesFile() {

		File file = new File(System.getProperty("user.dir") + "\\Configration Files\\configuration.properties");
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
}
