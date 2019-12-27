package com.techyworks.tests;

import io.github.selcukes.logging.Logger;
import io.github.selcukes.logging.LoggerFactory;
import io.github.selcukes.wdb.WebDriverBinary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class BrowserTest {
	private Logger logger = LoggerFactory.getLogger(BrowserTest.class);

	private String baseUrl = "https://www.google.com/";
	private WebDriver driver;

	@Test
	public void chromeDriverTest() {
		WebDriverBinary.chromeDriver().setup();
		driver = new ChromeDriver();
		driver.get(baseUrl);
		logger.debug(() -> driver.getTitle());
		assertEquals(driver.getCurrentUrl(), baseUrl);
	}

	@Test
	public void firefoxDriverTest() {
		WebDriverBinary.firefoxDriver().setup();
		driver = new FirefoxDriver();
		driver.get(baseUrl);
		logger.debug(() -> driver.getTitle());
		assertEquals(driver.getCurrentUrl(), baseUrl);
	}

	@AfterTest
	public void afterTest() {
		if (driver != null) {
			driver.quit();
		}
	}
}
