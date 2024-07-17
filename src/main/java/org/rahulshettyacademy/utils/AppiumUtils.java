package org.rahulshettyacademy.utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public abstract class AppiumUtils {
	
	
	public AppiumDriverLocalService service;


	public Double getFormattedAmount(String amount)
	{
		Double price = Double.parseDouble(amount.substring(1));
		return price;
	}
	
	public List<HashMap<String,String>> getJsonData(String jsonFilePath) throws IOException{
		//convert Json file content to Json String
		//System.getProperty("user.dir")+"//src//test//java//org//rahulshettyacademy//testData//eCommerce.json"
		String jsonContent=FileUtils.readFileToString(new File(jsonFilePath),StandardCharsets.UTF_8);

		ObjectMapper mapper=new ObjectMapper();

		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){

		});

		return data;

		}
	public AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {
		service = new AppiumServiceBuilder().withAppiumJS(new File("//usr//local//lib//node_modules//appium//build//lib//main.js"))
				.withIPAddress(ipAddress).usingPort(port).build();
			service.start();
			return service;
	}
	
	public void waitForElementToAppear(WebElement ele, AppiumDriver driver) {
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		//wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")),"text" , "Cart"));
				
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		wait.until(ExpectedConditions.attributeContains((ele),"text" , "Cart"));
				
	}
	public String getScreenshotPath(String testCaseName, AppiumDriver driver) throws IOException {
		File source = driver.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir")+"//reports"+testCaseName+".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;
		// 1. Capture and place in folder
		// 2. Pick file and attach to report
	}
}
