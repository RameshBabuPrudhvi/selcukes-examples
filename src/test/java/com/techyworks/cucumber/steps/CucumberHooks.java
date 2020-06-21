package com.techyworks.cucumber.steps;


import io.cucumber.java.*;
import io.github.selcukes.core.config.ConfigFactory;
import io.github.selcukes.core.logging.Logger;
import io.github.selcukes.core.logging.LoggerFactory;
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


        screenPlay = ScreenPlayBuilder.getScreenPlay(driver, scenario);
        screenPlay.start();

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
        /*String allLogs = logRecordListener.getLogRecords()
            .map(LogRecord::getMessage)
            .collect(Collectors.joining("\n  --> ", "\n--ALL Logs-- \n\n  --> ", "\n\n--End Of Logs--"));

        scenario.write(allLogs);*/
    }

    @After
    public void afterTest(Scenario scenario) {
        logger.info(() -> "After Scenario .." + scenario.getName());
        screenPlay.attachScreenshot();
        screenPlay.attachVideo();
    }
}
