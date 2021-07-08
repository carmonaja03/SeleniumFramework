package com.jason.auto.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty","html:target/cucumber-html", "json:target/cucumber-json/TestSuite1/api.json",
                "junit:target/test-reports/cucumber-junit-api.xml", "rerun:target/rerun/ui-1.txt"},
        tags = "@seleniumeasy",
        glue = "com.jason.auto.steps.ui",
        features = "src/test/resources/ui"
)
public class RunSeleniumEasyTests {
}
