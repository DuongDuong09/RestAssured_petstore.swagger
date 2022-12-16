package extent_report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.observer.ExtentObserver;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static final ExtentReports extentReports = new ExtentReports();

    public ExtentManager() {
    }

    public static synchronized ExtentReports getExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("./ExtentReports/ExtentReport.html");
        reporter.config().setReportName("Demo Extent Report");
        extentReports.attachReporter(new ExtentObserver[]{reporter});
        extentReports.setSystemInfo("Framework Name", "Selenium Java ");
        extentReports.setSystemInfo("Author", "Duong NGUYEN");
        return extentReports;
    }
}
