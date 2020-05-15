package com.techyworks.tests;

import io.github.selcukes.core.config.ConfigFactory;
import io.github.selcukes.core.logging.Logger;
import io.github.selcukes.core.logging.LoggerFactory;
import io.github.selcukes.wdb.WebDriverBinary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class BrowserTest {
	private final Logger logger = LoggerFactory.getLogger(BrowserTest.class);

	private final String baseUrl = "https://www.google.com/";
	private WebDriver driver;

	@BeforeClass
	public void beforeclass() {
		ConfigFactory.loadLoggerProperties();
	}

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
