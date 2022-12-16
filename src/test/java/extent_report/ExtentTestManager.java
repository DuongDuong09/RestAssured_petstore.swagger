package extent_report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {
    static Map<Integer, ExtentTest> extentTestMap = new HashMap();
    static ExtentReports extent = ExtentManager.getExtentReports();

    public ExtentTestManager() {
    }

    public static ExtentTest getTest() {
        return (ExtentTest)extentTestMap.get((int)Thread.currentThread().getId());
    }

    public static void logMessage(String message) {
        getTest().log(Status.INFO, message);
    }

    public static void logMessage(Status status, String message) {
        getTest().log(status, message);
    }

    public static synchronized ExtentTest saveToReport(String testName, String desc) {
        ExtentTest test = extent.createTest(testName, desc);
        extentTestMap.put((int)Thread.currentThread().getId(), test);
        return test;
    }
}

