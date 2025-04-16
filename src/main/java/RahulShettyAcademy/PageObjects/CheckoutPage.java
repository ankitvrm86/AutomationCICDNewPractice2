package RahulShettyAcademy.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulShettyAcademy.AbstractComponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	WebDriver driver;
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy (css=".user__name input[placeholder='Select Country']")
	WebElement countryTextBox;
	@FindBy (css=".ta-item:nth-of-type(2)")
	WebElement countrySelect;
	@FindBy (css= ".actions a")
	WebElement submitOrderButton;
	By countryNameList = By.cssSelector(".ta-item");
	
	public void countrySelect(String countryName) throws InterruptedException
	{
		countryTextBox.sendKeys(countryName);
		waitForElementToAppear(countryNameList);
		countrySelect.click();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,700)");
		Thread.sleep(2000);
	}
	
	public ConfirmationPage submitOrder()
	{
		submitOrderButton.click();
		return new ConfirmationPage(driver);
	}
	
	

}
	