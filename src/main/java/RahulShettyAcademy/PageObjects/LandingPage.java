package RahulShettyAcademy.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulShettyAcademy.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy (id="userEmail")
	WebElement userID;

	@FindBy(id = "userPassword")
	WebElement passwordEle;
	
	@FindBy(id ="login")
	WebElement submitButton;
	
	@FindBy (css="div[aria-label*='Incorrect email']")
	WebElement errorMessageEle;
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public ProductCatalogue loginApplication(String username, String password)
	{
		userID.sendKeys(username);
		passwordEle.sendKeys(password);
		submitButton.click();
		return new ProductCatalogue(driver);
	}

	public String getErrorMessage() {
	
		waitForElementToAppearEle(errorMessageEle);
		return errorMessageEle.getText();
	}
	


}
