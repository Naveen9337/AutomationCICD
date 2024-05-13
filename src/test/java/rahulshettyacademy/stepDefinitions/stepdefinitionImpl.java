package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class stepdefinitionImpl extends BaseTest{
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	public ConfirmationPage confirmationPage;
	public CheckoutPage checkoutPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage = LaunchApplication();
		//code
	}

	
	@Given ("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username,String password)
	{
		productCatalogue = LandingPage.LoginApplication(username, password);
	}
	
	
	@When("^I add prduct (.+) to cart$")
	public void I_add_product_to_Cart(String productName)
	{
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	
	@And("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName) throws InterruptedException
	{
		cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		 checkoutPage = cartPage.CheckoutPage();
		checkoutPage.selectCountry("India");

		confirmationPage = checkoutPage.submitOrder();

	}
	
	
	@Then("{string} page is displayed on Confirmation page")
	public void page_is_displayed_on_Confirmation_page(String string)
	{
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void error_message_is_displayed(String msg)
	{
		
		Assert.assertEquals(msg, LandingPage.getErrorMessage());
		driver.close();
	}
			
	
	
}
