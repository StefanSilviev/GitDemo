package org.rahulshettyacademy.utils;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class IOSActions extends AppiumUtils{
	IOSDriver driver;
	public IOSActions(IOSDriver driver) {
 		this.driver = driver;
	}
	public void touchAndHold(WebElement ele, int duration) {
		Map <String, Object>params = new HashMap<>();
		params.put("element", ((RemoteWebElement)ele).getId());
		params.put("duration", duration);
		driver.executeScript("mobile:touchAndHold", params);
	}
	
	public void scroll(WebElement ele, String direction) {
		Map<String, Object> params = new HashMap<>();
		params.put("direction",direction );
		params.put("element",((RemoteWebElement)ele).getId());
		driver.executeScript("mobile:scroll", params); 
	}
	
	public void swipeAction() {
		Map<String,Object> params1 = new HashMap<String, Object>();
		params1.put("direction", "left");
		driver.executeScript("mobile:swipe",params1);

	}
	
}
