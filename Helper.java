package Base;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper {
	public static void checkExistence(WebDriver driver) {
		 // Explicit wait condition for alert
	      WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
	      //alertIsPresent() condition applied
	      if(w.until(ExpectedConditions.alertIsPresent())==null)
	      System.out.println("Alert not exists");
	      else
	      System.out.println("Alert exists");
	}
	public static String getData(String key) {
		String data = "";
		try {
			InputStream in = new FileInputStream("config.properties");
			Properties file = new Properties();
			file.load(in);
			data = file.getProperty(key);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(key + " not available");;
			
		}
		return data;
	}
}
