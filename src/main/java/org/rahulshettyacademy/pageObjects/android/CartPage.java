package org.rahulshettyacademy.pageObjects.android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.rahulshettyacademy.utils.AndroidActions;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions{
	AndroidDriver driver;
	Double totalSum;
	Double displayFormattedSum;
	
	//List<WebElement> productPrices =driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
		@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
		private List<WebElement> productList;
	
	//driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText()
		@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
		private WebElement totalAmountOfItems;
		
	//WebElement ele = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
		@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
		private WebElement terms;
		
	//driver.findElement(By.id("android:id/button1")).click();
		@AndroidFindBy(id = "android:id/button1")
		private WebElement acceptButton;
		
	//driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
		@AndroidFindBy(className = "android.widget.CheckBox")
		private WebElement checkBox;
		
	//driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
		@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
		private WebElement proceed;
		
		
		//driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title");
				@AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
				private WebElement cartTitle;
		
	public List<WebElement> getProductList(){
		return productList;
	}
		
	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);//
	}
	
	public double getProductsSum() {
		int count = productList.size();
		double totalSum =0;
		for(int i =0; i< count; i++)
		{
			String amountString =productList.get(i).getText();
			Double price = getFormattedAmount(amountString);
			totalSum = totalSum + price;  //160.97 + 120 =280.97
		}
		return totalSum;
	}
	
	public double getTotalAmountDisplayed() {
		String displaySum =totalAmountOfItems.getText();
		displayFormattedSum = getFormattedAmount(displaySum);
		return displayFormattedSum;
	}
	
	public void verifyCart() {
		Assert.assertEquals(totalSum, displayFormattedSum);
	}
	public void acceptTermsConditions() {
		longPressAction(terms);
		acceptButton.click();
	}
	
	public void subscribe() {
		checkBox.click();
	}
	
	public void submitOrder() {
		proceed.click();
	}
	
	public WebElement getCartTitle() {
		return cartTitle;
	}
}
