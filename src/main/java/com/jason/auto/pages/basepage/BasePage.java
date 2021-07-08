package com.jason.auto.pages.basepage;


import com.jason.auto.utilities.DriverFactory;
import com.jason.auto.utilities.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BasePage<T extends LoadableComponent<T>> extends LoadableComponent<T> {
    private static final Logger log = LoggerFactory.getLogger(BasePage.class);
    protected WebDriver driver = DriverFactory.getDriver();
    private String url;

    public BasePage() {
        PageFactory.initElements(this.driver, this);
    }

    public String getUrl() {
        return this.url;
    }

    protected void setUrl(String url) {
        this.url = url;
    }

    @Override
    protected void load() throws Error {
        this.driver.get(this.url);
    }
    @Override
    protected void isLoaded() throws Error {
        try {
            if (!this.url.equals(this.driver.getCurrentUrl())) {
                throw new Error();
            }
        } catch (Exception var2) {
            throw new Error(var2.getMessage());
        }
    }

    public void refreshPage() {
        this.driver.navigate().refresh();
    }
}