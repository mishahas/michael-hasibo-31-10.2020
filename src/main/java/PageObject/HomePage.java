package PageObject;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Sleeper;

public class HomePage {
	
	public WebDriver driver;
	
	public HomePage(WebDriver driver) {
		
		this.driver = driver;
	}
	
	private By uploadPopup = By.cssSelector(".onUnloadPopup__ModalWrapper-v34ylr-0");
	private By popupCloseButton = By.cssSelector(".onUnloadPopup__CloseModalBtn-v34ylr-1");
	
    private By footer = By.cssSelector("#footer");
    private By footerNameField = By.cssSelector("div.Footer__InputDiv-sc-159s1ql-4:nth-child(1) > input:nth-child(1)");
	private By footerEmailField = By.cssSelector("div.Footer__InputDiv-sc-159s1ql-4:nth-child(2) > input:nth-child(1)");
    private By footerPhoneNumberField = By.cssSelector("div.Footer__InputDiv-sc-159s1ql-4:nth-child(3) > input:nth-child(1)");
    private By footerSubmitButton = By.cssSelector(".Footer__Button-sc-159s1ql-7");
    private By footerLinksContainer = By.cssSelector(".socialMediaBar__container-sc-1ry1db0-1");
    private By footerNameMissingAlert = By.cssSelector("div.Footer__InputDiv-sc-159s1ql-4:nth-child(1) > label:nth-child(2)");
    private By whatsappButton = By.cssSelector(".callUsWhatsapp__BtnWhatsapp-rkzbui-0");
    private final String slickArrowString = "div.slick-arrow:nth-child(3)";
    private By slideToTheRightButton = By.cssSelector(slickArrowString);
    private By radioButtonsContainer = By.cssSelector("ul.slick-dots:nth-child(4)");
    
    
    
    // getters
    public By getUploadPopup() {
		return uploadPopup;
	}
    
    public WebElement getPopupCloseButton() {
		return driver.findElement(popupCloseButton);
	}
    
    public String getSlickArrowString() {
		return slickArrowString;
	}
    
    public WebElement getSlideToTheRightButton() {
		return driver.findElement(slideToTheRightButton);
	}
    public WebElement getRadioButtonsContainer() {
		return driver.findElement(radioButtonsContainer);
	}
    public WebElement getWhatsappButton() {
		return driver.findElement(whatsappButton);
	}
    
    public WebElement getFooterNameMissingAlert() {
		return driver.findElement(footerNameMissingAlert);
	}
    
    public WebElement getFooterLinksContainer() {
		return driver.findElement(footerLinksContainer);
	}

	public WebElement getFooter() {
		return driver.findElement(footer);
	}
	
	public By getFooterCss() {
		return this.footerNameMissingAlert;
	}
	
	public WebElement getFooterNameField() {
		return driver.findElement(footerNameField);
	}
	
	public WebElement getFooterEmailField() {
		return driver.findElement(footerEmailField);
	}
	
	public WebElement getFooterPhoneNumberField() {
		return driver.findElement(footerPhoneNumberField);
	}
	
	public WebElement getFooterSubmitButton() {
		return driver.findElement(footerSubmitButton);
	}
	
	
	public void footerSubmitData(Properties prop) {
		
		//insert required data 
		getFooterNameField().sendKeys(prop.getProperty("validName"));
		getFooterEmailField().sendKeys(prop.getProperty("validEmail"));
		getFooterPhoneNumberField().sendKeys(prop.getProperty("validPhone"));
		
		//submit
		getFooterSubmitButton().click();
		
	}
	
	public ThankYouPage getThankYouPage() {
		
		//create Thank You Page 
		ThankYouPage thankYouPage = new ThankYouPage(driver);
		return thankYouPage;
	}
	
	
}
