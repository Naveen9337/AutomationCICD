package rahulshettyacademy.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })

	// This method without Hash Map
	// public void submitOrder(String email,String password,String productName)
	// throws IOException, InterruptedException {
	// TODO Auto-generated method stub
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		// LandingPage LandingPage = LaunchApplication();

		// This Method is with arrays and not HashMap
		// ProductCatalogue productCatalogue = LandingPage.LoginApplication(email, password);
		ProductCatalogue productCatalogue = LandingPage.LoginApplication(input.get("email"), input.get("password"));
		// Extract Card Name from Shopping page
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.CheckoutPage();
		checkoutPage.selectCountry("India");

		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	// tr td:nth-child(3)
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalogue productCatalogue = LandingPage.LoginApplication("naveen.naveen11@gmail.com", "L-FRsh86#");
		OrderPage orderPage =productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
	}
	
	
// This is approch through arrays
//	@DataProvider
//	public Object[][] getData()
//	{
//		return new Object[][] {{"naveen.naveen11@gmail.com", "L-FRsh86#","ZARA COAT 3"},{"naveen.naveen12@gmail.com", "L-FRsh86#","ADIDAS ORIGINAL"}};
//	}



//This Approach is through Hash Map
 // @DataProvider 
//  public Object[][] getData() 
//  {  
//	  HashMap<String,String>  map = new HashMap<String, String>(); 
//  map.put("email","naveen.naveen11@gmail.com"); 
//  map.put("password", "L-FRsh86#");
//  map.put("product", "ZARA COAT 3");
//  HashMap<String,String> map1 = new HashMap<String, String>(); 
//  map1.put("email", "naveen.naveen12@gmail.com");
//  map1.put("password", "L-FRsh86#"); 
//  map1.put("product","ADIDAS ORIGINAL");
//  return new Object[][] {{map},{map1}}; }
//  }
//  
	//This Approach is through json external file
	 @DataProvider 
	 public Object[][] getData() throws IOException 
 {  
		 List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") +"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		 

	  return new Object[][] {{data.get(0)},{data.get(1)}};
	  }
}
 