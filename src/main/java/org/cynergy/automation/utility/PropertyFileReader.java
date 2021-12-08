
/*
$Id: PropertyFileReader.java 190 2015-05-25 05:39:49Z 812203 $
Copyright 2012 iGATE GROUP OF COMPANIES. All rights reserved
(Subject to Limited Distribution and Restricted Disclosure Only.)
THIS SOURCE FILE MAY CONTAIN INFORMATION WHICH IS THE PROPRIETARY 
 INFORMATION of iGATE GROUP OF COMPANIES AND IS INTENDED FOR USE 
 ONLY BY THE ENTITY WHO IS ENTITLED TO AND MAY CONTAIN 
 INFORMATION THAT IS PRIVILEGED, CONFIDENTIAL, OR EXEMPT FROM         
 DISCLOSURE UNDER APPLICABLE LAW.
YOUR ACCESS TO THIS SOURCE FILE IS GOVERNED BY THE TERMS AND           
 CONDITIONS OF AN AGREEMENT BETWEEN YOU AND IGATE GROUP OF COMPANIES.                  
 The USE, DISCLOSURE REPRODUCTION OR TRANSFER OF THIS PROGRAM IS      
 RESTRICTED AS SET FORTH THEREIN.
*/

package org.cynergy.automation.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <h1>PropertyFileReader!</h1> The Purpose of this Class is to read and write
 * property files.
 * <p>
 * <b>Note:</b> Giving proper comments in your program makes it more user
 * friendly and it is assumed as a high quality code.
 * 
 * @author sumit.nagar1000066
 * @version 2.0
 * @since 21-11-2021
 */
public class PropertyFileReader {

	/**
	 * Read Configuration Property file
	 * 
	 * @return value of the key
	 */
	public String readPropFile(String key) {
		Properties prop = new Properties();
		InputStream input = null;
		String getProp = null;
		try {
			input = new FileInputStream("./Configration Files/configuration.properties");
			// load a properties file
			prop.load(input);
			// get the property value and print it out
			getProp = prop.getProperty(key);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return getProp;
	}

	/**
	 * Read Parallel Property file //used for Parallel execution
	 * @author sumit.nagar1000066
	 * @return value of the key
	 * @since 21-11-2021
	 */
	public String readPara(String key) {
		Properties prop = new Properties();
		InputStream input = null;
		String getProp = null;
		try {

			input = new FileInputStream("files/parallel.properties");
			// load a properties file
			prop.load(input);
			// get the property value and print it out
			getProp = prop.getProperty(key);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return getProp;
	}


	public String readPropFile(String key, String PropFile) {
		Properties prop = new Properties();
		InputStream input = null;
		String getProp = null;
		try {
			input = new FileInputStream(PropFile);
			// load a properties file
			prop.load(input);
			// get the property value and print it out
			getProp = prop.getProperty(key);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return getProp;
	}

}
