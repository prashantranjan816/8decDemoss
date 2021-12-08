package org.cynergy.automation.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class RandomStringLib {

	static int testcb(String randomString) {
		int randomInt = 0;
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(randomString.length());
		if (randomInt - 1 == -1) {
			return randomInt;
		} else {
			return randomInt - 1;
		}
	}

	public static String generateDateOfBirth() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate = dateFormat.format(date);
		List<String> list = Arrays.asList(currentDate.split("-"));
		int year = Integer.parseInt(list.get(0)) - 18;
		int month = Integer.parseInt(list.get(1));
		int day = Integer.parseInt(list.get(2));
		if (month == 1 && day == 1) {
			year = year - 1;
			month = month + 11;
		}
		if (day >= 2) {
			day = day - 1;
		}
		String m = String.format("%02d", month);
		String d = String.format("%02d", day);

		String dateOfBirth = d + m + String.valueOf(year);

		return dateOfBirth;
	}

	public static String generateMobileNumer() {
		return RandomStringLib.randomNumber(9, 10);
	}

	public static String generatePancard() {
		String pancard = "BAVRT" + RandomStringLib.randomNumber(6, 4) + "P";
		return pancard;
	}

	public static String generateAccountNumber() {
		return RandomStringLib.randomNumber(1, 10);
	}

	public static String randomName(int length) {
		String randomString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int RANDOM_STRING_LENGTH = length;
		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
			int number = testcb(randomString);
			char ch = randomString.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}

	public static String randomEmail() {
		String randomString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		int RANDOM_STRING_LENGTH = 10;
		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
			int number = testcb(randomString);
			char ch = randomString.charAt(number);
			randStr.append(ch);
		}
		randStr.append("@gmail.com");
		return randStr.toString();
	}

	public static String randomNumber(int startingNumber, int numberLimit) {
		String randomString = "1234567890";
		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < (numberLimit - 1); i++) {
			int number = testcb(randomString);
			char ch = randomString.charAt(number);
			randStr.append(ch);
		}
		return startingNumber + randStr.toString();
	}
	public static String getAlphaNumericString(int n) 
	{ 

		
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
									/*+ "0123456789"
									+ "abcdefghijklmnopqrstuvxyz"*/

		// create StringBuffer size of AlphaNumericString 
		StringBuilder sb = new StringBuilder(n); 

		for (int i = 0; i < n; i++) { 

			// generate a random number between 
			// 0 to AlphaNumericString variable length 
			int index 
				= (int)(AlphaNumericString.length() 
						* Math.random()); 

			// add Character one by one in end of sb 
			sb.append(AlphaNumericString 
						.charAt(index)); 
		} 

		return sb.toString(); 
	} 
	public static String getLetters(int n) 
	{ 

		
		String AlphaNumericString = "PFRCAHBJ";
									/*+ "0123456789"
									+ "abcdefghijklmnopqrstuvxyz"*/

		// create StringBuffer size of AlphaNumericString 
		StringBuilder sb = new StringBuilder(n); 

		for (int i = 0; i < n; i++) { 

			// generate a random number between 
			// 0 to AlphaNumericString variable length 
			int index 
				= (int)(AlphaNumericString.length() 
						* Math.random()); 

			// add Character one by one in end of sb 
			sb.append(AlphaNumericString 
						.charAt(index)); 
		} 

		return sb.toString(); 
	} 
}
