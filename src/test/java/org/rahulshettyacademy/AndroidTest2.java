package org.rahulshettyacademy;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;


import io.appium.java_client.AppiumBy;
import org.rahulshettyacademy.TestUtils.AndroidBaseTest;


public class AndroidTest2 extends AndroidBaseTest{

	@BeforeMethod(alwaysRun = true)
	public void preSetup() {
		formPage.setActivity();
		}
	
	@Test
	public void FillForm_ErrorValidation() throws InterruptedException
	{
		
	//	driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Rahul Shetty");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
		AssertJUnit.assertEquals(toastMessage,"Please enter your name");
		
	}
	@Test
	public void FillForm_PositiveFlow() throws InterruptedException
	{
		
	 	driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Rahul Shetty");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		AssertJUnit.assertTrue(driver.findElements(By.xpath("(//android.widget.Toast)[1]")).size()<1);
		
	}
}
