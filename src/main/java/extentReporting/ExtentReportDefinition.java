package extentReporting;

import java.io.File;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * Singleton class to manage Extent Report initialization and configuration
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
     * Initialize and configure the Extent Report
     */
    private static ExtentReports initializeReport() {
        String reportPath = createReportDirectory();
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportPath);
        configureReporter(htmlReporter);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        setSystemInfo();

        return extent;
    }

    /**
     * Configure the HTML reporter with standard settings
     */
    private static void configureReporter(ExtentHtmlReporter reporter) {
        reporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        reporter.config().setChartVisibilityOnOpen(true);
        reporter.config().setTheme(Theme.STANDARD);
        reporter.config().setDocumentTitle(REPORT_FILE_NAME);
        reporter.config().setEncoding("utf-8");
        reporter.config().setReportName("Test Automation Report");
        reporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    }

    /**
     * Set system information in the report
     */
    private static void setSystemInfo() {
        extent.setSystemInfo("OS", System.getProperty("os.name"));
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
