package RahulShettyAcademy.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import RahulShettyAcademy.PageObjects.CartPage;
import RahulShettyAcademy.PageObjects.OrdersPage;

public class AbstractComponent {
	
	WebDriver driver;
	public AbstractComponent(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy (css="[routerlink*='cart']")
	WebElement cartButton;
	
	@FindBy (css ="[routerlink*='myorders']")
	WebElement orderHistory;
	
	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));	
	}
	
	public void waitForElementToAppearEle(WebElement findEle)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findEle));
		
	}
	public void waitForElementToDisappearEle(WebElement findEle)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(findEle));
		
	}
	
	public CartPage goToCart()
	{
		cartButton.click();
		return new CartPage(driver);
	}
	
	public OrdersPage goToOrders()
	{
		orderHistory.click();
		return new OrdersPage(driver);
	}

}
