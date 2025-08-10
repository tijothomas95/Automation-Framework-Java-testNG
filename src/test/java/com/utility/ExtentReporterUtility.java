package com.utility;

import java.nio.file.Paths;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterUtility {
	
	//To ensure thread safety
	private static ExtentReports extentReports;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	public static void setUpSparkReporter(String fileName) {
		String fileLoc = Paths.get(System.getProperty("user.dir"), fileName).toString();
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(fileLoc);
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
	}
	
	public static void createExtentTest(String methodName) {
		ExtentTest test = extentReports.createTest(methodName);
		extentTest.set(test);
	}
	
	public static ExtentTest getTest() {
		return extentTest.get();
	}
	
	public static void flushReport() {
		extentReports.flush();
	}
	
}
