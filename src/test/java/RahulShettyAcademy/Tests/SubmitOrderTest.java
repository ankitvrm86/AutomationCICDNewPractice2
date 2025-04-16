package RahulShettyAcademy.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RahulShettyAcademy.PageObjects.CartPage;
import RahulShettyAcademy.PageObjects.CheckoutPage;
import RahulShettyAcademy.PageObjects.ConfirmationPage;
import RahulShettyAcademy.PageObjects.OrdersPage;
import RahulShettyAcademy.PageObjects.ProductCatalogue;
import RahulShettyAcademy.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest{
//	String productName = "ZARA COAT 3";
//	String userName = "ankit86@gmail.com";
//	String password = "Ankit@123";
// Practice CICD Integration
	
	@Test (dataProvider = "getData", groups= {"PurchaseOrder"})
	public void submitOrder(HashMap<String, String> input) throws InterruptedException  {
		
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("userName"), input.get("password"));
		productCatalogue.addToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCart();
		boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.countrySelect("india");
		ConfirmationPage confirmationpage = checkoutPage.submitOrder();
		String confirmMessageText = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMessageText.equalsIgnoreCase("Thankyou for the order."));
	}
	
	@Test (dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest()
	{
		ProductCatalogue productCatalogue = landingPage.loginApplication("ankit86@gmail.com", "Ankit@123");
		OrdersPage ordersPage = productCatalogue.goToOrders();
		boolean match = ordersPage.verifyOrderDisplay("ZARA COAT 3");
		Assert.assertTrue(match);
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
//		HashMap<String, String > map = new HashMap<String, String>();
//		map.put("userName", "ankit86@gmail.com");
//		map.put("password", "Ankit@123");
//		map.put("product", "ZARA COAT 3");
//		HashMap<String, String > map1 = new HashMap<String, String>();
//		map1.put("userName", "shetty@gmail.com");
//		map1.put("password", "Iamking@000");
//		map1.put("product", "ADIDAS ORIGINAL");
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\RahulShettyAcademy\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
		
	}
}
