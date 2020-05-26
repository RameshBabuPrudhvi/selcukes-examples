package com.techyworks.cucumber.steps;

import io.cucumber.java.*;
import io.github.selcukes.core.logging.Logger;
import io.github.selcukes.core.logging.LoggerFactory;
import io.github.selcukes.devtools.DevToolsService;
import io.github.selcukes.devtools.core.Screenshot;
import io.github.selcukes.devtools.services.ChromeDevToolsService;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class CucumberHooks {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    WebDriver driver;

    public CucumberHooks(Controller controller) {

        controller.setupController();
        driver = controller.getDriver();
    }

    @Before
    public void beforeTest(Scenario scenario) {
        logger.info(() -> "Before Scenario .." + scenario.getName());
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
    public void afterTest(Scenario scenario) throws IOException {
        logger.info(() -> "After Scenario .." + scenario.getName());

        ChromeDevToolsService devToolsService = DevToolsService.getDevToolsService(driver);
        byte[] screenshot= Screenshot.captureFullPageAsBytes(devToolsService);
        scenario.embed(screenshot, "image/png", "screenshot");

    }
}
