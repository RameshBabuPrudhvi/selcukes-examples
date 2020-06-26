package io.github.selcukes.cucumber.steps;

import io.cucumber.java.*;
import io.github.selcukes.core.config.ConfigFactory;
import io.github.selcukes.core.logging.Logger;
import io.github.selcukes.core.logging.LoggerFactory;
import io.github.selcukes.cucumber.drivers.Controller;
import io.github.selcukes.reports.enums.RecorderType;
import io.github.selcukes.reports.screen.ScreenPlay;
import io.github.selcukes.reports.screen.ScreenPlayBuilder;
import org.openqa.selenium.WebDriver;

public class CucumberHooks {
    Logger logger = LoggerFactory.getLogger(getClass());
    WebDriver driver;
    ScreenPlay screenPlay;

    public CucumberHooks(Controller controller) {
        ConfigFactory.loadLoggerProperties();
        controller.setupController();
        driver = controller.getDriver();
    }

    @Before
    public void beforeTest(Scenario scenario) {

        screenPlay = ScreenPlayBuilder.getScreenPlay(driver)
            .withRecorder(RecorderType.FFMPEG)
            .start();

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
    public void afterTest(Scenario scenario) {
        logger.info(() -> "After Scenario .." + scenario.getName());

        screenPlay.withResult(scenario)
            .ignoreCondition()
            .attachScreenshot()
            .attachVideo();

    }
}
