package com.searchAPI;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features/iTunesSearchAPI.feature",
    glue = "com.searchAPI",
    tags = "@iTunesSearchAPI",
    plugin = {
        "pretty",
        "html:target/cucumber-reports",
        "json:target/cucumber-reports/cucumber.json"
    }
)
public class ITunesSearchAPIRunner extends AbstractTestNGCucumberTests {
}