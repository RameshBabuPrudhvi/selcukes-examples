package io.github.selcukes.cucumber.drivers;

import io.github.selcukes.wdb.WebDriverBinary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Controller {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void setupController() {

        WebDriverBinary.chromeDriver().setup();
        driver = new ChromeDriver();

        /*ChromeOptions options = new ChromeOptions();
        try {
            this.driver = new RemoteWebDriver(
                new URL("http://172.18.62.149:30001/wd/hub"),
                options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }*/
    }

    public void teardownController() {
        if (driver != null) {
            driver.quit();
        }
    }

}
