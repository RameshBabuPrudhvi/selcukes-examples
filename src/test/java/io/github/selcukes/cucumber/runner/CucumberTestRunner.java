package io.github.selcukes.cucumber.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "src/test/resources/features",
    glue = {"io.github.selcukes.cucumber.steps"},
    plugin = {"pretty", "html:target/cucumber-reports",
        "io.github.selcukes.cucumber.listeners.CucumberListener",
        "json:target/cucumber-reports/cucumber.json"},
    monochrome = true,
    strict = true,
    tags = {"@pdf"})
public class CucumberTestRunner extends AbstractTestNGCucumberTests {
}
