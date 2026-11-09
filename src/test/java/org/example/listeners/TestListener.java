
package org.example.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class TestListener implements ITestListener {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> test =
            new ThreadLocal<>();

    static {
        ExtentSparkReporter spark =
                new ExtentSparkReporter("target/ExtentReport.html");

        extent = new ExtentReports();
        extent.attachReporter(spark);

        extent.setSystemInfo("Project", "SauceDemo Automation");
        extent.setSystemInfo("Framework", "Selenium + TestNG");
        extent.setSystemInfo("Language", "Java");
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest =
                extent.createTest(result.getMethod().getMethodName());

        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test passed successfully");
    }


    @Override
    public void onTestFailure(ITestResult result) {

        test.get().fail(result.getThrowable());

        Object testClass = result.getInstance();

        WebDriver driver =
                ((org.example.base.BaseTest) testClass).getDriver();

        if (driver != null) {
            try {
                File screenshot = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.FILE);

                String fileName = result.getMethod().getMethodName()
                        + "_" + System.currentTimeMillis() + ".png";

                File destination = new File(
                        "target/screenshots/" + fileName
                );

                destination.getParentFile().mkdirs();

                Files.copy(
                        screenshot.toPath(),
                        destination.toPath(),
                        StandardCopyOption.REPLACE_EXISTING
                );

                test.get().addScreenCaptureFromPath(
                        "screenshots/" + fileName
                );

            } catch (IOException e) {
                test.get().warning(
                        "Screenshot could not be saved: " + e.getMessage()
                );
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip(result.getThrowable());
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        extent.flush();
    }
}
