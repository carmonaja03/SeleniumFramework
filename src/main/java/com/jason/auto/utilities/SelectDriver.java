package com.jason.auto.utilities;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SelectDriver {
    private static final Logger log = LoggerFactory.getLogger(SelectDriver.class);
    private static final String URL_GRID_BASEPATH = "/wd/hub";
    private static final String browser = getCurrentBrowser();

    private static String getCurrentBrowser() {
        String _browser = PropertyReader.getProperty("browser");
        if (_browser == null) {
            _browser = PropertyReader.getProperty("browser");
        }

        return _browser;
    }

    public static WebDriver getDriver() {
        boolean remote = Boolean.getBoolean("remote");
        if (remote) {
            return getSeleniumGridDriver();
        } else {
            log.info("Browser: " + browser);
            String var1 = browser.toLowerCase();
            byte var2 = -1;
            switch(var1.hashCode()) {
                case -1361128838:
                    if (var1.equals("chrome")) {
                        var2 = 0;
                    }
                    break;
                case -1115062407:
                    if (var1.equals("headless")) {
                        var2 = 1;
                    }
                    break;
                case -909897856:
                    if (var1.equals("safari")) {
                        var2 = 2;
                    }
            }

            switch(var2) {
                case 0:
                    return getChromeDriver();
                case 1:
                    return getChromeHeadlessDriver();
                case 2:
                    return getSafariDriver();
                default:
                    return getGeckoDriverForFirefox();
            }
        }
    }

    private static WebDriver getGeckoDriverForFirefox() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    private static WebDriver getChromeDriver() {
        return getChromeDriver(false);
    }

    private static WebDriver getChromeDriver(Boolean headless) {
        ChromeOptions chromeOptions = new ChromeOptions();
        if (headless) {
            chromeOptions.addArguments(new String[]{"--headless"});
        }

        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(chromeOptions);
    }

    private static WebDriver getChromeHeadlessDriver() {
        return getChromeDriver(true);
    }

    private static WebDriver getSafariDriver() {
        SafariOptions options = new SafariOptions();
        return new SafariDriver(options);
    }

    private static WebDriver getSeleniumGridDriver() {
        String urlString = UrlOperationUtils.buildGridURL("/wd/hub");
        log.info("Running tests using Remote Driver with " + browser.toUpperCase() + " browser at " + urlString);
        RemoteWebDriver driver = null;

        URL url;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException var8) {
            log.error("ERROR: Could not generate URL for " + urlString);
            throw new RuntimeException(var8);
        }

        String var3 = browser;
        byte var4 = -1;
        switch(var3.hashCode()) {
            case -1361128838:
                if (var3.equals("chrome")) {
                    var4 = 1;
                }
                break;
            case -1115062407:
                if (var3.equals("headless")) {
                    var4 = 2;
                }
                break;
            case -849452327:
                if (var3.equals("firefox")) {
                    var4 = 0;
                }
        }

        switch(var4) {
            case 0:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = new RemoteWebDriver(url, firefoxOptions);
                break;
            case 1:
                ChromeOptions chromeOptions = new ChromeOptions();
                driver = new RemoteWebDriver(url, chromeOptions);
                break;
            case 2:
                ChromeOptions headlessChromeOptions = new ChromeOptions();
                headlessChromeOptions.addArguments(new String[]{"--headless"});
                driver = new RemoteWebDriver(url, headlessChromeOptions);
        }

        if (driver != null) {
            driver.setFileDetector(new LocalFileDetector());
            driver.manage().timeouts().implicitlyWait(1L, TimeUnit.SECONDS);
        }

        return driver;
    }

    private SelectDriver() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
