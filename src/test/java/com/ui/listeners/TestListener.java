package com.ui.listeners;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.utility.ExtentReporterUtility;
import com.utility.LoggerUtility;

public class TestListener implements ITestListener {
	Logger logger = LoggerUtility.getLogger(this.getClass());

	// These listener methods implements from ITestListener interface
	@Override
	public void onStart(ITestContext context) {
		logger.info("Test Suite started");
		
		ExtentReporterUtility.setUpSparkReporter("extentReport.html");
	}

	@Override
	public void onTestStart(ITestResult result) {
		logger.info(result.getMethod().getMethodName());
		logger.warn("{}, {}", result.getMethod().getDescription(), Arrays.toString(result.getMethod().getGroups()));

		ExtentReporterUtility.createExtentTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		logger.warn("PASSED {}", result.getMethod().getMethodName());
		
		ExtentReporterUtility.getTest().log(Status.PASS, result.getMethod().getMethodName() + " " + "PASSED");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		logger.warn("FAILED {}", result.getMethod().getMethodName());
		logger.error(result.getThrowable().getMessage());

		ExtentReporterUtility.getTest().log(Status.FAIL, result.getMethod().getMethodName() + " " + "FAILED");
		ExtentReporterUtility.getTest().log(Status.FAIL, result.getThrowable().getMessage());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		logger.warn("SKIPPED {}", result.getMethod().getMethodName());

		ExtentReporterUtility.getTest().log(Status.SKIP, result.getMethod().getMethodName() + " " + "SKIPPED");
	}

	@Override
	public void onFinish(ITestContext context) {
		logger.info("Test Suite finished");

		ExtentReporterUtility.flushReport();
	}
}
