package screenshot;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenShot {
	public static final Logger log = LogManager.getLogger(ScreenShot.class.getName());
	
	public void CaptureScreenShot(WebDriver driver, String scenario_name){	
		try {
			File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			SimpleDateFormat s=new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
			String d = s.format(new Date());
			FileHandler.copy(source, new File("ScreenShots/"+d+"-"+scenario_name+".png"));
			log.info("Screenshot Taken");
		}  catch (Exception e) {
			log.info(e.getMessage());
		}
	}
}
