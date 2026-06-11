package extentReporting;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * Singleton class to manage Extent Report v5 initialization and configuration
 * Uses ExtentSparkReporter for HTML reporting (ExtentHtmlReporter deprecated in v5)
 */
public class ExtentReportDefinition {
    private static ExtentReports extent;

    private static final String REPORT_FILE_NAME = "Test-Automaton-Report.html";
    private static final String REPORT_DIR = "TestReport";
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");
    private static final String USER_DIR = System.getProperty("user.dir");

    /**
     * Get or create the Extent Report instance (Lazy Initialization)
     */
    public static ExtentReports getInstance() {
        if (extent == null) {
            initializeReport();
        }
        return extent;
    }

    /**
     * Initialize and configure the Extent Report v5
     */
    private static ExtentReports initializeReport() {
        String reportPath = createReportDirectory();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        configureReporter(sparkReporter);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        setSystemInfo();

        return extent;
    }

    /**
     * Configure the Spark reporter with standard settings
     * Note: v5 uses ExtentSparkReporter which has simplified configuration
     */
    private static void configureReporter(ExtentSparkReporter reporter) {
        reporter.config().setReportName("Test Automation Report");
        reporter.config().setDocumentTitle(REPORT_FILE_NAME);
        reporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.DARK);
        reporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a");
    }

    /**
     * Set system information in the report
     */
    private static void setSystemInfo() {
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("OS Version", System.getProperty("os.version"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
    }

    /**
     * Create and return the full report file path
     */
    private static String createReportDirectory() {
        String reportDirPath = USER_DIR + FILE_SEPARATOR + REPORT_DIR;
        File directory = new File(reportDirPath);

        if (!directory.exists()) {
            if (directory.mkdir()) {
                System.out.println("Report directory created: " + reportDirPath);
            } else {
                System.err.println("Failed to create report directory: " + reportDirPath);
            }
        }

        return reportDirPath + FILE_SEPARATOR + REPORT_FILE_NAME;
    }
}
