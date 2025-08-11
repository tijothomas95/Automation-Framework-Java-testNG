package com.ui.pages;

import org.openqa.selenium.By;
import com.constants.Browser;
import static com.constants.Env.*;
import com.utility.BrowserUtility;
import com.utility.JSONUtility;

public final class HomePage extends BrowserUtility {
	
	private static final By SIGN_IN_LOC = By.cssSelector("a.login[href*=\"my-account\"]");
	
	public HomePage(Browser browserName, boolean isHeadless) {
		super(browserName, isHeadless); // Inheritance: calls parent class constructor from child class constructor
		//goTo(getProperties(DEV, "URL"));
		goTo(JSONUtility.readJSON(DEV).getUrl());
	}
	
	public LoginPage gotoLoginPage() {
		click(SIGN_IN_LOC);
		return new LoginPage(getDriver());
	}

}
