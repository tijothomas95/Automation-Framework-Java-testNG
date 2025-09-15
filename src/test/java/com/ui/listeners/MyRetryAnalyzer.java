package com.ui.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.constants.Constants;
import com.constants.Env;
import com.utility.JSONUtility;
import com.utility.PropertiesUtil;

public class MyRetryAnalyzer implements IRetryAnalyzer {
	
	//private static final int MAX_NUMBER_OF_ATTEMPTS = Integer.parseInt(PropertiesUtil.getEnvProperties(DEV, "MAX_NUMBER_OF_ATTEMPTS")); // Using Properties file
	
	private static final int MAX_NUMBER_OF_ATTEMPTS = JSONUtility.readJSON(Env.valueOf(PropertiesUtil.getDefaultProperty(Constants.ENV))).getMAX_NUMBER_OF_ATTEMPTS(); //Using Json
	private int currentAttempt = 1; //IRetryAnalyzer creates a new instance for each test, so this should be an instance variable and will be shared across all retries/tests

	@Override
	public boolean retry(ITestResult result) {
		if (currentAttempt<=MAX_NUMBER_OF_ATTEMPTS) {
			currentAttempt++;
			return true;
		}
		return false;
	}
	
	

}
