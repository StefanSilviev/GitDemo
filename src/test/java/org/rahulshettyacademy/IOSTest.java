package org.rahulshettyacademy;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.rahulshettyacademy.TestUtils.IOSBaseTest;
import org.rahulshettyacademy.pageObjects.ios.AlertViews;

public class IOSTest extends IOSBaseTest{
	@Test(alwaysRun = true)
	public void IOSBasicsTest()
	{
		//Xpath - XML language - App source
		AlertViews alertViews = homePage.selectAlertViews();
		alertViews.fillTextLabel("Hello World!"); //(AppiumBy.iOSClassChain("**/XCUIElementTypeOther[`name == 'Horizontal scroll bar, 1 page'`]"));
		String actualMessage = alertViews.getConfirmMessage();
		AssertJUnit.assertEquals(actualMessage, "A message should be a short, complete sentence.");
		
	}
}
