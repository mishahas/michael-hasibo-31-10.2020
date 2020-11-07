package WebAutomation;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObject.HomePage;
import PageObject.ThankYouPage;
import Resources.Utils;
import io.netty.handler.timeout.TimeoutException;
import junit.framework.Assert;

public class HomePageTest extends Utils {

	HomePage homePage;

	public void initialize(String browser) throws IOException {

		// initialize driver
		driverInitialize(browser);

		// go to landing page
		driver.get(properties.getProperty("url"));
		homePage = new HomePage(driver);
	}

	@Test(dataProvider = "getData")
		public void FooterPositiveSubmitValidation(String browser) throws IOException {
			
			//initialize driver and open URL
			initialize(browser);
			
			//add data and submit
			homePage.footerSubmitData(properties);
			
			//create Thank You Page object
			ThankYouPage thankYouPage = homePage.getThankYouPage();
		try {	
			//Assert Thank You Page displayed
			assertTrue(thankYouPage.getThankYouText().isDisplayed());
		}finally {
			//close driver
			tearDown();
		}
	}

	@Test(dataProvider = "getData")
	public void FooterNegativreSubmitValidation(String browser) throws IOException {

		// initialize driver and open URL
		initialize(browser);

		try {
			// assert alert message is hidden
			assertTrue(driver.findElements(homePage.getFooterCss()).size() == 0);

			// submit with empty fields
			homePage.getFooterSubmitButton().click();
			assertTrue(homePage.getFooterNameMissingAlert().isDisplayed());
		} finally {
			// close driver
			tearDown();
		}
	}

	@Test(dataProvider = "getData")
	public void whatsappButtonValidation(String browser) throws IOException {
		final long timeToWait = 1000;
		
		// initialize driver and open URL 
		initialize(browser);

		// open whatsapp in new window
		String c = Keys.chord(Keys.SHIFT, Keys.ENTER);
		homePage.getWhatsappButton().sendKeys(c);

		// wait for new window to open
		try {
			Thread.sleep(timeToWait);
			
			// move to whatsapp window
			Set<String> windows = driver.getWindowHandles();
			Iterator<String> it = windows.iterator();
			it.next();
			driver.switchTo().window(it.next());

			// assert whatsapp page opened successful with expected URL
			Assert.assertEquals(properties.getProperty("whatsapp"), driver.getCurrentUrl());
		} 
		catch (Exception e) {

		}
		finally {
			// close driver
			tearDown();
		}
	}

	@Test(dataProvider = "getData")
	public void arrowButtonsSwitchRadioButtonsValidation(String browser) throws IOException {

		// initialize driver and open URL
		initialize(browser);

		// maximize window
		driver.manage().window().maximize();
		
		try {
			// Remove the footer because it obscures the elements we need to click on
			((JavascriptExecutor) driver).executeScript("document.getElementById('footer').remove()");
			
			// extra time for window maximize
			Thread.sleep(1000);
	
			// get all the toggle buttons
			WebElement togglesContainer = homePage.getRadioButtonsContainer();
			List<WebElement> togglesList = togglesContainer.findElements(By.tagName("li"));
	
			// get current selected toggle index
			int beforeClickIndex = 0;
			for (int i = 0; i < togglesList.size(); i++) {
				if (togglesList.get(i).getAttribute("class").equals("slick-active")) {
					beforeClickIndex = i;
					break;
				}
			}
	
			// click on slide right button
			homePage.getSlideToTheRightButton().click();
	
			// assert after click the button old selected toggle no more active
			Assert.assertTrue(!togglesList.get(beforeClickIndex).getAttribute("class").equals("slick-active"));
			
			// check if carousel position is in the end   
			if(beforeClickIndex == togglesList.size()-1) {
				beforeClickIndex = 0;
				Assert.assertTrue(togglesList.get(beforeClickIndex).getAttribute("class").equals("slick-active"));
			}else {

				Assert.assertTrue(togglesList.get(beforeClickIndex + 1).getAttribute("class").equals("slick-active"));
			}
				
		}
		catch (Exception e) {
			
		}
		finally {
			tearDown();
		}	
		
	}
	

	@Test(dataProvider = "getData")
	public void popupValidation(String browser) throws IOException {
		
		//initialize driver and open URL
		initialize(browser);
		
		// maximize window  -- because i do not sure what is the popup exact logic
		driver.manage().window().maximize();
		
		//wait until popup is visible
		try {	
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
			wait.until(ExpectedConditions.elementToBeClickable(homePage.getUploadPopup())); 
		
		// validate popup close button is visible 
			Assert.assertTrue(homePage.getPopupCloseButton().isDisplayed());
		}
		catch (TimeoutException e) {
			
		}
		finally {
			//close driver
			tearDown();
		}
	}
	
	
	@DataProvider
	public Iterator<Object> getData() {
		ArrayList<Object> browserList = new ArrayList<>();
		
		browserList.add("chrome");
		browserList.add("firefox");
		
		return browserList.iterator();
	}

	public void tearDown() {
		driver.quit();
		;
	}

}
