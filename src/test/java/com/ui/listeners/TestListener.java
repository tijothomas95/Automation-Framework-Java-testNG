package com.ui.listeners;

import java.util.Arrays;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.ui.tests.BaseTest;
import com.utility.BrowserUtility;
import com.utility.ExtentReporterUtility;
import com.utility.LoggerUtility;

import io.qameta.allure.Allure;
import io.qameta.allure.model.StepResult;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Attachment;

public class TestListener implements ITestListener {
	Logger logger = LoggerUtility.getLogger(this.getClass());
	private final AllureLifecycle lifecycle = Allure.getLifecycle();

	@Override
	public void onStart(ITestContext context) {
		logger.info("Test Suite started");
		ExtentReporterUtility.setUpSparkReporter("extentReport.html");
	}

	@Override
	public void onTestStart(ITestResult result) {
		logger.info("Starting test: {}", getTestMethodName(result));
		logger.info("{}, {}", result.getMethod().getDescription(), Arrays.toString(result.getMethod().getGroups()));

		ExtentReporterUtility.createExtentTest(getTestMethodName(result));
		Allure.getLifecycle().startStep(getTestMethodName(result), new StepResult().setName("Starting Test"));
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		logger.info("{} PASSED", getTestMethodName(result));

		ExtentReporterUtility.getTest().log(Status.PASS, getTestMethodName(result) + " PASSED");
		Allure.step(getTestMethodName(result) + " PASSED", io.qameta.allure.model.Status.PASSED);
	}
	
	@Attachment(value = "Failure Screenshot - {0}", type = "image/png", fileExtension = ".png")
	private byte[] attachScreenshotToAllure(BrowserUtility util) {
	    try {
	        return ((TakesScreenshot) util.getDriver()).getScreenshotAs(OutputType.BYTES);
	    } catch (Exception e) {
	        System.out.println("Screenshot capture failed: " + e.getMessage());
	        return null;
	    }
	}
	
	@Attachment(value = "Failure Screenshot", type = "image/png")
	public byte[] attachScreenshotToAllure(WebDriver driver) {
	    try {
	        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	    } catch (Exception e) {
	        System.out.println("Failed to capture screenshot: " + e.getMessage());
	        return null;
	    }
	}

	@Override
	public void onTestFailure(ITestResult result) {
	    String testName = result.getMethod().getMethodName();
	    logger.error("{} FAILED", testName);
	    logger.error("Reason: ", result.getThrowable());

	    ExtentReporterUtility.getTest().log(Status.FAIL, testName + " FAILED");
	    ExtentReporterUtility.getTest().log(Status.FAIL, result.getThrowable().getMessage());

	    // Attach failure reason
	    Allure.addAttachment("Failure Reason", result.getThrowable().toString());

	    // Get driver instance
	    Object testInstance = result.getInstance();
	    if (testInstance instanceof BaseTest) {
	        BrowserUtility util = ((BaseTest) testInstance).getInstance();
	        attachScreenshotToAllure(util.getDriver());
	    }
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		logger.warn("{} SKIPPED", getTestMethodName(result));

		ExtentReporterUtility.getTest().log(Status.SKIP, getTestMethodName(result) + " SKIPPED");
		Allure.step(getTestMethodName(result) + " SKIPPED", io.qameta.allure.model.Status.SKIPPED);
	}

	@Override
	public void onFinish(ITestContext context) {
		logger.info("Test Suite finished");
		ExtentReporterUtility.flushReport();
	}

	private static String getTestMethodName(ITestResult result) {
        return result.getMethod().getMethodName();
    }
}
