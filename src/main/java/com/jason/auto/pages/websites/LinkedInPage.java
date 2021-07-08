package com.jason.auto.pages.websites;

import com.jason.auto.pages.basepage.BasePage;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class LinkedInPage extends BasePage<LinkedInPage> {
    private static final String URL = "https://www.linkedin.com/";

    // == web elements ==

    // == By locators ==

    public LinkedInPage() {
        driver.get(URL);
        log.info("im in "+URL+" page");
    }


    @Override
    protected void load() throws Error {
        driver.get(URL);
    }
    @Override
    protected void isLoaded() throws Error {

        log.info(URL+ "page has loaded");
    }
}
