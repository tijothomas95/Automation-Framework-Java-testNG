package com.ui.tests;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.constants.Browser;
import com.ui.pages.HomePage;
import com.utility.BrowserUtility;

import io.qameta.allure.Attachment;

public class BaseTest {
	
	protected HomePage homePg;
	private boolean isHeadless;
	
	
	@Parameters({"browser", "isHeadless"}) // Parameter config in the testNG.xml
	@BeforeMethod(description="Load home page")
	public void setup(
			@Optional("chrome") String browser,
			@Optional("false") boolean isHeadless) {
		homePg = new HomePage(Browser.valueOf(browser.toUpperCase()), isHeadless); //Passing enum valueOf
	}
	
	
	@AfterMethod
	public void teardown() {
	    homePg.getDriver().quit();
	}
	
	public BrowserUtility getInstance() {
		return homePg;
	}

}
