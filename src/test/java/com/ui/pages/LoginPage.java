package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;

public final class LoginPage extends BrowserUtility {
	
	private static final By EMAIL_LOC = By.cssSelector("input#email");
	private static final By PASSWORD_LOC = By.cssSelector("input#passwd");
	private static final By SIGN_IN_BTN = By.cssSelector("button#SubmitLogin");
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public MyAccountPage doLoginUser(String email, String password) {
		enterText(EMAIL_LOC, email);
		enterText(PASSWORD_LOC, password);
		clickOn(SIGN_IN_BTN);
		return new MyAccountPage(getDriver());
	}

}
