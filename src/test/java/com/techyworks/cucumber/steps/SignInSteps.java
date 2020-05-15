package com.techyworks.cucumber.steps;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.DataTableType;
import io.cucumber.java.DocStringType;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.selcukes.core.logging.Logger;
import io.github.selcukes.core.logging.LoggerFactory;
import io.github.selcukes.wdb.BinaryInfo;
import io.github.selcukes.wdb.WebDriverBinary;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Map;


public class SignInSteps {
    Logger logger = LoggerFactory.getLogger(SignInSteps.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @DocStringType
    public JsonNode json(String docString) throws JsonProcessingException {
        return objectMapper.readValue(docString, JsonNode.class);
    }

    @Given("Books are defined by json")
    public void books_are_defined_by_json(JsonNode books) {
        System.out.println(books.get("price"));
    }

    @DataTableType
    public TestData testDataEntry(Map<String, String> entry) {
        return new TestData(
            entry.get("Username"),
            entry.get("Password"));
    }

    @When("User enters Credentials to login")
    public void user_enters_credentials_to_login(List<TestData> tableData) {
        for (TestData rowData : tableData) {
            System.out.println(rowData.toString());
        }
    }

    @ParameterType(".*")
    public Book book(String bookName) {
        return new Book(bookName);
    }

    @Given("{book} is my favorite book")
    public void this_is_my_favorite_book(Book book) {
        System.out.println(book.getBookName());
    }

    @Given("User is on Home Page")
    public void user_is_on_Home_Page() {
        BinaryInfo version = WebDriverBinary.chromeDriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/");
        logger.debug(()->"Version...." + version.getBinaryPath());
        WebElement ele=driver.findElement(By.name("q"));
        ele.sendKeys("Hello");

        driver.quit();
    }

    @Given("Sign In link is displayed")
    public void sign_In_link_is_displayed() {

    }

    @When("User clicks the Sign In link")
    public void user_clicks_the_Sign_In_link() {

    }

    @Then("User should be login failed message")
    public void user_should_be_login_failed_message() {
    }


}

