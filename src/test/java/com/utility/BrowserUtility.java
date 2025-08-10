package com.utility;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.constants.Browser;

public abstract class BrowserUtility {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	Logger logger = LoggerUtility.getLogger(this.getClass());

	
	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver); // Constructors initialize instance variable
	}
	
	// Adding another way to create BrowserUtility constructor using browser names
	public BrowserUtility(Browser browserName, boolean isHeadless) {
	    logger.info("Launching browser: " + browserName + ", headless: " + isHeadless);

	    switch (browserName) {
	        case CHROME:
	            ChromeOptions chromeOptions = new ChromeOptions();
	            if (isHeadless) {
	            	chromeOptions.addArguments("--headless=new");
	                chromeOptions.addArguments("--window-size=1920,1080");
	            }
	            driver.set(new ChromeDriver(chromeOptions));
	            break;

	        case FIREFOX:
	            FirefoxOptions firefoxOptions = new FirefoxOptions();
	            if (isHeadless) {
	            	firefoxOptions.addArguments("--headless"); 
	            }
	            driver.set(new FirefoxDriver(firefoxOptions));
	            break;

	        case EDGE:
	            EdgeOptions edgeOptions = new EdgeOptions();
	            if (isHeadless) {
	                edgeOptions.addArguments("--headless=new"); // works with recent Edge (Chromium-based)
	                edgeOptions.addArguments("--disable-gpu");
	                edgeOptions.addArguments("--window-size=1920,1080");
	            }
	            driver.set(new EdgeDriver(edgeOptions));
	            break;

	        default:
	            throw new RuntimeException("Invalid browserName: " + browserName);
	    }
	}

	public WebDriver getDriver() {
		return driver.get();
	}
	
	public void goTo(String url) {
		getDriver().get(url);
	}
	
	public void maximizeWindow() {
		getDriver().manage().window().maximize();
	}
	
	public void clickOn(By locator) {
		logger.info("Finding element: " + locator);
		WebElement elem = getDriver().findElement(locator);
		logger.info("Element found, performing click");
		elem.click();
	}
	
	public void enterText(By locator, String text) {
		logger.info("Finding element: " + locator);
		WebElement elem = getDriver().findElement(locator);
		logger.info("Element found, entering text: " + text);
		elem.sendKeys(text);
	}
	
	public String getVisibleTest(By locator) {
		logger.info("Finding element: " + locator);
		WebElement elem = getDriver().findElement(locator);
		logger.info("Element found, returning visible text: " + elem.getText());
		return elem.getText();
	}

}
