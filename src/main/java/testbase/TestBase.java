package testbase;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import datamanager.ConfigReader;

public class TestBase {

	public static final Logger log = LogManager.getLogger(TestBase.class.getName());
	
	public WebDriver driver;
	String url=ConfigReader.getValueFromPropertyFile("AppUrl");
	String browser_type = "chrome";
	
	public void init() {
		selectBrowser(browser_type);
		getUrl(url);
		File file = new File("log4j2.properties");
		LoggerContext context = (LoggerContext) LogManager.getContext(false);
		context.setConfigLocation(file.toURI());
	}
	
	public void selectBrowser(String browser_type) {
		if(browser_type.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		    log.info("Chrome browser object created");
		}
	}
	
	public void getUrl(String url) {
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		log.info("URL Opened");
	}

}
