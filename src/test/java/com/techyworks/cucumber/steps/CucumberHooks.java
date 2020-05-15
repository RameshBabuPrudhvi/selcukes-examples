package com.techyworks.cucumber.steps;

import io.cucumber.java.*;
import io.github.selcukes.core.logging.Logger;
import io.github.selcukes.core.logging.LoggerFactory;

public class CucumberHooks {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public CucumberHooks(Controller controller) {
        controller.setupController();
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
    public void afterTest(Scenario scenario) {
        logger.info(() -> "After Scenario .." + scenario.getName());

    }
}
