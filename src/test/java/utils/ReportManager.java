package utils;

import base.baseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
public class ReportManager extends baseTest {
    public static ExtentSparkReporter sparkReporter;
    public static ExtentReports extent;
    public static ExtentTest logger;
    public static void setExtent() {
        //Initialize here you driver and html report
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/extentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("HostName", "RHEL8");
        sparkReporter.config().setDocumentTitle("Automation Reports");
    }

    public static void endReport() {
        extent.flush();
    }
}
