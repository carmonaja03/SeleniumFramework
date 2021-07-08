package com.jason.auto.utilities;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class HelperUtils {

    private WaitUtils waitUtils;
    public WebDriver driver;

    public static boolean isUsable(String value) {
        return (value != null && value.length() > 0);
    }

    public static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getRandomUiString() {
        return "UI Auto " + RandomStringUtils.random(10, true, true)
                + " " + HelperUtils.getCurrentDate();
    }

    public static String getRandomApiString() {
        return "API Auto " + RandomStringUtils.random(10, true, true)
                + " " + HelperUtils.getCurrentDate();
    }
    public void scrollElementIntoCenterView(WebElement element) {
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("arguments[0].scrollIntoView({block:\"center\",inline:\"center\"});", element);
    }

    public void scrollElementIntoCenterView(By locator) {
        waitUtils.waitForElementToAppear(locator);
        WebElement element = waitUtils.getElementWhenVisibleAfterMediumWait(locator);
        scrollElementIntoCenterView(element);
    }

    public String getUsernameForRole(String role) {

        String username;

        switch (role) {

            case "User":
                username = PropertyReader.getProperty("USER.USERNAME");
                break;
            case "Admin":
                username = PropertyReader.getProperty("ADMIN.USERNAME");
                break;
            case "Incorrect Username":
                username = PropertyReader.getProperty("INCORRECT.USERNAME");
                break;
            default:
                throw new IllegalArgumentException("Invalid role: " + role);
        }
        return username;
    }
}
