package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

//To configure specific browser setup before executing.
public class DriverSetup {
	public WebDriver driver;
	public void getWebDriver(String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			chromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")){
			firefoxDriver();
		}
		else if (browser.equalsIgnoreCase("edge")) edgeDriver();
		else System.out.println("Invalid Browser");
	}
	private void edgeDriver() {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
	}
	private void firefoxDriver() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
	}
	public void chromeDriver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	//to close the driver
	public void closeDriver() {
		driver.close();
	}
	//to quit all drivers
	public void quitDriver() {
		driver.quit();
	}
}
