package RahulShettyAcademy.StepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import RahulShettyAcademy.PageObjects.CartPage;
import RahulShettyAcademy.PageObjects.CheckoutPage;
import RahulShettyAcademy.PageObjects.ConfirmationPage;
import RahulShettyAcademy.PageObjects.LandingPage;
import RahulShettyAcademy.PageObjects.ProductCatalogue;
import RahulShettyAcademy.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinition extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationpage;
	
	@Given ("I landed on Ecommerce Website")
	public void i_landed_on_Ecommerce_Website() throws IOException
	{
		landingPage = launchApplication();
	}
	
	@Given("^I logged in with username (.+) and password (.+)$")
	public void i_logged_in_with_username_and_password(String username, String password)
	{
		productCatalogue = landingPage.loginApplication(username, password);
	}
	
	@When("^I add the product (.+) to Cart$")
	public void i_add_product_to_cart(String productName)
	{
		productCatalogue.addToCart(productName);
	}
	
	@And ("^I checkout with product (.+) and submit the order$")
	public void i_checkout_with_product_and_submit_the_order(String productName) throws InterruptedException
	{
		CartPage cartPage = productCatalogue.goToCart();
		boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.countrySelect("india");
		confirmationpage = checkoutPage.submitOrder();
	}
	
	@Then ("{string} message is displayed on the Confirmation Page")
	public void message_is_displayed_on_the_Confirmation_Page(String message)
	{
		String confirmMessageText = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMessageText.equalsIgnoreCase(message));
		driver.close();
	}
	
	@Then ("{string} error message is displayed and user can not login")
	public void Error_Message_is_displayed_and_user_can_not_login(String message)
	{
		Assert.assertEquals(landingPage.getErrorMessage(), message);
		driver.close();
	}
}
