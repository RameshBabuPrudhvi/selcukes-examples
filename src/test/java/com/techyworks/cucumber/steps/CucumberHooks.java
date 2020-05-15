package com.techyworks.cucumber.steps;

import io.cucumber.java.*;
import io.github.selcukes.core.logging.Logger;
import io.github.selcukes.core.logging.LoggerFactory;
import io.github.selcukes.wdb.WebDriverBinary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CucumberHooks {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    WebDriver driver;

    @Before
    public void beforeTest(Scenario scenario) {

        logger.info(() -> "Before Scenario .." + scenario.getName());
        WebDriverBinary.chromeDriver().setup();
        driver = new ChromeDriver();

    }

    @BeforeStep
    public void beforeStep(Scenario scenario) {

        logger.info(() -> "Before Step");
    }

    @AfterStep
    public void afterStep(Scenario scenario) {

        logger.info(() -> "After Step");
        logger.info(() -> "Scenario Status:" + scenario.getStatus());

    }

    @After
    public void afterTest(Scenario scenario) {

        logger.info(() -> "After Scenario .." + scenario.getName());
        driver.quit();

    }
}
