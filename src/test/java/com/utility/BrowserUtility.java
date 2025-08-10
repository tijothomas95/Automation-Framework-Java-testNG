package com.utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;

import com.constants.Browser;

import io.qameta.allure.Attachment;

public abstract class BrowserUtility {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	Logger logger = LoggerUtility.getLogger(this.getClass());

	public BrowserUtility(WebDriver driver) {
		this.driver.set(driver);
	}

	public BrowserUtility(Browser browserName, boolean isHeadless) {
	    logger.info("Launching browser: {}, headless: {}", browserName, isHeadless);

	    switch (browserName) {
	        case CHROME:
	            ChromeOptions chromeOptions = new ChromeOptions();
	            if (isHeadless) {
	            	chromeOptions.addArguments("--headless=new", "--window-size=1920,1080");
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
	                edgeOptions.addArguments("--headless=new", "--disable-gpu", "--window-size=1920,1080");
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

	public void click(By locator) {
		logger.info("Finding element: {}", locator);
		WebElement elem = findClickable(locator);
		logger.info("Element found, performing click");
		elem.click();
	}

	public void enterText(By locator, String text) {
		logger.info("Finding presence of element: {}", locator);
		WebElement elem = findVisible(locator);
		logger.info("Element found, entering text: {}", text);
		elem.sendKeys(text);
	}

	public String getVisibleText(By locator) {
		logger.info("Finding visibility of element: {}", locator);
		WebElement elem = findPresent(locator);
		logger.info("Element found, returning visible text: {}", elem.getText());
		return elem.getText();
	}

	// Optional if you also want to save locally
	public String getScreenshot(String name) {
	    String screenshotsDir = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator;
	    File dir = new File(screenshotsDir);
	    if (!dir.exists()) dir.mkdirs();

	    String path = screenshotsDir + System.currentTimeMillis() + "_" + name + ".png";
	    File destination = new File(path);

	    try {
	        File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
	        FileUtils.copyFile(src, destination);
	    } catch (IOException e) {
	        logger.error("Failed to capture screenshot: {}", e.getMessage());
	    }
	    return path;
	}

	protected WebElement findPresent(By locator) {
        try {
            return WaitUtils.waitForPresence(getDriver(), locator);
        } catch (TimeoutException e) {
            Assert.fail("Element not present: " + locator);
            return null;
        }
    }

    protected WebElement findVisible(By locator) {
        try {
            return WaitUtils.waitForVisibility(getDriver(), locator);
        } catch (TimeoutException e) {
            Assert.fail("Element not visible: " + locator);
            return null;
        }
    }

    protected WebElement findClickable(By locator) {
        try {
            return WaitUtils.waitForClickability(getDriver(), locator);
        } catch (TimeoutException e) {
            Assert.fail("Element not clickable: " + locator);
            return null;
        }
    }
}
