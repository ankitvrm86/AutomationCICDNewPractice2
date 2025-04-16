package RahulShettyAcademy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulShettyAcademy.AbstractComponent.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	
	WebDriver driver;
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy (css=".toast-message")
	WebElement toastMessageEle;
	
	By productsBy = By.cssSelector(".mb-3");
	By desiredProductEle= By.cssSelector(".card-body h5 b");
	By cartBy = By.cssSelector(".card-body button:last-of-type");
	By toastContainerBy = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductsList ()
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getDesiredProductEle (String productName)
	{
		WebElement prod = getProductsList().stream().filter(product->product.findElement(desiredProductEle).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addToCart(String productName)
	{
		WebElement prod = getDesiredProductEle (productName);
		prod.findElement(cartBy).click();
		waitForElementToAppear(toastContainerBy);
		waitForElementToDisappearEle(toastMessageEle);
	}

}
