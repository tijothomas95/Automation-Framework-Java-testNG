# **Automation-Java-TestNG**

## **Overview**
`Automation-Java-TestNG` is a **Test Automation framework** designed for automating tests for the application **[automationpractise.pl](https://automationpractise.pl)**. The framework is built using **Java**, **TestNG**, **Selenium WebDriver**, and integrates with **Maven** for easy build management and dependency management.
This project enables you to run automated UI tests using Selenium Grid with Docker Compose, allowing parallel execution across multiple browsers like Chrome, Firefox, and Edge.

The framework supports the following:
- **Browser Automation** (using Selenium WebDriver)
- **Data-driven Testing** (using OpenCSV, Apache POI)
- **Test Reporting** (using ExtentReports)
- **Logging** (via Log4j)
- **Cross-browser Testing** (supports Chrome by default)
- **Selenium Grid** (Docker Compose)

---

## **Features**
- **Cross-browser support** (currently Chrome is configured by default)
- **Headless execution** support
- **TestNG** for test execution and parallelism
- **ExtentReports** integration for advanced reporting
- **Log4j** for logging and debugging
- **Data-driven testing** (using OpenCSV and Apache POI for Excel)

---

## **Prerequisites**
To run the tests, you must have the following installed:

- **Java 11+** (Required by the project)
- **Maven** (for dependency management and test execution)
- **IDE** (like IntelliJ IDEA or Eclipse)
- **Docker Desktop** (installed and running)

---

## **Setup and Installation**

### **1. Clone the Repository**
Clone the repository to your local machine:
```bash
git clone https://github.com/your-repo/Automation-Java-TestNG.git
cd Automation-Java-TestNG
```

### 2. Install Dependencies
Run the following Maven command to install all the required dependencies defined in the pom.xml file:
```bash
mvn clean install
```
This will download the necessary dependencies such as Selenium, TestNG, Log4j, etc.

### 3. Configure System Properties (Optional)
By default, the framework is set to run on Chrome in non-headless mode. You can customize this by modifying the following system properties in the pom.xml or through command-line arguments:
```
browser: Set browser type (e.g., chrome, firefox, etc.)
isHeadless: Set to true if you want to run the tests in headless mode.
```

## Running the Tests
### 1. Run Tests Using Maven
To run the tests using Maven, execute the following command:
```bash
mvn test
```
This will trigger TestNG to execute the tests based on the testng.xml suite configuration.

### 2. Run Specific Tests
You can run specific test methods or classes by specifying them with the -Dtest option:
```bash
mvn -Dtest={testClass} test
```


## Test Configuration
The test configuration file is located in src/test/resources/testng.xml. Here, you can define:
Test Suites: Groups of tests to execute.
Parallel Execution: TestNG allows parallel test execution by configuring parallel="tests" in the testng.xml.
Example configuration:
```
<suite name="AutomationSuite" parallel="tests" thread-count="2">
    <test name="Test Chrome">
        <parameter name="browser" value="chrome"/>
        <classes>
            <class name="com.automation.test.{testClass}"/>
        </classes>
    </test>
</suite>
```
    
## Test Reporting
The framework generates test reports using ExtentReports. The reports will be saved in the target/extentreports folder by default. The report provides detailed insights into the execution of the tests, including:
Test execution status (Pass/Fail)
Screenshots for failed tests
Logs for debugging

## Logging
The framework uses Log4j for logging. Logs will be generated in target/logs/ and can help track test execution and debug failures.
You can configure logging in the src/main/resources/log4j2.xml file.

## Selenium Grid Execution
This project enables you to run automated UI tests using Selenium Grid with Docker Compose, allowing parallel execution across multiple browsers like Chrome, Firefox, and Edge.

From the root of the project:
```
docker compose up --scale chrome=3 --scale firefox=2 --scale edge=1

chrome=3 starts 3 Chrome nodes
firefox=2 starts 2 Firefox nodes
edge=1 starts 1 Edge node
```

After the Grid is up, run your tests via Maven:
```
mvn clean test
```

Make sure your framework points to the Grid URL: http://localhost:4444/wd/hub
This can be configured via default_properties or passed through testng.xml

## Dependencies
The following dependencies are included in this framework:

| Dependency GroupId                  | ArtifactId                  | Version    | Description                                       |
|-------------------------------------|-----------------------------|------------|---------------------------------------------------|
| `org.seleniumhq.selenium`           | `selenium-java`             | `4.34.0`   | Selenium WebDriver for browser automation         |
| `org.testng`                        | `testng`                    | `7.11.0`   | TestNG for test execution                         |
| `com.google.code.gson`              | `gson`                      | `2.13.1`   | Gson for JSON handling                            |
| `com.opencsv`                       | `opencsv`                   | `5.12.0`   | OpenCSV for reading/writing CSV files             |
| `org.apache.poi`                    | `poi`                       | `5.4.1`    | Apache POI for working with Excel files           |
| `org.apache.poi`                    | `poi-ooxml`                 | `5.4.1`    | Apache POI for working with OOXML Excel files     |
| `org.apache.logging.log4j`          | `log4j-core`                | `2.25.1`   | Log4j core for logging                            |
| `org.apache.logging.log4j`          | `log4j-api`                 | `2.25.1`   | Log4j API for logging                             |
| `com.aventstack`                    | `extentreports`             | `5.1.2`    | ExtentReports for test reporting                  |
| `org.apache.maven.plugins`          | `maven-surefire-plugin`     | `3.5.3`    | Plugin for running tests with Maven               |
| `org.apache.maven.plugins`          | `maven-compiler-plugin`     | `3.14.0`   | Plugin for compiling Java code                    |


