package extentReporting;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

/**
 * Thread-safe utility to manage and track extent test instances per thread
 * Compatible with ExtentReports v5.x
 */
public class ExtentReportTestsTracker {
    private static final Map<Long, ExtentTest> TEST_MAP = new HashMap<>();
    private static final ExtentReports EXTENT = ExtentReportDefinition.getInstance();

    /**
     * Start a new test and track it by thread ID
     */
    public static synchronized ExtentTest startTest(String testName) {
        ExtentTest test = EXTENT.createTest(testName);
        TEST_MAP.put(Thread.currentThread().getId(), test);
        return test;
    }

    /**
     * Get the current thread's ExtentTest instance
     */
    public static synchronized ExtentTest getCurrentTest() {
        return TEST_MAP.get(Thread.currentThread().getId());
    }

    /**
     * Log a PASS status for the current test
     */
    public static synchronized void logPass(String message) {
        ExtentTest test = getCurrentTest();
        if (test != null) {
            test.log(Status.PASS, message);
        }
    }

    /**
     * Log a FAIL status for the current test
     */
    public static synchronized void logFail(String message) {
        ExtentTest test = getCurrentTest();
        if (test != null) {
            test.log(Status.FAIL, message);
        }
    }

    /**
     * Log a SKIP status for the current test
     */
    public static synchronized void logSkip(String message) {
        ExtentTest test = getCurrentTest();
        if (test != null) {
            test.log(Status.SKIP, message);
        }
    }

    /**
     * Log an INFO message for the current test
     */
    public static synchronized void logInfo(String message) {
        ExtentTest test = getCurrentTest();
        if (test != null) {
            test.log(Status.INFO, message);
        }
    }

    /**
     * Log a WARNING message for the current test
     */
    public static synchronized void logWarning(String message) {
        ExtentTest test = getCurrentTest();
        if (test != null) {
            test.log(Status.WARNING, message);
        }
    }

    /**
     * Add category/tag to the current test (v5 feature)
     */
    public static synchronized void addCategory(String category) {
        ExtentTest test = getCurrentTest();
        if (test != null) {
            test.assignCategory(category);
        }
    }

    /**
     * Add author to the current test (v5 feature)
     */
    public static synchronized void addAuthor(String author) {
        ExtentTest test = getCurrentTest();
        if (test != null) {
            test.assignAuthor(author);
        }
    }

    /**
     * Add device information to the current test (v5 feature)
     */
    public static synchronized void addDevice(String device) {
        ExtentTest test = getCurrentTest();
        if (test != null) {
            test.assignDevice(device);
        }
    }

    /**
     * Finish reporting and flush all test data
     */
    public static synchronized void finishAllTests() {
        EXTENT.flush();
        TEST_MAP.clear();
    }

    /**
     * End a specific test and remove it from tracking
     */
    public static synchronized void endTest(String testName) {
        TEST_MAP.remove(Thread.currentThread().getId());
    }
}
