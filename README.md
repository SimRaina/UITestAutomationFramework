# Hybrid-UI-Test-Automation-Framework-POM
This is a Hybrid UI Test Automation Framework using:
  1. Selenium WebDriver as UI automation tool
  2. TestNG as test execution engine
  3. Java as Programming Language
  4. Maven as build tool
  5. Extent Reporting for test HTML reports 
  6. Fillo as data manipulation tool for reading and writing data in excel sheet
 
# Using the code in your local
1. Download the zip or clone using HTTP or SSH in your local. It is a public repository.
2. Once downloaded, open your IDE (Eclipse or Intellij or any other) and click on File > Import
3. Import as Existing Maven Project and choose the folder path location in your local.
4. It will show pom.xml file choosen. Select OK
5. Maven will build the project. Once done successfully, you can use the project and explore it.

**Few Interview Questions related to Selenium + Java + TestNG + Maven framework**
**Q1) How would you design a scalable Selenium automation framework using Java and TestNG?
Ans1) A scalable framework should follow a layered architecture:**
src/test/java
 ├── tests
 ├── pages
 ├── utilities
 ├── listeners
 ├── reports
 ├── drivers
 ├── api
 └── data
Key Components

Page Object Model (POM): 
Separate page locators and page actions
Improves maintainability

Base Test:
Driver initialization
Configuration loading
Reporting setup

Utilities:
Wait utilities
Screenshot utility
Excel/JSON readers
Config readers

Listeners:
Capture failures
Generate reports

TestNG:
Test execution
DataProviders
Parallel execution

CI/CD:
Jenkins/GitHub Actions integration

Benefits:
Reusable code
Easy maintenance
Parallel execution support
Cross-browser support
Easy reporting

**Q2) Explain the Page Object Model (POM)?
Ans2) POM is a design pattern where each web page is represented by a Java class.**
**Login Page Example**
_public class LoginPage {
    WebDriver driver;
    @FindBy(id="username")
    WebElement username;
    @FindBy(id="password")
    WebElement password;
    @FindBy(id="loginBtn")
    WebElement loginBtn;
    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public void login(String user,String pass){
        username.sendKeys(user);
        password.sendKeys(pass);
        loginBtn.click();
    }
}_
**Test Class**
_LoginPage login = new LoginPage(driver);
login.login("admin","password");_

Advantages:
Better readability
Reduced code duplication
Easy maintenance
Separation of test logic and UI logic

**Q3) How do you manage test execution across different browsers and environments (QA/UAT/Staging)?
Ans3) Use Configuration Files**
**config.properties**
_browser=chrome
environment=QA
url=https://qa.company.com_

**Environment-Based Config**
_QA=https://qa.company.com
UAT=https://uat.company.com
STAGE=https://stage.company.com_

**Browser Selection**
_String browser = prop.getProperty("browser");
switch(browser){
    case "chrome":
        driver = new ChromeDriver();
        break;

    case "firefox":
        driver = new FirefoxDriver();
        break;
}_

**TestNG XML**
_<parameter name="browser" value="chrome"/>_

Can also be passed through Maven:
_mvn test -Dbrowser=firefox -Denv=UAT_

**Q4) How do you run parallel tests in TestNG and avoid thread-safety issues?
Ans4) TestNG XML
<suite parallel="tests" thread-count="4">**

Problem
WebDriver is not thread-safe.
**Solution: ThreadLocal**
_private static ThreadLocal<WebDriver> driver =
        new ThreadLocal<>();

public static WebDriver getDriver(){
    return driver.get();
}

public static void setDriver(WebDriver webDriver){
    driver.set(webDriver);
}_

Benefits:
Each thread gets its own browser instance
No session overlap
Stable parallel execution

**Q5) How do you handle dynamic elements with changing IDs?
Ans5) Dynamic ID Example**
_id="user_12345"
id="user_67890"_

XPath Using Contains
_//input[contains(@id,'user_')]_

Starts-With
_//input[starts-with(@id,'user_')]_

Relative XPath
_//label[text()='Username']/following-sibling::input_

**Q6) Can you automate file upload/download in Selenium?
Ans6) Upload**
sendKeys()
_driver.findElement(By.id("upload"))
      .sendKeys("C:\\Files\\sample.pdf");_
      
Robot Class (when sendKeys doesn't work)
_Robot robot = new Robot();_

AutoIT (Windows)
Used when native OS dialog appears.

**Download Validation
Set Download Directory**
_ChromeOptions options = new ChromeOptions();
prefs.put("download.default_directory",
          "C:\\Downloads");_
          
Verify File Exists
**File file = new File(path);
Assert.assertTrue(file.exists());**

**Q7) How do you capture screenshots on test failures and attach them to Extent Reports?
Ans7) Screenshot Utility**
_public String captureScreenshot(String testName)
{
    TakesScreenshot ts =
      (TakesScreenshot)driver;

    File src = ts.getScreenshotAs(OutputType.FILE);

    String path = "./Screenshots/" +
                   testName + ".png";

    FileUtils.copyFile(src,new File(path));

    return path;
}_

TestNG Listener
_public void onTestFailure(ITestResult result)
{
    String path =
      captureScreenshot(result.getName());

    extentTest.fail("Failed")
              .addScreenCaptureFromPath(path);
}_

**Q8) How do you debug flaky or intermittent test failures?
Ans8) Common Reasons:**
_Timing issues
Dynamic elements
Test data dependency
Environment instability
Parallel execution conflicts_

Debug Approach:
_Review logs
Check screenshots
Re-run locally
Analyze browser console logs
Replace hard waits with explicit waits
Verify test independence_

**Q9) Difference between Jenkins Freestyle Jobs and Pipeline Scripts
Ans9)** **Freestyle**	                          **Pipeline**
GUI-based	                              Code-based
Hard to maintain	                      Version controlled
Limited scalability	                    Highly scalable
Manual configuration	                  Jenkinsfile
Less suitable for CI/CD	                Preferred for DevOps

Pipeline Example:
_pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean test'
            }
        }
    }
}_

**Q10) How to integrate your Selenium suite with Jenkins or GitHub Actions
Ans10) Jenkins:**
Configure Maven
Pull code from Git
Execute: _mvn clean test_
Publish reports
Extent Reports/Allure Reports

**GitHub Actions:**
_name: Selenium Tests

on: [push]

jobs:
  test:
    runs-on: ubuntu-latest

    steps:
      - uses: actions/checkout@v4

      - name: Run Tests
        run: mvn clean test_
        
Benefits:
Scheduled execution
CI/CD integration
Automated reporting

**Q11) How to implement data-driven testing using Excel, CSV, or JSON
Ans11) TestNG DataProvider**
_@DataProvider
public Object[][] loginData(){

    return new Object[][]{
        {"admin","pass1"},
        {"user","pass2"}
    };
}_
_@Test(dataProvider="loginData")
public void loginTest(String user,
                      String password){
}_

Excel:
Apache POI
_Workbook wb = new XSSFWorkbook(file);_

CSV
_BufferedReader br = new BufferedReader(new FileReader(file));_

JSON
Jackson
_ObjectMapper mapper = new ObjectMapper();_

**Q12) Strategies to keep test scripts reusable and maintainable
Ans12) Best Practices**
Follow POM
Create reusable utilities
Centralize locators
Use constants/config files
Avoid duplicate code
Use meaningful naming
Use TestNG listeners
Follow coding standards
Separate test data from scripts

Framework Principles
**DRY** (Don't Repeat Yourself)
**KISS** (Keep It Simple, Stupid)
**SOLID** Principles: SOLID is a set of 5 object-oriented design principles that help make code maintainable, scalable, reusable, and easier to test. These principles are frequently discussed in Java Automation, Selenium Framework, and Software Development interviews.
S — Single Responsibility Principle (SRP): A class should have only one reason to change, meaning it should have only one responsibility.

Bad Example
_public class Employee {
    public void calculateSalary() {
        System.out.println("Calculating salary");
    }
    public void saveToDatabase() {
        System.out.println("Saving employee data");
    }
    public void generateReport() {
        System.out.println("Generating report");
    }
}_

Problems:
Salary calculation changes
Database changes
Report format changes
All affect the same class.

Good Example
_public class SalaryService {
    public void calculateSalary() {
        System.out.println("Calculating salary");
    }
}
public class EmployeeRepository {
    public void saveEmployee() {
        System.out.println("Saving employee");
    }
}
public class ReportService {
    public void generateReport() {
        System.out.println("Generating report");
    }
}_

Now each class has a single responsibility.

In Selenium Framework example, instead of:
_public class LoginTest {
    // Login actions
    // Excel reading
    // Screenshot capture
    // Reporting
}_

Create separate classes:
LoginPage
ExcelUtil
ScreenshotUtil
ReportManager

O — Open/Closed Principle (OCP)
Software entities should be:
Open for Extension
Closed for Modification

You should be able to add new functionality without modifying existing code.

Bad Example
_public class PaymentService {
    public void pay(String type) {

        if(type.equals("CreditCard")) {
            System.out.println("Credit Card Payment");
        }
        else if(type.equals("PayPal")) {
            System.out.println("PayPal Payment");
        }
    }
}_

Adding UPI means modifying existing code.

Good Example
_public interface Payment {
    void pay();
}
public class CreditCardPayment implements Payment {
    public void pay() {
        System.out.println("Credit Card Payment");
    }
}
public class PayPalPayment implements Payment {
    public void pay() {
        System.out.println("PayPal Payment");
    }
}
public class UpiPayment implements Payment {
    public void pay() {
        System.out.println("UPI Payment");
    }
}_

Now new payment methods can be added without changing old code.

In Selenium Framework example, instead of:
if(browser.equals("chrome"))
else if(browser.equals("firefox"))

Use:
interface BrowserDriver with separate implementations.

Adding Edge won't affect existing classes.

L — Liskov Substitution Principle (LSP)
A child class should be able to replace its parent class without breaking the application.

Bad Example
_class Bird {
    public void fly() {
        System.out.println("Flying");
    }
}
class Penguin extends Bird {

    @Override
    public void fly() {
        throw new UnsupportedOperationException();
    }
}_

Problem:
_Bird bird = new Penguin();
bird.fly();_
Application breaks.

Good Example
_class Bird {
}
class FlyingBird extends Bird {

    public void fly() {
        System.out.println("Flying");
    }
}
class Sparrow extends FlyingBird {
}
class Penguin extends Bird {
}_

Now inheritance makes sense.

In Selenium example:
_WebDriver driver = new ChromeDriver();_
or
_WebDriver driver = new FirefoxDriver();_

Both can substitute WebDriver without breaking functionality.

I — Interface Segregation Principle (ISP)
Clients should not be forced to implement methods they don't use.
Use smaller, specific interfaces rather than one large interface.
Bad Example
_interface Worker {

    void work();

    void eat();

    void sleep();
}
class Robot implements Worker {

    public void work() {
        System.out.println("Working");
    }

    public void eat() {
        // Not applicable
    }

    public void sleep() {
        // Not applicable
    }
}
_
Robot doesn't need eat or sleep.

Good Example
_interface Workable {
    void work();
}
interface Eatable {
    void eat();
}
interface Sleepable {
    void sleep();
}
class Human implements Workable, Eatable, Sleepable {

    public void work() {}

    public void eat() {}

    public void sleep() {}
}
class Robot implements Workable {

    public void work() {}
}
_
Perfect separation.
In Selenium Example, instead of:
_interface BrowserActions {

    void click();

    void uploadFile();

    void takeScreenshot();

    void executeSQL();
}_

Create smaller interfaces:
**Clickable
Uploadable
Screenshotable**
Classes implement only what they need.

D — Dependency Inversion Principle (DIP)
High-level modules should not depend on low-level modules.
Both should depend on abstractions (interfaces).

Bad Example
_class EmailService {

    public void sendEmail() {
        System.out.println("Email sent");
    }
}
class Notification {

    EmailService email =
            new EmailService();

    public void send() {
        email.sendEmail();
    }
}
_
Problem:
Notification is tightly coupled to EmailService.

Good Example
_interface MessageService {

    void sendMessage();
}
class EmailService
        implements MessageService {

    public void sendMessage() {
        System.out.println("Email Sent");
    }
}
class SMSService
        implements MessageService {

    public void sendMessage() {
        System.out.println("SMS Sent");
    }
}
class Notification {

    private MessageService service;

    public Notification(MessageService service) {
        this.service = service;
    }

    public void send() {
        service.sendMessage();
    }
}_

Usage:
_Notification notification = new Notification(new EmailService());
notification.send();_
or
_Notification notification = new Notification(new SMSService());_

No code changes required.

**SOLID in Selenium Automation Framework
SRP	Separate Page Classes, Utilities, Reports
OCP	Add new browser support without modifying existing code
LSP	ChromeDriver, FirefoxDriver can replace WebDriver
ISP	Separate interfaces for screenshots, uploads, waits
DIP	Test classes depend on WebDriver interface, not ChromeDriver directly**

**Summary Answer**
_"SOLID is a set of five object-oriented design principles: Single Responsibility, Open/Closed, Liskov Substitution, Interface Segregation, and Dependency Inversion. These principles help build scalable, maintainable, and loosely coupled frameworks. In Selenium automation, I apply SOLID by separating page objects, utilities, reporting, and driver management, using interfaces and abstractions to make the framework extensible and easy to maintain."_
