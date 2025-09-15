package com.ui.pages;

import org.openqa.selenium.By;
import com.constants.Browser;
import com.constants.Constants;
import com.constants.Env;

import com.utility.BrowserUtility;
import com.utility.JSONUtility;
import com.utility.PropertiesUtil;

public final class HomePage extends BrowserUtility {
	
	private static final By SIGN_IN_LOC = By.cssSelector("a.login[href*=\"my-account\"]");
	
	public HomePage(Browser browserName, boolean isHeadless, boolean isSeleniumGrid) {
		super(browserName, isHeadless, isSeleniumGrid); // Inheritance: calls parent class constructor from child class constructor
		//goTo(getEnvProperties(DEV, "URL"));
		
		goTo(JSONUtility.readJSON(Env.valueOf(PropertiesUtil.getDefaultProperty(Constants.ENV))).getUrl());
	}
	
	public LoginPage gotoLoginPage() {
		click(SIGN_IN_LOC);
		return new LoginPage(getDriver());
	}

}
