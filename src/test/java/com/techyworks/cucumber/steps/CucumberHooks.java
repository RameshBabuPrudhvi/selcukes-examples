package com.techyworks.cucumber.steps;


import io.cucumber.java.*;
import io.github.selcukes.core.logging.Logger;
import io.github.selcukes.core.logging.LoggerFactory;
import io.github.selcukes.devtools.DevToolsService;
import io.github.selcukes.devtools.core.Screenshot;
import io.github.selcukes.devtools.services.ChromeDevToolsService;
import io.github.selcukes.reports.video.Recorder;
import io.github.selcukes.reports.video.VideoRecorder;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class CucumberHooks {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    WebDriver driver;
    Recorder recorder;

    public CucumberHooks(Controller controller) {

        controller.setupController();
        driver = controller.getDriver();
        recorder=new VideoRecorder();
    }

    @Before
    public void beforeTest(Scenario scenario) {
        
        recorder.start();

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

        String videoPath= recorder.stopAndSave(scenario.getName().replace(" ","_")).getAbsolutePath();

        StringBuilder htmlToEmbed = new StringBuilder();

        htmlToEmbed.append("<video width=\"864\" height=\"576\" controls>")
            .append("<source src=")
            .append(videoPath).append(" type=\"video/mp4\">")
            .append("Your browser does not support the video tag.")
            .append("</video>");

        byte[] objToEmbed = htmlToEmbed.toString().getBytes();
        scenario.embed(objToEmbed, "text/html",scenario.getName());

        ChromeDevToolsService devToolsService = DevToolsService.getDevToolsService(driver);
        byte[] screenshot = Screenshot.captureFullPageAsBytes(devToolsService);
        scenario.embed(screenshot, "image/png", "screenshot");

    }
}
