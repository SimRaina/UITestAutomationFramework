package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import extentReporting.ExtentReportTestsTracker;

/**
 * TestNG listener to capture test execution events and log to Extent Reports
 */
public class ListenerTest implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        System.out.println("Test Started: " + testName);
        ExtentReportTestsTracker.startTest(testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getName();
        System.out.println("Test Passed: " + testName);
        ExtentReportTestsTracker.logPass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName();
        System.out.println("Test Failed: " + testName);
        ExtentReportTestsTracker.logFail("Test Failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getName();
        System.out.println("Test Skipped: " + testName);
        ExtentReportTestsTracker.logSkip("Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not implemented
    }

    @Override
    public void onStart(ITestContext context) {
        // Not implemented
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Suite Finished");
        ExtentReportTestsTracker.finishAllTests();
    }
}
