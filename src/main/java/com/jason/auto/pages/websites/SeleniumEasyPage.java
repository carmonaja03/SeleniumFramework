package com.jason.auto.pages.websites;

import com.jason.auto.pages.basepage.BasePage;
import com.jason.auto.utilities.WaitUtils;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

@Slf4j
public class SeleniumEasyPage extends BasePage<SeleniumEasyPage> {
    private static final String URL = "https://www.seleniumeasy.com/test/";

    // == web elements ==

    // == By locators ==
    private By seleniunEasyPopUp = By.xpath("//div[@id='at-cv-lightbox-win']//a[@title='Close']");
    private By simpleInputTextField = By.xpath("//input[@id='user-message']");
    private By simpleInputShowMessageButton = By.xpath("//button[@onclick='showInput();']");

    public SeleniumEasyPage() {
        driver.get(URL);
        log.info("im in " + URL + " page");
    }


    @Override
    protected void load() throws Error {
        driver.get(URL);
    }

    @Override
    protected void isLoaded() throws Error {

        log.info(URL + "page has loaded");
    }

    public void closePopUp() {
        WaitUtils.waitForElementToAppear(seleniunEasyPopUp);
        WaitUtils.getElementWhenClickable(seleniunEasyPopUp).click();
    }

    public void clickNavBar(String navName) {
        By navNameMenu = By.xpath("//div[@id='navbar-brand-centered']//a[normalize-space()='" + navName + "']");
      WaitUtils.getElementWhenClickable(navNameMenu).click();
    }

    public void clickSubNavBar(String subNavName) {
        By subNavNameMenu = By.xpath("//ul[@class='dropdown-menu']//a[text()='" + subNavName + "']");
       WaitUtils.getElementWhenClickable(subNavNameMenu).click();
    }

    public void enterMessageInSimpleInputField(String message) {
        WaitUtils.getElementWhenClickable(simpleInputTextField).sendKeys(message);
    }

    public void simpleInputFieldShowMessageButton() {
        WaitUtils.getElementWhenClickable(simpleInputShowMessageButton).click();
    }

    public String simpleInputFieldYourMessageLabel(String message) {
        By simpleInputShowMessageButton = By.xpath("//div[@id='user-message']//span[text()='" + message + "']");
        return WaitUtils.getElementWhenClickable(simpleInputShowMessageButton).getText();
    }


}
