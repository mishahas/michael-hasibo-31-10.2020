package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ThankYouPage {
	
	public WebDriver driver;
	
	public ThankYouPage(WebDriver driver) {
			this.driver = driver;
	}
	

    private By ThankYouText = By.cssSelector(".thankYou__title-avz2fr-5");
    
    public WebElement getThankYouText() {
		return driver.findElement(ThankYouText);
	}
}
