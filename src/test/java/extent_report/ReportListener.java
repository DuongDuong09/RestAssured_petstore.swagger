package extent_report;

import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.PrintStream;

public class ReportListener implements ITestListener {
    public static final Logger Logging = LogManager.getLogger(ReportListener.class);

    public ReportListener() {
    }

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public static String saveTextLog(String message) {
        return message;
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : this.getTestName(result);
    }

    public void onTestStart(ITestResult iTestResult) {
        Logger var10000 = Logging;
        String var10001 = this.getTestName(iTestResult);
        var10000.error(var10001 + " test is starting...");
        ExtentTestManager.saveToReport(iTestResult.getName(), iTestResult.getTestName());
    }

    public void onTestSuccess(ITestResult iTestResult) {
        Logger var10000 = Logging;
        String var10001 = this.getTestName(iTestResult);
        var10000.error(var10001 + " test is passed...");
        ExtentTestManager.logMessage(Status.PASS, this.getTestDescription(iTestResult));
    }

    public void onFinish(ITestContext context) {

        Logging.error("Test execution is finished");
        ExtentManager.getExtentReports().flush();
    }


    public void onTestFailure(ITestResult iTestResult) {
        Logger var10000 = Logging;
        String var10001 = this.getTestName(iTestResult);
        var10000.error(var10001 + " test is failed...");
        ExtentTestManager.logMessage(Status.FAIL, this.getTestDescription(iTestResult));
        var10000 = Logging;
        var10001 = this.getTestName(iTestResult);
        var10000.error("Screenshot captured for test case: " + var10001);
        String var3 = this.getTestName(iTestResult);
        saveTextLog(var3 + " failed and screenshot taken!");
    }

    public void onTestSkipped(ITestResult iTestResult) {
        Logger var10000 = Logging;
        String var10001 = this.getTestName(iTestResult);
        var10000.error(var10001 + " test is skipped...");
        Status var2 = Status.SKIP;
        var10001 = this.getTestName(iTestResult);
        ExtentTestManager.logMessage(var2, var10001 + " test is skipped.");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        Logger var10000 = Logging;
        String var10001 = this.getTestName(iTestResult);
        var10000.error("Test failed but it is in defined success ratio " + var10001);
        String var2 = this.getTestName(iTestResult);
        ExtentTestManager.logMessage("Test failed but it is in defined success ratio " + var2);
    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }
}

