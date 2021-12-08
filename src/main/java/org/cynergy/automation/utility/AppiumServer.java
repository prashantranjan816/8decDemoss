package org.cynergy.automation.utility;

import java.io.IOException;
import java.net.ServerSocket;

import org.cynergy.automation.baselib.BaseLib;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumServer  extends BaseLib{
	
	public AppiumServer(String server, int port){
		this.server = server;
		this.port = port;
	}

	public AppiumDriverLocalService service;
	private AppiumServiceBuilder builder;
	private DesiredCapabilities cap;
	private int port;
	private String server ;
	
	public void startServer() {
		//Set Capabilities
		cap = new DesiredCapabilities();
		this.port =port;
		//cap.setCapability("noReset", "true");
		
		//Build the Appium service
		builder = new AppiumServiceBuilder();
		builder.withIPAddress(server);
		builder.usingPort(port);
		builder.withCapabilities(cap);
		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");
		
		//Start the server with the builder
		service = AppiumDriverLocalService.buildService(builder);
		service.start();
		System.out.println("Appium Server started  http://" +server+" " + port+"/wd/hub");
	}
	
	public void stopServer() {
		//System.out.println(service);
		service.stop();
		System.out.println("Appium Server Stopped Successfully on PORT " + port);
	}

	public boolean checkIfServerIsRunnning() {
		
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			//If control comes here, then it means that the port is in use
			isServerRunning = true;
			
		} finally {
			serverSocket = null;
		}
		
		return isServerRunning;
	}	

}
