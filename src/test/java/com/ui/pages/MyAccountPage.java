package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;

public final class MyAccountPage extends BrowserUtility {
	
	private static final By USER_NAME_LOC = By.cssSelector("div>a.account>span");

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	public String getUserNameDisplayed() {
		return getVisibleText(USER_NAME_LOC);
	}

}
