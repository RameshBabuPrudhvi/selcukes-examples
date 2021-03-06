package io.github.selcukes.cucumber.steps;


import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.selcukes.commons.logging.Logger;
import io.github.selcukes.commons.logging.LoggerFactory;
import io.github.selcukes.cucumber.drivers.Controller;
import io.github.selcukes.cucumber.pages.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class PdfTestSteps {
    Logger logger = LoggerFactory.getLogger(PdfTestSteps.class);
    WebDriver driver;

    public PdfTestSteps(Controller controller) {
        this.driver = controller.getDriver();
    }

    @Given("User is on Home Page")
    public void userIsOnHomePage() {
        driver.get("http://www.princexml.com/samples/");
        logger.info(() -> driver.getTitle());
    }

    @And("Dictionary PDF link is displayed")
    public void dictionaryPDFLinkIsDisplayed() {
        Assert.assertTrue(driver.findElement(By.xpath("//a[contains(@href,'dictionary.pdf')]")).isDisplayed());
    }


    @When("User clicks the Dictionary PDF link")
    public void userClicksTheDictionaryPDFLink() {
        driver.findElement(By.xpath("//a[contains(@href,'dictionary.pdf')]")).click();
    }


    @Then("User should verify {} in Browser")
    public void userShouldBeSeeTextInBrowser(String text) throws IOException {
        ReadPDF readPDF = new ReadPDF();
        logger.info(() -> "Sample Text :" + text);
        Assert.assertTrue(readPDF.verifyPDFContent(driver.getCurrentUrl(), text));
    }

    @And("PDF is displayed Browser")
    public void pdfIsDisplayedBrowser() {
        String getURL = driver.getCurrentUrl();
        Assert.assertTrue(getURL.contains(".pdf"));
    }


    @DataTableType
    public TestData testDataEntry(Map<String, String> entry) {
        return new TestData(
            entry.get("Username"),
            entry.get("Password"));
    }

    @When("User enters Credentials to login")
    public void userEntersCredentialsToLogin(List<TestData> tableData) {
        for (TestData rowData : tableData) {
            logger.info(rowData::toString);
        }
    }

}

