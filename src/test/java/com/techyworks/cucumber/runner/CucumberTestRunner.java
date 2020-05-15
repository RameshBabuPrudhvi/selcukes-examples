package com.techyworks.cucumber.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "src/test/resources/features",
    glue = {"com.techyworks.cucumber.steps"}
    , plugin = {"pretty", "html:target/cucumber-reports", "json:target/cucumber-reports/cucumber.json"}
    , monochrome = true
    , tags = {"@login"})
public class CucumberTestRunner extends AbstractTestNGCucumberTests {
}
