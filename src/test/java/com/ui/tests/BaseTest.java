package com.ui.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.constants.Browser;
import com.constants.Constants;
import com.ui.pages.HomePage;
import com.utility.PropertiesUtil;

public class BaseTest {
	protected HomePage homePg;

	
	@BeforeSuite
	public void setupConfig() {
		PropertiesUtil.initDefaultProperties();
	}
	
	@Parameters({"browser", "isHeadless", "isSeleniumGrid"}) // Parameter config in the testNG.xml
	@BeforeMethod
	public void setup(
	        @Optional String browser,
	        @Optional String isHeadless,
	        @Optional String isSeleniumGrid) {

	    browser = (browser != null) ? browser : PropertiesUtil.getDefaultProperty(Constants.BROWSER);
	    isHeadless = (isHeadless != null) ? isHeadless : PropertiesUtil.getDefaultProperty(Constants.IS_HEADLESS);
	    isSeleniumGrid = (isSeleniumGrid != null) ? isSeleniumGrid : PropertiesUtil.getDefaultProperty(Constants.IS_GRID_ENABLED);

	    boolean headless = Boolean.parseBoolean(isHeadless);
	    boolean grid = Boolean.parseBoolean(isSeleniumGrid);

	    homePg = new HomePage(Browser.valueOf(browser.toUpperCase()), headless, grid);
	}

	
	@AfterMethod
	public void teardown() {
		homePg.getDriver().close();
		homePg.getDriver().quit();
	}

}
