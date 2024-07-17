package org.rahulshettyacademy;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.rahulshettyacademy.TestUtils.AndroidBaseTest;
import org.rahulshettyacademy.pageObjects.android.CartPage;
import org.rahulshettyacademy.pageObjects.android.ProductCatalogue;



public class AndroidTest extends AndroidBaseTest{
	
	
	@BeforeMethod(alwaysRun = true)
	public void preSetup() {
		formPage.setActivity();
		}
	@Test(dataProvider = "getData", groups = {"Smoke"})
	public void FillForm(HashMap<String, String> input) throws InterruptedException 
	{
		formPage.setNameField(input.get("name"));
		formPage.setGender(input.get("gender"));
		formPage.setContrySelection(input.get("country")); 
		ProductCatalogue productCatalogue = formPage.submitForm();
		productCatalogue.addItemToCartByIndex(0);
		productCatalogue.addItemToCartByIndex(0);
		CartPage cartPage  = productCatalogue.goToCartPage();
	    //waitForElementToAppear(cartPage.getCartTitle());
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		//wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")),"text" , "Cart"));
		Double totalSum = cartPage.getProductsSum();
		Double displayFormattedSum = cartPage.getTotalAmountDisplayed();
		AssertJUnit.assertEquals(totalSum, displayFormattedSum);
		cartPage.acceptTermsConditions();
		cartPage.subscribe();
		cartPage.submitOrder();
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String,String>>data = getJsonData(System.getProperty("user.dir")+"//src//test//java//org//rahulshettyacademy//testData//eCommerce.json");
		//return new Object[][] {{ "Rahul Shetty","Female","Argentina" },{ "Shetty Mr","Male","Argentina" } };
		return new Object[][] {{ data.get(0) },{ data.get(1)} };

		// { {hash} {hash} }
	
	}
}
