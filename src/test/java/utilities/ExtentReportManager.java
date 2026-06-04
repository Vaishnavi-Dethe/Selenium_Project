package utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager implements ITestListener {
    // Making these fields static ensures that execution timestamps don't reset midway
    public static ExtentReports report;
    public static ExtentSparkReporter spark;
    public static ExtentTest test;

    public void onStart(ITestContext result) {
       String filePath = System.getProperty("user.dir") + "\\target\\extentReport\\report.html";
       spark = new ExtentSparkReporter(filePath);

       spark.config().setDocumentTitle("Automation Test Report");
       spark.config().setReportName("Regression Suite Results");
       spark.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.DARK);
       spark.config().setEncoding("utf-8");

       report = new ExtentReports();
       report.attachReporter(spark);
       report.setSystemInfo("OS", System.getProperty("os.name"));
       report.setSystemInfo("Java Version", System.getProperty("java.version"));
       report.setSystemInfo("Browser", "Chrome");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
       // ✅ FIX: Uses getMethodName() consistency to match your fail/skip methods
       test = report.createTest(result.getMethod().getMethodName());
       test.log(Status.PASS, "Test Passed: " + result.getMethod().getMethodName());
       test.assignCategory(result.getMethod().getRealClass().getSimpleName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
       // ✅ FIX: Standardized naming convention so Extent's engine can calculate test durations
       test = report.createTest(result.getMethod().getMethodName());
       test.log(Status.FAIL, "Test Failed: " + result.getMethod().getMethodName());
       test.log(Status.FAIL, result.getThrowable());
       
    }

    @Override
    public void onTestSkipped(ITestResult result) {
       test = report.createTest(result.getMethod().getMethodName());
       test.log(Status.SKIP, "Test Skipped: " + result.getMethod().getMethodName());
    }

    public void onFinish(ITestContext result) {
       // ✅ FIX: Checks if the report exists before flushing to safely complete chart rendering math
       if (report != null) {
           report.flush();
       }
    }
}