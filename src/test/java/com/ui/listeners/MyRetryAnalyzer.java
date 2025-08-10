package com.ui.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import static com.constants.Env.*;

import com.utility.JSONUtility;

public class MyRetryAnalyzer implements IRetryAnalyzer {
	
	//private static final int MAX_NUMBER_OF_ATTEMPTS = Integer.parseInt(PropertiesUtil.getProperties(DEV, "MAX_NUMBER_OF_ATTEMPTS")); // Using Properties file
	
	private static final int MAX_NUMBER_OF_ATTEMPTS = JSONUtility.readJSON(DEV).getMAX_NUMBER_OF_ATTEMPTS(); //Using Json
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
