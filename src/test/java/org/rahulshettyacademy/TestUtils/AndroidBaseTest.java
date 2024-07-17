package org.rahulshettyacademy.TestUtils;
import org.testng.annotations.BeforeClass;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.rahulshettyacademy.pageObjects.android.FormPage;
import org.rahulshettyacademy.utils.AppiumUtils;
import org.testng.annotations.AfterClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class AndroidBaseTest extends AppiumUtils{

	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public FormPage formPage;
	@BeforeClass(alwaysRun = true)
	public void ConfigureAppium() throws IOException
	{
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java/org//rahulshettyacademy//resources//data.properties");
			String ipAddress = System.getProperty("ipAddress")!=null ? System.getProperty("ipAddress"):prop.getProperty("ipAddress");
			prop.load(fis);
			String port = prop.getProperty("port");
			String androidDeviceName = prop.getProperty("AndroidDeviceName");
			service = startAppiumServer(ipAddress,Integer.parseInt(port));
				
			UiAutomator2Options options = new UiAutomator2Options();
			options.setDeviceName(androidDeviceName);// real device / emulator
			
			
			//options.setChromedriverExecutable("//Users//rahulshetty//documents//chromedriver 11");
			//ApiDemos app
		    //options.setApp(System.getProperty("user.dir")+"//src//test//java//org//rahulshettyacademy//ApiDemos-debug.apk");	
			//General Store app
			options.setApp(System.getProperty("user.dir")+"//src//test//java//org//rahulshettyacademy//resources//General-Store.apk");
			
		    driver = new AndroidDriver(service.getUrl(), options);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			formPage = new FormPage(driver);
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown()
	{
		driver.quit();
        service.stop();
		}
	
}
