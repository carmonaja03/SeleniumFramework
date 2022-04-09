package com.jason.auto.hooks;

import com.jason.auto.utilities.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

@Slf4j
public class Hooks {

    private static boolean dunit = false;

    @Before("@front_end")
    public void beforeAll() {
        if (!dunit) {
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    try {
                        DriverFactory.destroyDriver();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            log.info("Inside Before All block");
            dunit = true;
        }
    }

    @Before
    public void initializeTest(Scenario scenario) {
        log.info("Starting Test: " + scenario.getName());
    }

    @After
    public void TeardownTest(Scenario scenario) {
        log.info("Finishing Test: " + scenario.getName());
        //Remove comment on 2 lines to close browser after each scenarios
        DriverFactory.destroyDriver();
        log.info("Quitting driver after finishing: " + scenario.getName());
    }

    @After("@front_end")
    public void tearDownTest(Scenario scenario) {
        if (scenario.isFailed()) {
            log.info("Taking failure screenshot for Scenario: " + scenario.getName());
            final byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot");
        }
//        Uncomment 2 lines below and comment out BeforeAll method above to close browser after each scenarios
//        DriverFactory.destroyDriver();
//        log.info("Quitting driver after finishing: " + scenario.getName());
    }
}
