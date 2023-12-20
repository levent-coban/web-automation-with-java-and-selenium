package tests;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ExtentReportsListener;
import java.time.Duration;


@Listeners(ExtentReportsListener.class)
public class BaseTest extends ExtentReportsListener {

    protected WebDriver driver;

    @BeforeMethod
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Invalid browser value: " + browser);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @AfterMethod
    public void tearDown(ITestResult result) {
        // Screenshot for Status.FAIL
        addScreenshotLogForFailedTest(result);
        // Close Browser
        if (driver != null) {
            driver.quit();
        }
    }


    void logScreenshotToReport() {
        String base64ScreenShot = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(base64ScreenShot).build());
    }


    void logInfoToReport(String str) {
        test.log(Status.INFO, str);
    }


    void addScreenshotLogForFailedTest(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String base64ScreenShot = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            test.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromBase64String(base64ScreenShot).build());
        }
    }

}