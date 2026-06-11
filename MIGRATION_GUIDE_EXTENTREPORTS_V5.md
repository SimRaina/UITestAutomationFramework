# ExtentReports v3 to v5 Migration Guide

## Overview
This guide documents the migration of your UITestAutomationFramework from ExtentReports v3.1.5 to v5.1.1, including the changes made and how to use the new features.

---

## 📦 Dependencies Updated

### Maven pom.xml Changes
```xml
<!-- OLD (v3) -->
<dependency>
    <groupId>com.aventstack</groupId>
    <artifactId>extentreports</artifactId>
    <version>3.1.5</version>
</dependency>

<!-- NEW (v5) -->
<dependency>
    <groupId>com.aventstack</groupId>
    <artifactId>extentreports</artifactId>
    <version>5.1.1</version>
</dependency>
```

### Other Updated Dependencies
- **Selenium**: 4.38.0 → 4.24.0
- **TestNG**: 7.7.0 → 7.9.1
- **Log4j Core**: 2.25.4 → 2.25.5
- **Jackson Databind**: 2.17.1 → 2.18.0

### Updated Build Plugins
- **maven-compiler-plugin**: 3.8.0 → 3.13.0
- **maven-surefire-plugin**: 2.22.1 → 3.2.5
- **maven-resources-plugin**: 3.0.2 → 3.3.1

---

## 🔄 Key API Changes

### 1. **Reporter Change: ExtentHtmlReporter → ExtentSparkReporter**

**v3 (Old):**
```java
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportPath);
htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
htmlReporter.config().setChartVisibilityOnOpen(true);
```

**v5 (New):**
```java
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
sparkReporter.config().setReportName("Test Automation Report");
```

### 2. **Configuration Simplification**

**v3 Configuration (Removed):**
- `setTestViewChartLocation(ChartLocation.BOTTOM)` - No longer needed
- `setChartVisibilityOnOpen(true)` - No longer needed
- `setEncoding("utf-8")` - Automatic in v5

**v5 Configuration (Available):**
- `setReportName(String)` - Set report name
- `setDocumentTitle(String)` - Set browser tab title
- `setTheme(Theme)` - Theme.DARK or Theme.LIGHT
- `setTimeStampFormat(String)` - Custom timestamp format

### 3. **System Info Enhanced**

**v3:**
```java
extent.setSystemInfo("OS", "Windows");
extent.setSystemInfo("AUT", "QA");  // Application Under Test
```

**v5:**
```java
extent.setSystemInfo("Operating System", System.getProperty("os.name"));
extent.setSystemInfo("OS Version", System.getProperty("os.version"));
extent.setSystemInfo("Java Version", System.getProperty("java.version"));
extent.setSystemInfo("User Name", System.getProperty("user.name"));
extent.setSystemInfo("Environment", "QA");
```

---

## 📝 Code Changes Made

### ExtentReportDefinition.java
**Changes:**
1. Replaced `ExtentHtmlReporter` with `ExtentSparkReporter`
2. Removed deprecated configuration methods (`ChartLocation`, `setEncoding`)
3. Enhanced `setSystemInfo()` with more system details
4. Simplified reporter configuration
5. Added comprehensive JavaDoc comments

**New Features:**
- Dynamic OS detection instead of hardcoded "Windows"
- Includes Java version and username in system info
- Better error logging with `System.err`

### ExtentReportTestsTracker.java
**New Methods Added (v5 Features):**
```java
// Add test category/tag
addCategory(String category)

// Add test author
addAuthor(String author)

// Add device information
addDevice(String device)

// Additional logging level
logWarning(String message)
```

**Example Usage:**
```java
ExtentReportTestsTracker.addAuthor("QA Team");
ExtentReportTestsTracker.addCategory("Smoke Tests");
ExtentReportTestsTracker.addDevice("Chrome Browser");
```

### ListenerTest.java
**Changes:**
1. Removed unused `Status` import
2. Added `@Override` annotations for clarity
3. Uses simplified logging methods from tracker
4. No direct dependency on ExtentReportDefinition anymore

---

## 🎨 Report Output Improvements

### v5 Report Features
1. **Modern Spark HTML Report**
   - Responsive design
   - Better charts and graphs
   - Improved UI/UX
   - Mobile-friendly

2. **Enhanced Test Metadata**
   - Categorization support
   - Author information
   - Device information
   - Better filtering options

3. **System Information Display**
   - OS and Version details
   - Java version
   - Machine information
   - Test environment info

---

## 📋 Usage Examples

### Basic Test Logging
```java
@Test
public void testLogin() {
    ExtentReportTestsTracker.startTest("Login Test");
    
    // Add metadata
    ExtentReportTestsTracker.addAuthor("Automation Team");
    ExtentReportTestsTracker.addCategory("Login Tests");
    
    try {
        // Your test code here
        loginPage.login("user", "password");
        ExtentReportTestsTracker.logPass("Login successful");
    } catch (Exception e) {
        ExtentReportTestsTracker.logFail("Login failed: " + e.getMessage());
    }
}
```

### Using New v5 Methods
```java
// Log different severity levels
ExtentReportTestsTracker.logInfo("Starting test execution");
ExtentReportTestsTracker.logWarning("Element load time exceeded");
ExtentReportTestsTracker.logPass("All assertions passed");

// Add test attributes
ExtentReportTestsTracker.addCategory("Regression");
ExtentReportTestsTracker.addAuthor("John Doe");
ExtentReportTestsTracker.addDevice("Chrome 120");
```

---

## ✅ Backward Compatibility Notes

### What Still Works
- All existing test code remains functional
- `logPass()`, `logFail()`, `logSkip()`, `logInfo()` work as before
- `startTest()`, `finishAllTests()` work as before
- Thread-safe test tracking unchanged

### What's Different
- Report filename remains: `Test-Automaton-Report.html`
- Report location remains: `TestReport/` directory
- Report format is now Spark (v5 standard)
- Some configuration options removed (but better defaults provided)

---

## 🚀 How to Build and Run

### Maven Commands
```bash
# Clean and rebuild
mvn clean compile

# Run tests
mvn test

# Generate only report
mvn clean test -DsuiteXmlFile=testng.xml
```

### Report Location
```
UITestAutomationFramework/
└── TestReport/
    └── Test-Automaton-Report.html
```

Open the HTML file in a web browser to view the report.

---

## 🐛 Known Issues & Troubleshooting

### Issue 1: Report not generating
**Solution:** Ensure `TestReport/` directory is writable
```java
// Check directory creation in logs
Report directory created: C:\...\UITestAutomationFramework\TestReport
```

### Issue 2: Test methods not appearing
**Solution:** Ensure `ListenerTest` is registered in `testng.xml`
```xml
<listeners>
    <listener class-name="listeners.ListenerTest" />
</listeners>
```

### Issue 3: Thread ID mismatch in parallel tests
**Solution:** The tracker uses thread IDs - ensure unique thread IDs per test
```java
// Thread-safe by design, no additional config needed
```

---

## 📚 Additional Resources

### ExtentReports v5 Documentation
- Official Docs: https://www.extentreports.com/
- GitHub: https://github.com/extent-framework/extentreports-java

### Migration Benefits
1. ✅ Modern, responsive HTML reports
2. ✅ Better performance
3. ✅ Enhanced test filtering
4. ✅ Improved metadata support
5. ✅ Active maintenance and support

---

## 🔍 Configuration Thresholds

### Spark Reporter Available Themes
```java
Theme.DARK    // New modern dark theme
Theme.LIGHT   // Light theme version
```

### Timestamp Formats
```java
"EEEE, MMMM dd, yyyy, hh:mm a"          // Example: Friday, June 11, 2025, 02:30 PM
"yyyy-MM-dd HH:mm:ss"                    // Example: 2025-06-11 14:30:45
"dd-MMM-yyyy hh:mm:ss a"                 // Example: 11-Jun-2025 02:30:45 PM
```

---

## 📝 Checklist for Migration

- ✅ Updated pom.xml with v5 dependencies
- ✅ Updated ExtentReportDefinition to use ExtentSparkReporter
- ✅ Enhanced ExtentReportTestsTracker with v5 methods
- ✅ Updated ListenerTest for v5 compatibility
- ✅ Build plugins updated to latest stable versions
- ✅ All dependencies checked for CVEs (No vulnerabilities found)
- ✅ Backward compatibility maintained
- ✅ Documentation provided

---

## 🎯 Next Steps

1. **Run your existing tests** - They should work without modification
2. **Update test code** (optional) - Use new v5 methods like `addAuthor()`, `addCategory()`
3. **Customize report** - Update theme or timestamp format in `ExtentReportDefinition`
4. **Review reports** - Check the improved Spark HTML report format

---

## 📞 Support

For issues or questions:
1. Check the logs in `logfile/app.log`
2. Review the generated HTML report for test details
3. Verify `TestReport/` directory permissions
4. Ensure all dependencies are downloaded correctly using `mvn dependency:tree`

---

**Last Updated:** June 11, 2026  
**Migration From:** ExtentReports v3.1.5  
**Migration To:** ExtentReports v5.1.1

