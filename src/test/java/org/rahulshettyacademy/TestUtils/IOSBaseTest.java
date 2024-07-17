package org.rahulshettyacademy.TestUtils;

import org.testng.annotations.BeforeClass;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.rahulshettyacademy.pageObjects.ios.HomePage;
import org.rahulshettyacademy.utils.AppiumUtils;
import org.testng.annotations.AfterClass;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class IOSBaseTest extends AppiumUtils{

	public IOSDriver driver;
	public AppiumDriverLocalService service;
	public HomePage homePage;
	@BeforeClass(alwaysRun = true)
	public void ConfigureAppium() throws IOException
	{
		
		
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java/org//rahulshettyacademy//resources//data.properties");
		
		prop.load(fis);
		String ipAddress = prop.getProperty("ipAddress");
		String port = prop.getProperty("port");
		String iosDeviceName = prop.getProperty("IosDeviceName");
		String IosPlatformVersion = prop.getProperty("IosPlatformVersion");
		
		service = startAppiumServer(ipAddress,Integer.parseInt(port));
		XCUITestOptions options = new XCUITestOptions();
		options.setDeviceName(iosDeviceName);
		//UIKitCatalog app
		options.setApp(System.getProperty("user.dir")+"/src/test/java/org/rahulshettyacademy/resources/UIKitCatalog.app");
		//TestApp app
		//options.setApp(System.getProperty("user.dir")+"//src//test//java//org//rahulshettyacademy//resources//TestApp 3.app");
		options.setPlatformVersion(IosPlatformVersion);
		//Appium- Webdriver Agent -> IOS Apps
		options.setWdaLaunchTimeout(Duration.ofSeconds(1000));
				
		driver = new IOSDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		homePage = new HomePage(driver);
	}
	
	
	
	
	@AfterClass(alwaysRun = true)
	public void tearDown()
	{
		driver.quit();
        service.stop();
		}
	
}
