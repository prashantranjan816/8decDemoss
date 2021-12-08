package org.cynergy.automation.commonlib;
import org.cynergy.automation.baselib.BaseLib;
import org.cynergy.automation.utility.Xls_Reader;
import org.testng.annotations.DataProvider;

public class DataFile extends BaseLib{

	
	
	@DataProvider(name = "LoginTestcase")
	public Object getDatafromExcel() {
	
		int rowCount = xlsfile.getRowCount("Login_Testcase");
		int columnCount = xlsfile.getColumnCount("Login_Testcase");
		Object[][] data = new Object[rowCount - 1][columnCount];
		for (int i = 2; i <= rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				data[i - 2][j] = xlsfile.getCellData("Login_Testcase", j, i);
			}
		}
		return data;
	}


	@DataProvider(name = "forgotpasswordTestcase")
	public Object getDatafromExcel_ForGOTPASWWORD() {
	
		int rowCount = xlsfile.getRowCount("Forgot_Testcase");
		int columnCount = xlsfile.getColumnCount("Forgot_Testcase");
		Object[][] data = new Object[rowCount - 1][columnCount];
		for (int i = 2; i <= rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				data[i - 2][j] = xlsfile.getCellData("Forgot_Testcase", j, i);
			}
		}
		return data;
	}

	
}
