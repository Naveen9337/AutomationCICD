package rahulshettyacademy.tests;

import java.io.IOException;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer= Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		// LandingPage LandingPage = LaunchApplication();
		ProductCatalogue productCatalogue = LandingPage.LoginApplication("naveen.naveen1@gmail.com", "L-FRsh86#");
		// Extract Card Name from Shiopping page
		Assert.assertEquals("Incorrect email  password.", LandingPage.getErrorMessage());

		// div[@aria-label='Incorrect email or password.']

	}

	@Test(groups= {"ErrorHandling"})
	public void ProductErrorValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		// LandingPage LandingPage = LaunchApplication();
		ProductCatalogue productCatalogue = LandingPage.LoginApplication("naveen.naveen12@gmail.com", "L-FRsh86#");
		// Extract Card Name from Shiopping page
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);

	}

}
