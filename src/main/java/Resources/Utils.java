package Resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Utils {
	public WebDriver driver;
	public Properties properties;
	
	// initialize the correct web driver
	public WebDriver driverInitialize(String browserName) throws IOException {
		properties = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/Resources/data.properties");
		properties.load(fis);
		
		// run headless
		ChromeOptions chroneOptions = new ChromeOptions();
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		
		chroneOptions.addArguments("headless");
		firefoxOptions.setHeadless(true);
		
		if(browserName.contains("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/main/java/Resources/webdrivers/chromedriver");
			driver = new ChromeDriver(chroneOptions);
		}
		
		else if(browserName.contains("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/src/main/java/Resources/webdrivers/geckodriver");
			driver = new FirefoxDriver(firefoxOptions);
			
		}
		
		// explicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
		
		
		}
	
	}
