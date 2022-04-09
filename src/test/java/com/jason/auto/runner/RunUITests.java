package com.jason.auto.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        plugin = {"pretty","json:target/cucumber-report/cucumber.json"},
        tags = "@google",
        glue = "com.jason.auto.steps.ui",
        features = "src/test/resources/ui"
)
public class RunUITests {
}
