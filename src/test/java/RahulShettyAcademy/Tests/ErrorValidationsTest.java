package RahulShettyAcademy.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import RahulShettyAcademy.PageObjects.CartPage;
import RahulShettyAcademy.PageObjects.ProductCatalogue;
import RahulShettyAcademy.TestComponents.BaseTest;

public class ErrorValidationsTest extends BaseTest{
	String productName = "ZARA COAT 3";
	String userName = "ankit86@gmail.com";
	String password = "Ankit@123";
	
	@Test (groups = {"ErrorHandling"}, retryAnalyzer=RahulShettyAcademy.TestComponents.Retry.class)
	public void loginErrorValidation() throws InterruptedException 
	{
			String userName = "ankit6@gmail.com";
			String password = "Ankit@123";
			
			landingPage.loginApplication(userName, password);
			String errorMessage = landingPage.getErrorMessage();
			Assert.assertEquals(errorMessage, "Incorrect email or password.");
	}
	
	@Test 
	public void productErrorValidation()
	{
				
		ProductCatalogue productCatalogue = landingPage.loginApplication(userName, password);
		productCatalogue.addToCart(productName);
		CartPage cartPage = productCatalogue.goToCart();
		boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}
	
	

}
