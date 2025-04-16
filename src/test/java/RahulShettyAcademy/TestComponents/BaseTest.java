package RahulShettyAcademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import RahulShettyAcademy.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initializeDriver() throws IOException
	{
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\RahulShettyAcademy\\resources\\globalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
//		String browserName = prop.getProperty("browser");
		if(browserName.contains("chrome")) 
		{
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().clearDriverCache().setup();
			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));	
		} else if(browserName.equalsIgnoreCase("firefox")) 
		{
			WebDriverManager.firefoxdriver().clearDriverCache().setup();
			driver = new FirefoxDriver();
		} else if(browserName.equalsIgnoreCase("edge")) 
		{
			WebDriverManager.edgedriver().clearDriverCache().setup();
			driver = new EdgeDriver();
		}	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();	
		return landingPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException
	{
		//read json file to String
		String jsonData = FileUtils.readFileToString(new File (filepath), StandardCharsets.UTF_8);
		//convert String to HashMap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>>data = mapper.readValue(jsonData, new TypeReference<List<HashMap<String, String>>>(){});
		return data;
		
	}
	
	public String screenshotCapture(String testCaseName, WebDriver driver) throws IOException
	{
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File(System.getProperty("user.dir")+"\\reports\\" + testCaseName + ".png"));
		return System.getProperty("user.dir")+"\\reports\\" + testCaseName + ".png";
	}

}
