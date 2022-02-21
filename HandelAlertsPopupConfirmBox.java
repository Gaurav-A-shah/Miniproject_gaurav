package handeling;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Base.DriverSetup;
import Base.Helper;

public class HandelAlertsPopupConfirmBox extends DriverSetup {

	WebDriver driver;
	Alert al;

	@BeforeTest
	@Parameters({ "browser" })
	public void getUrl(String browser) {
		getWebDriver(browser);
		driver = super.driver;
//		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get(Helper.getData("url"));//resad url from config properties file
		driver.manage().window().maximize();
//		w.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText("SwitchTo"))));
	}

	@AfterTest
	public void closeDriver() {
		quitDriver();
	}

	@Test(priority = 1)
	public void alertWithOk() throws InterruptedException {

		// clicking on Switch to tab
		driver.findElement(By.linkText("SwitchTo")).click();
		Thread.sleep(500);

		// clicking on Alerts option
		driver.findElement(By.linkText("Alerts")).click();

		// check if "Alerts with ok" is selected or not
		WebElement alertOK = driver.findElement(By.xpath(" //a[normalize-space()='Alert with OK']"));
		if (!alertOK.isSelected())
			alertOK.click();
		driver.findElement(By.xpath("(//button[contains(text(),'click the button to display an')])")).click();
		al = driver.switchTo().alert();
		al.accept();
		Thread.sleep(3000);
	}

	@Test(priority = 2)
	public void alertWithOKCancel() throws InterruptedException {
		WebElement alertOkCancel = driver.findElement(By.xpath("//a[normalize-space()='Alert with OK & Cancel']"));
		if (!alertOkCancel.isSelected())
			alertOkCancel.click();
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		al = driver.switchTo().alert();
		al.dismiss();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String msg = driver.findElement(By.xpath("//p[@id='demo']")).getText();
		System.out.println(msg);
	}

	@Test(priority = 3)
	public void alertWithTextbox() throws InterruptedException {
		WebElement alertTextbox = driver.findElement(By.xpath("//a[normalize-space()='Alert with Textbox']"));
		if (!alertTextbox.isSelected())
			alertTextbox.click();
		driver.findElement(By.xpath("//button[@class='btn btn-info']")).click();
		al = driver.switchTo().alert();
		Helper.checkExistence(driver);
		// Entering name in the prompt box
		String name = "Gaurav";
		al.sendKeys(name);
		al.accept();
		Thread.sleep(2000);
		String msg = driver.findElement(By.xpath("//p[@id='demo1']")).getText();
		System.out.println(msg);
		String expected = "Hello " + name + " How are you today";
		try {
			Assert.assertEquals(msg, "Hello " + name + " How are you today");
		} catch (Exception e) {
			System.out.println(expected);
		}
	}

}
