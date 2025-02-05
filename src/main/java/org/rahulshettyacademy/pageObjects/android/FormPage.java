package org.rahulshettyacademy.pageObjects.android;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.rahulshettyacademy.utils.AndroidActions;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends AndroidActions{
	AndroidDriver driver;
	public FormPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	//driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Rahul Shetty");
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	
	//driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Female']")
	private WebElement femaleOption;

	//driver.findElement(By.xpath("//android.widget.RadioButton[@text='Male']")).click();
	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Male']")
	private WebElement maleOption;
		
	//driver.findElement(By.id("android:id/text1")).click();
	@AndroidFindBy(id="android:id/text1")
	private WebElement countrySelection;
	
	//driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement shopButton;
		
	public void setNameField(String name) {
		nameField.sendKeys(name);
		driver.hideKeyboard();
	}
	
	public void setGender(String gender) {
		if(gender.contains("Female")) 
			femaleOption.click();
		else 
			maleOption.click();
	}
	public void setContrySelection(String countryName) {
		countrySelection.click();
		scrollToText(countryName);
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+countryName+"']")).click();
	}
	
	public ProductCatalogue submitForm() {
		shopButton.click();
		return new ProductCatalogue(driver);
	}
	
	public void setActivity()  {
		//screen to home page
				//open the app in simulator and enter this in terminal
				//adb shell dumpsys window | grep -E 'mCurrentFocus'  - MAC
				//adb shell dumpsys window | find "mCurrentFocus"  - Windows
				//com.androidsample.generalstore/com.androidsample.generalstore.MainActivity
				//{package name}/{activity name}
			    //Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.SplashActivity");
				//Deprecated -> driver.startActivity(activity);
				((JavascriptExecutor)driver).executeScript("mobile:startActivity", ImmutableMap.of("intent","com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"));
				
	}
}