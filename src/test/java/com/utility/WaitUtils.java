package com.utility;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import com.constants.Env;
import java.time.Duration;

public class WaitUtils {
	private static final Env env = Env.DEV;
    private static final int TIMEOUT = Integer.parseInt(PropertiesUtil.getProperties(env, "EXPLICIT_TIMEOUT"));
    private static final int POLL_INTERVAL = Integer.parseInt(PropertiesUtil.getProperties(env, "FLUENT_WAIT_TIMEOUT"));
    
    public static WebElement waitForPresence(WebDriver driver, By locator) {
        return waitForPresence(driver, locator, TIMEOUT);
    }

    public static WebElement waitForPresence(WebDriver driver, By locator, int timeout) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(POLL_INTERVAL))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static WebElement waitForVisibility(WebDriver driver, By locator) {
        return waitForVisibility(driver, locator, TIMEOUT);
    }

    public static WebElement waitForVisibility(WebDriver driver, By locator, int timeout) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(POLL_INTERVAL))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickability(WebDriver driver, By locator) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(TIMEOUT))
                .pollingEvery(Duration.ofMillis(POLL_INTERVAL))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
}
