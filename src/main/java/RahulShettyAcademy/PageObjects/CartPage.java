package RahulShettyAcademy.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulShettyAcademy.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy (css=" .cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy (css=".totalRow button")
	WebElement checkout;
	
	public boolean verifyProductDisplay(String productName)
	{
		return cartProducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
	}
	
	public CheckoutPage goToCheckout()
	{
		checkout.click();
		return new CheckoutPage(driver);
	}
	
	
	

}
