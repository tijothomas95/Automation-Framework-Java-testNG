package com.ui.tests;

import static org.testng.Assert.*;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pojo.User;

@Listeners({ com.ui.listeners.TestListener.class })
public class LoginTest extends BaseTest {

	@Test(description = "Verify user login usi json data", 
			groups = { "e2e", "smoke" }, 
			dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, 
			dataProvider = "LoginTestJSONDataProvider")
	public void loginTestUsingJSONData(User user) {
		assertEquals(homePg.gotoLoginPage().doLoginUser(user.getEmail(), user.getPassword()).getUserNameDisplayed(),
				"Testfname TestLname");
	}

	@Test(description = "Verify user login using csv data", groups = { "e2e", "smoke" }, 
			dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, 
			dataProvider = "LoginTestCSVDataProvider",
			retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class)
	public void loginTestUsingCSVData(User user) {
		assertEquals(homePg.gotoLoginPage().doLoginUser(user.getEmail(), user.getPassword()).getUserNameDisplayed(),
				"Testfname TestLname");
	}

	@Test(description = "Verify user login using excel data", groups = { "e2e", "smoke" }, 
			dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, 
			dataProvider = "LoginTestExcelDataProvider", 
			retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class)
	public void loginTestUsingExcelData(User user) {
		assertEquals(homePg.gotoLoginPage().doLoginUser(user.getEmail(), user.getPassword()).getUserNameDisplayed(),
				"Testfname TestLname");
	}

}
