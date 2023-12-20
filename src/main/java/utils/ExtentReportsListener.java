package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;


public class ExtentReportsListener implements ITestListener {

    protected static ExtentReports extent;
    protected static ExtentTest test;

    @Override
    public void onStart(ITestContext context) {

        ITestListener.super.onStart(context);

        extent = new ExtentReports();

        ExtentSparkReporter spark = new ExtentSparkReporter(
                "extent-reports" + File.separator + "ExtentReport.html");

        try {
            spark.loadXMLConfig(new File(Paths.get("src", "test", "resources", "extent-reports-config.xml").toString()));
        } catch (IOException e) {
            ExceptionLogger.logError("An error occurred at: utils/ExtentReportsListener", e);
        }

        extent.setSystemInfo("Project", ConfigProperties.getProperty("project"));
        extent.setSystemInfo("Author", ConfigProperties.getProperty("author"));
        extent.setSystemInfo("Environment", ConfigProperties.getProperty("environment"));
        extent.setSystemInfo("OS", ConfigProperties.getProperty("os"));
        extent.setSystemInfo("Browser", ConfigProperties.getProperty("browser"));
        extent.attachReporter(spark);

    }


    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        test = extent.createTest(result.getMethod().getMethodName())
                .log(Status.INFO, result.getMethod().getMethodName() + " is starting.");
    }


    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        test.log(Status.PASS, MarkupHelper.createLabel("Test PASSED", ExtentColor.GREEN));
    }


    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        test.log(Status.FAIL, MarkupHelper.createLabel("Test FAILED", ExtentColor.RED));
        test.log(Status.WARNING, result.getThrowable());
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
        test.log(Status.SKIP, MarkupHelper.createLabel("Test SKIPPED", ExtentColor.ORANGE));
        test.log(Status.WARNING, result.getThrowable().toString());
    }


    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        extent.flush();
    }

}