package com.techyworks.cucumber.steps;


import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.selcukes.core.logging.Logger;
import io.github.selcukes.core.logging.LoggerFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class SignInSteps {
    Logger logger = LoggerFactory.getLogger(SignInSteps.class);
    WebDriver driver;

    @DataTableType
    public TestData testDataEntry(Map<String, String> entry) {
        return new TestData(
            entry.get("Username"),
            entry.get("Password"));
    }

    @When("User enters Credentials to login")
    public void user_enters_credentials_to_login(List<TestData> tableData) {
        for (TestData rowData : tableData) {
            logger.debug(rowData::toString);
        }
    }


    @Given("User is on Home Page")
    public void user_is_on_Home_Page() {
        driver.get("http://www.princexml.com/samples/");
        logger.debug(() -> driver.getTitle());
    }

    @Given("Dictionary PDF link is displayed")
    public void dictionary_PDF_link_is_displayed() {

    }

    @When("User clicks the Dictionary PDF link")
    public void user_clicks_the_Dictionary_PDF_link() {
        driver.findElement(By.xpath("//a[contains(@href,'dictionary.pdf')]")).click();
    }

    @Then("User should verify {word} in Browser")
    public void userShouldBeSeeTextInBrowser(String text) throws IOException {
        ReadPDF readPDF = new ReadPDF();
        Assert.assertTrue(readPDF.verifyPDFContent(driver.getCurrentUrl(), text));
    }
}

