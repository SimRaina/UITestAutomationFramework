# 🚀 Hybrid UI Test Automation Framework (POM)

> A comprehensive, production-ready Selenium automation framework using Page Object Model (POM) pattern with Java, TestNG, and Maven.

---

## 📋 Table of Contents

- [Overview](#overview)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Quick Start](#quick-start)
- [Project Structure](#project-structure)
- [Features](#features)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Interview Questions & Answers](#interview-questions--answers)
- [Contributing](#contributing)

---

## 🎯 Overview

This is a **Hybrid UI Test Automation Framework** designed for scalable, maintainable, and reusable test automation. It follows industry best practices including:

- ✅ Page Object Model (POM) for better maintainability
- ✅ Layered architecture for scalability
- ✅ Parallel test execution support
- ✅ Cross-browser testing capabilities
- ✅ Comprehensive HTML reporting with ExtentReports v5
- ✅ Data-driven testing support (Excel, JSON)
- ✅ Screenshot capture on failure
- ✅ TestNG listeners for event-driven automation

---

## 🛠 Tech Stack

| Component | Version | Purpose |
|-----------|---------|---------|
| **Selenium WebDriver** | 4.40.0 | Browser automation |
| **Java** | 1.8+ | Programming language |
| **TestNG** | 7.7.1 | Test execution framework |
| **Maven** | 3.6+ | Build tool |
| **ExtentReports** | 5.1.1 | HTML test reporting |
| **Fillo** | 1.18 | Excel data manipulation |
| **Log4j Core** | 2.23.1 | Logging framework |
| **Jackson** | 2.18.0 | JSON processing |

---

## 📦 Prerequisites

### System Requirements
- ✅ Java Development Kit (JDK) 1.8 or higher
- ✅ Maven 3.6 or higher
- ✅ Git (for cloning the repository)
- ✅ IDE: IntelliJ IDEA, Eclipse, or VS Code

### Installation

1. **Install Java JDK**
   ```bash
   # Verify Java installation
   java -version
   ```

2. **Install Maven**
   ```bash
   # Verify Maven installation
   mvn -version
   ```

3. **Clone or Download Project**
   ```bash
   git clone <repository-url>
   cd UITestAutomationFramework
   ```

---

## ⚡ Quick Start

### 1. Import Project

```
1. Open your IDE (IntelliJ / Eclipse)
2. Click File > Import/Open
3. Select the project folder
4. Choose "Import as Existing Maven Project"
5. Select pom.xml
6. Click OK/Finish
```

Maven will automatically download all dependencies.

### 2. Build Project

```bash
mvn clean compile
```

### 3. Run Tests

```bash
# Run all tests
mvn clean test

# Run specific test class
mvn test -Dtest=LoginTest

# Run with specific browser
mvn test -Dbrowser=firefox

# Run with specific environment
mvn test -Denv=UAT

# Run in parallel
mvn test -Dparallel=true
```

### 4. View Test Reports

```
Navigate to: UITestAutomationFramework/TestReport/Test-Automaton-Report.html
```

---

## 📁 Project Structure

```
UITestAutomationFramework/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── pageClasses/          # Page Object Model classes
│   │       │   ├── LoginPage.java
│   │       │   └── DashboardPage.java
│   │       ├── testbase/             # Base test class
│   │       │   └── TestBase.java
│   │       ├── utilities/            # Helper utilities
│   │       │   ├── ExplicitWaitUtil.java
│   │       │   ├── JSExecUtil.java
│   │       │   └── ScreenShot.java
│   │       ├── listeners/            # TestNG listeners
│   │       │   ├── ListenerTest.java
│   │       │   └── CustomRetryListener.java
│   │       ├── extentReporting/      # Reporting utilities (v5)
│   │       │   ├── ExtentReportDefinition.java
│   │       │   └── ExtentReportTestsTracker.java
│   │       ├── datamanager/          # Configuration management
│   │       │   └── ConfigReader.java
│   │       ├── filloreader/          # Excel utility
│   │       │   └── FilloReader.java
│   │       ├── screenshot/           # Screenshot utility
│   │       │   └── ScreenShot.java
│   │       └── pojo/                 # Data models
│   │           └── LoginData.java
│   │
│   └── test/
│       └── java/test/                # Test classes
│           └── LoginTest.java
│
├── Resources/
│   ├── ConfigFiles/
│   │   └── Env.properties            # Environment configuration
│   └── data/
│       ├── logindata.json
│       └── loginData.xlsx
│
├── ScreenShots/                      # Captured screenshots (auto)
├── TestReport/                       # HTML reports (auto)
├── logfile/                          # Application logs (auto)
│
├── pom.xml                           # Maven configuration
├── testng.xml                        # TestNG suite configuration
├── log4j2.properties                 # Logging configuration
└── README.md                         # This file
```

---

## ✨ Features

### 🔹 Page Object Model (POM)
- Centralized locator management
- Reusable page methods
- Improved maintainability

### 🔹 Parallel Execution
- Multi-threaded test execution
- Thread-safe WebDriver management
- ThreadLocal implementation

### 🔹 Cross-Browser Support
- Chrome, Firefox, Safari, Edge
- Configuration-based browser selection

### 🔹 Data-Driven Testing
- Excel (Fillo library)
- JSON
- TestNG DataProviders

### 🔹 Advanced Reporting
- ExtentReports v5 (Modern Spark HTML)
- Test metadata (Author, Category, Device)
- Screenshot attachment on failure

### 🔹 Comprehensive Logging
- Log4j integration
- Test execution logs
- Error tracking

### 🔹 Wait Strategies
- Explicit waits
- Implicit waits
- Fluent waits

---

## ⚙️ Configuration

### Environment Configuration (Env.properties)
```properties
# Browser settings
browser=chrome
headless=false

# Environment URLs
QA=https://qa.company.com
UAT=https://uat.company.com
STAGE=https://staging.company.com

# Explicit wait timeout (seconds)
explicit_wait=10
implicit_wait=5

# Screenshot settings
screenshot_on_failure=true
screenshot_path=./ScreenShots/
```

### TestNG Configuration (testng.xml)
```xml
<suite name="Test Suite" parallel="tests" thread-count="4">
    <test name="Login Tests">
        <parameter name="browser" value="chrome"/>
        <classes>
            <class name="test.LoginTest"/>
        </classes>
    </test>
</suite>
```

---

## 🧪 Running Tests

### Command Line Examples

```bash
# Clean build and run all tests
mvn clean test

# Run with custom browser
mvn test -Dbrowser=firefox

# Run specific test class
mvn test -Dtest=LoginTest

# Run specific test method
mvn test -Dtest=LoginTest#loginWithValidCredentials

# Parallel execution
mvn test -DthreadCount=4

# Skip tests during build
mvn clean compile -DskipTests
```

---

## 📚 Interview Questions & Answers

This section covers essential interview questions for Selenium automation framework design, best practices, and SOLID principles.

---

### Q1: How would you design a scalable Selenium automation framework using Java and TestNG?

**Answer:**

A scalable framework should follow a **layered architecture** to separate concerns and improve maintainability.

**Framework Architecture:**

```
Project Structure:
├── Page Objects Layer      (UI Locators & Methods)
├── Test Layer              (Test Cases)
├── Utilities Layer         (Reusable Methods)
├── Listeners Layer         (Event Handling)
├── Reporting Layer         (Test Reports)
├── Configuration Layer     (Properties & Config)
└── Data Layer              (Test Data Management)
```

**Key Components:**

| Component | Purpose | Example |
|-----------|---------|---------|
| **Page Object Model** | Separate locators & actions | LoginPage.java |
| **Base Test** | Driver initialization, config loading | TestBase.java |
| **Utilities** | Reusable methods | ExplicitWaitUtil.java, ScreenShot.java |
| **Listeners** | Capture events (pass/fail/skip) | ListenerTest.java |
| **Configuration** | Environment & browser settings | Env.properties |
| **TestNG** | Test execution & parallel runs | testng.xml |
| **Reporting** | HTML reports with screenshots | ExtentReports v5 |

**Benefits:**
✅ Reusable code  
✅ Easy maintenance  
✅ Parallel execution support  
✅ Cross-browser compatibility  
✅ Professional reporting  
✅ Better scalability  

---

### Q2: Explain the Page Object Model (POM)

**Answer:**

POM is a design pattern where each web page is represented as a Java class. It separates test logic from UI logic.

**Example: LoginPage.java**

```java
public class LoginPage {
    WebDriver driver;
    
    // Locators using @FindBy annotation
    @FindBy(id="username")
    WebElement usernameField;
    
    @FindBy(id="password")
    WebElement passwordField;
    
    @FindBy(id="loginBtn")
    WebElement loginButton;
    
    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    // Page actions
    public void login(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }
}
```

**Test Usage:**
```java
@Test
public void testValidLogin() {
    LoginPage loginPage = new LoginPage(driver);
    loginPage.login("admin", "password");
    // Verify login successful
}
```

**Advantages of POM:**
✅ Better readability  
✅ Reduced code duplication  
✅ Easy maintenance (change locator in one place)  
✅ Separation of UI logic and test logic  
✅ Improved collaboration (testers & developers)  

---

### Q3: How do you manage test execution across different browsers and environments?

**Answer:**

Use configuration files and parameter passing to manage browsers and environments.

**1. Configuration File (Env.properties)**
```properties
browser=chrome
environment=QA
url=https://qa.company.com
implicit_wait=5
explicit_wait=10
```

**2. Environment URLs (config.properties)**
```properties
QA=https://qa.company.com
UAT=https://uat.company.com
STAGING=https://stage.company.com
PROD=https://www.company.com
```

**3. Browser Selection (TestBase.java)**
```java
public void initializeDriver() {
    String browser = prop.getProperty("browser").toLowerCase();
    
    switch(browser) {
        case "chrome":
            driver = new ChromeDriver();
            break;
        case "firefox":
            driver = new FirefoxDriver();
            break;
        case "edge":
            driver = new EdgeDriver();
            break;
        default:
            driver = new ChromeDriver();
    }
}
```

**4. TestNG XML Parameter Passing**
```xml
<parameter name="browser" value="chrome"/>
<parameter name="environment" value="QA"/>
```

**5. Maven Command Line**
```bash
mvn test -Dbrowser=firefox -Denv=UAT -Durl=https://uat.company.com
```

---

### Q4: How do you run parallel tests in TestNG and avoid thread-safety issues?

**Answer:**

TestNG supports parallel execution, but WebDriver must be made thread-safe using **ThreadLocal**.

**1. TestNG XML Configuration**
```xml
<suite parallel="tests" thread-count="4">
    <test name="Test1">
        <classes>
            <class name="test.LoginTest"/>
        </classes>
    </test>
    <test name="Test2">
        <classes>
            <class name="test.CheckoutTest"/>
        </classes>
    </test>
</suite>
```

**2. Thread-Safe WebDriver Management**
```java
public class DriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    
    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }
    
    public static WebDriver getDriver() {
        return driver.get();
    }
    
    public static void quitDriver() {
        driver.get().quit();
        driver.remove();
    }
}
```

**Benefits:**
✅ Each thread gets its own browser instance  
✅ No session overlap  
✅ Stable parallel execution  
✅ Reduced test execution time  

**Parallel Execution Options:**
- `parallel="tests"` - Parallel at test level
- `parallel="methods"` - Parallel at method level
- `parallel="classes"` - Parallel at class level
- `thread-count="4"` - Number of threads

---

### Q5: How do you handle dynamic elements with changing IDs?

**Answer:**

Use XPath strategies to locate dynamic elements.

**Dynamic ID Examples:**
```
id="user_12345"
id="user_67890"
id="user_unique_id_54321"
```

**Solution 1: XPath with Contains()**
```java
driver.findElement(By.xpath("//input[contains(@id,'user_')]"));
```

**Solution 2: XPath with Starts-With()**
```java
driver.findElement(By.xpath("//input[starts-with(@id,'user_')]"));
```

**Solution 3: Relative XPath**
```java
driver.findElement(By.xpath("//label[text()='Username']/following-sibling::input"));
```

**Solution 4: CSS Selectors**
```java
driver.findElement(By.cssSelector("input[id*='user_']")); // Contains
driver.findElement(By.cssSelector("input[id^='user_']")); // Starts-with
driver.findElement(By.cssSelector("input[id$='_field']")); // Ends-with
```

**Best Practice:** Use multiple locators in order of preference:
1. ID (most stable)
2. Name
3. CSS Selectors
4. XPath (least stable)

---

### Q6: Can you automate file upload/download in Selenium?

**Answer:**

File upload and download require different approaches.

**File Upload**

Method 1: SendKeys (Recommended)
```java
driver.findElement(By.id("fileInput")).sendKeys("C:\\Files\\document.pdf");
```

Method 2: Robot Class (when sendKeys fails)
```java
Robot robot = new Robot();
robot.keyPress(KeyEvent.VK_CONTROL);
robot.keyPress(KeyEvent.VK_V);
robot.keyRelease(KeyEvent.VK_V);
robot.keyRelease(KeyEvent.VK_CONTROL);
```

Method 3: AutoIT (Windows only)
```
Used for native OS file dialogs that Selenium cannot interact with directly.
```

**File Download Validation**

```java
// Set download directory
ChromeOptions options = new ChromeOptions();
Map<String, Object> prefs = new HashMap<>();
prefs.put("download.default_directory", "C:\\Downloads");
options.setExperimentalOption("prefs", prefs);
WebDriver driver = new ChromeDriver(options);

// Verify download
File downloadedFile = new File("C:\\Downloads\\document.pdf");
if(downloadedFile.exists()) {
    System.out.println("File downloaded successfully");
} else {
    System.out.println("Download failed");
}
```

---

### Q7: How do you capture screenshots on test failures and attach to reports?

**Answer:**

Create a screenshot utility and integrate with TestNG listeners.

**Screenshot Utility**
```java
public class ScreenshotUtil {
    public static String captureScreenshot(WebDriver driver, String testName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        String path = "./Screenshots/" + testName + "_" + System.currentTimeMillis() + ".png";
        FileUtils.copyFile(src, new File(path));
        return path;
    }
}
```

**TestNG Listener Integration**
```java
public class ListenerTest implements ITestListener {
    
    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverManager.getDriver();
        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());
        
        // Attach to ExtentReports
        ExtentReportTestsTracker.logFail("Test Failed");
        ExtentReportTestsTracker.getCurrentTest()
            .addScreenCaptureFromPath(screenshotPath);
    }
}
```

---

### Q8: How do you debug flaky or intermittent test failures?

**Answer:**

Follow a systematic debugging approach.

**Common Causes:**
```
❌ Timing issues (element not ready)
❌ Dynamic elements (changing IDs)
❌ Test data dependency (data not available)
❌ Environment instability (server down)
❌ Parallel execution conflicts (thread issues)
❌ Network latency
❌ Browser cache issues
```

**Debug Approach:**

| Step | Action |
|------|--------|
| 1 | Review test logs & console output |
| 2 | Check captured screenshots |
| 3 | Re-run test locally |
| 4 | Check browser console logs |
| 5 | Replace hard waits with explicit waits |
| 6 | Verify test independence (no data dependency) |
| 7 | Check network issues |
| 8 | Clear browser cache & cookies |

**Prevention Best Practices:**
✅ Use explicit waits instead of Thread.sleep()  
✅ Make tests independent (no data dependency)  
✅ Use proper wait conditions  
✅ Add detailed logging  
✅ Implement retry logic  

---

### Q9: What's the difference between Jenkins Freestyle Jobs and Pipeline Scripts?

**Answer:**

| Aspect | Freestyle | Pipeline |
|--------|-----------|----------|
| **Configuration** | GUI-based | Code-based (Jenkinsfile) |
| **Version Control** | Manual | Can be version controlled |
| **Scalability** | Limited | Highly scalable |
| **Maintainability** | Hard | Easy |
| **Reusability** | Low | High |
| **CI/CD Suitable** | Not ideal | Ideal for DevOps |
| **Complexity** | Simple | More complex |

**Pipeline Example (Declarative):**
```groovy
pipeline {
    agent any
    
    environment {
        BROWSER = 'chrome'
        ENVIRONMENT = 'QA'
    }
    
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/repo.git'
            }
        }
        
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        
        stage('Test') {
            steps {
                sh 'mvn test -Dbrowser=$BROWSER -Denv=$ENVIRONMENT'
            }
        }
        
        stage('Report') {
            steps {
                publishHTML([
                    reportDir: 'TestReport',
                    reportFiles: 'Test-Automaton-Report.html',
                    reportName: 'Extent Report'
                ])
            }
        }
    }
}
```

---

### Q10: How to integrate Selenium suite with Jenkins or GitHub Actions?

**Answer:**

**GitHub Actions Workflow:**

```yaml
name: Selenium Automation Tests

on:
  push:
    branches: [ main, develop ]
  pull_request:
    branches: [ main ]
  schedule:
    - cron: '0 2 * * *'  # Run daily at 2 AM

jobs:
  test:
    runs-on: ubuntu-latest
    
    strategy:
      matrix:
        browser: [chrome, firefox]
    
    steps:
      - uses: actions/checkout@v4
      
      - name: Set up JDK 1.8
        uses: actions/setup-java@v3
        with:
          java-version: '1.8'
      
      - name: Run Tests
        run: mvn clean test -Dbrowser=${{ matrix.browser }}
      
      - name: Upload Reports
        if: always()
        uses: actions/upload-artifact@v3
        with:
          name: Test-Reports-${{ matrix.browser }}
          path: TestReport/
      
      - name: Publish Test Results
        if: always()
        uses: EnricoMi/publish-unit-test-result-action@v2
        with:
          files: target/surefire-reports/TEST-*.xml
```

**Jenkins Configuration:**

1. Install plugins: Maven, Test Result, HTML Publisher
2. Create new Pipeline job
3. Configure Git repository
4. Add Jenkinsfile
5. Set up triggers (poll SCM, webhooks)
6. Configure post-build actions for reporting

**Benefits:**
✅ Automated test execution  
✅ Scheduled test runs  
✅ CI/CD integration  
✅ Automated reporting  
✅ Test history tracking  

---

### Q11: How to implement data-driven testing using Excel, CSV, or JSON?

**Answer:**

**TestNG DataProvider (In-Memory Data):**
```java
@DataProvider(name = "loginData")
public Object[][] loginDataProvider() {
    return new Object[][] {
        { "admin", "password123" },
        { "user1", "pass456" },
        { "user2", "pass789" }
    };
}

@Test(dataProvider = "loginData")
public void loginTest(String username, String password) {
    loginPage.login(username, password);
    Assert.assertTrue(dashboardPage.isDashboardDisplayed());
}
```

**Excel Data (Using Fillo):**
```java
@Test
public void readExcelData() throws Exception {
    Fillo fillo = new Fillo();
    Connection conn = fillo.getConnection("./Resources/data/loginData.xlsx");
    
    String query = "Select * from Sheet1 where TestCase='ValidLogin'";
    RecordSet recordSet = conn.executeQuery(query);
    
    while(recordSet.next()) {
        String username = recordSet.getValue("Username");
        String password = recordSet.getValue("Password");
        loginPage.login(username, password);
    }
}
```

**JSON Data:**
```java
@Test
public void readJsonData() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    File file = new File("./Resources/data/logindata.json");
    LoginData[] loginDataArray = mapper.readValue(file, LoginData[].class);
    
    for(LoginData data : loginDataArray) {
        loginPage.login(data.getUsername(), data.getPassword());
    }
}
```

**CSV Data:**
```java
@Test
public void readCsvData() throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("./data/login.csv"));
    String line;
    while((line = br.readLine()) != null) {
        String[] values = line.split(",");
        loginPage.login(values[0], values[1]);
    }
    br.close();
}
```

---

### Q12: Strategies to keep test scripts reusable and maintainable

**Answer:**

Follow **SOLID principles** and best practices:

**Best Practices:**
✅ Follow Page Object Model (POM)  
✅ Create reusable utility methods  
✅ Centralize all locators  
✅ Use constants for hardcoded values  
✅ Avoid code duplication  
✅ Use meaningful variable names  
✅ Implement TestNG listeners  
✅ Separate test data from test scripts  
✅ Document code properly  
✅ Use configuration files  

**SOLID Principles Overview:**

---

#### **S - Single Responsibility Principle (SRP)**

A class should have only one reason to change.

**❌ Bad Example:**
```java
public class Employee {
    public void calculateSalary() {
        System.out.println("Calculating salary");
    }
    public void saveToDatabase() {
        System.out.println("Saving to DB");
    }
    public void generateReport() {
        System.out.println("Generating report");
    }
}
```

**✅ Good Example:**
```java
public class SalaryService {
    public void calculateSalary() {}
}
public class EmployeeRepository {
    public void saveEmployee() {}
}
public class ReportService {
    public void generateReport() {}
}
```

---

#### **O - Open/Closed Principle (OCP)**

Software entities should be **open for extension, closed for modification**.

**❌ Bad Example:**
```java
public class BrowserFactory {
    public WebDriver createBrowser(String type) {
        if(type.equals("chrome")) {
            return new ChromeDriver();
        } else if(type.equals("firefox")) {
            return new FirefoxDriver();
        }
        // Adding Edge requires modifying this class
        return null;
    }
}
```

**✅ Good Example:**
```java
public interface BrowserDriver {
    WebDriver createDriver();
}

public class ChromeBrowser implements BrowserDriver {
    public WebDriver createDriver() {
        return new ChromeDriver();
    }
}

public class FirefoxBrowser implements BrowserDriver {
    public WebDriver createDriver() {
        return new FirefoxDriver();
    }
}
```

---

#### **L - Liskov Substitution Principle (LSP)**

Derived classes should be substitutable for base classes.

**✅ Correct Usage:**
```java
WebDriver driver = new ChromeDriver();  // Can be substituted
// or
WebDriver driver = new FirefoxDriver(); // Seamless replacement
```

---

#### **I - Interface Segregation Principle (ISP)**

Clients should not depend on interfaces they don't use.

**❌ Bad Example:**
```java
interface BrowserActions {
    void click();
    void uploadFile();
    void takeScreenshot();
    void executeSQL();  // Not all browsers need this
}
```

**✅ Good Example:**
```java
interface Clickable {
    void click();
}
interface Uploadable {
    void uploadFile();
}
interface Screenshotable {
    void takeScreenshot();
}
```

---

#### **D - Dependency Inversion Principle (DIP)**

High-level modules should not depend on low-level modules.

**❌ Bad Example:**
```java
class NotificationService {
    EmailService email = new EmailService();
    public void send() {
        email.sendEmail();  // Tightly coupled
    }
}
```

**✅ Good Example:**
```java
interface MessageService {
    void sendMessage();
}
class NotificationService {
    private MessageService service;
    
    public NotificationService(MessageService service) {
        this.service = service;  // Loosely coupled
    }
    
    public void send() {
        service.sendMessage();
    }
}
```

---

#### **SOLID in Selenium Framework:**

| Principle | Application |
|-----------|-------------|
| **SRP** | Separate: PageClasses, Utilities, Listeners, Reports |
| **OCP** | Add new browsers without modifying existing code |
| **LSP** | ChromeDriver, FirefoxDriver can replace WebDriver |
| **ISP** | Separate interfaces: Clickable, Uploadable, Waitable |
| **DIP** | Tests depend on WebDriver interface, not ChromeDriver directly |

---

## 🔄 CI/CD Integration

### GitHub Actions
Automated tests on push/pull requests. See `.github/workflows/` for pipeline definition.

### Jenkins
Declarative pipelines for scheduled execution and reporting.

---

## 📖 Additional Resources

- [Selenium Official Documentation](https://www.selenium.dev/)
- [TestNG User Guide](https://testng.org/)
- [Maven Documentation](https://maven.apache.org/)
- [ExtentReports v5](https://www.extentreports.com/)

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📝 License

This project is open source and available under the MIT License.

---

## 👤 Author

**UITestAutomationFramework Contributors**

For questions or support, please create an issue in the repository.

---

**Last Updated:** June 11, 2026  
**Framework Version:** 1.0 (ExtentReports v5.1.1)  
**Status:** ✅ Production Ready
