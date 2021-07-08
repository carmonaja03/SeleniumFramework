package com.jason.auto.pages.websites;

import com.jason.auto.pages.basepage.BasePage;

import com.jason.auto.utilities.WaitUtils;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.fail;

@Slf4j
public class GooglePage extends BasePage<GooglePage> {
    private static final String URL = "https://www.google.com/";

    // == web elements ==
    @FindBy(xpath = "//input[@title='Search']")
    private WebElement inputSearchBox;

    // == By locators ==
    private By gitHubLink = By.xpath("//h3[text()='GitHub: Where the world builds software · GitHub']");
    private By inputSearchBoxField = By.xpath("//input[@title='Search']");

    public GooglePage() {
        driver.get(URL);
        log.info("im in "+URL+" page");
    }

    public void inputMessage(String message) {
        inputSearchBox.sendKeys(message);
    }
    @Override
    protected void load() throws Error {
        driver.get(URL);
    }
    @Override
    protected void isLoaded() throws Error {
        WaitUtils.getElementWhenClickable(inputSearchBoxField);
        log.info(URL+ "page has loaded");
    }

    public void search(String search) {
        inputSearchBox.sendKeys(search + Keys.ENTER);
    }
    public String searchResults(String searchResult){
        By gitHubSearchResultLink = By.xpath("//h3[normalize-space()='"+searchResult+": Where the world builds software · GitHub']");
        return  WaitUtils.getElementWhenClickable(gitHubSearchResultLink).getText();
    }
}
