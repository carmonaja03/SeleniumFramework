package com.jason.auto.utilities;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static final Logger log = LoggerFactory.getLogger(DriverFactory.class);
    private static WebDriver driver;
    private static int automationMessageSpace = 45;

    private static void initializeDriver() {
        if (driver == null) {
            driver = SelectDriver.getDriver();
            driver.manage().timeouts().implicitlyWait(3L, TimeUnit.SECONDS);
            driver.manage().window().setSize(new Dimension(1366, 768 + automationMessageSpace));
            log.info("****** Screen Size: " + driver.manage().window().getSize() + "******");
        }

    }

    public static WebDriver getDriver() {
        initializeDriver();
        return driver;
    }

    public static void destroyDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }

    }

    private DriverFactory() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
