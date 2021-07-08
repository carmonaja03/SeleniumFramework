package com.jason.auto.managers;

import com.jason.auto.pages.websites.GooglePage;
import com.jason.auto.pages.websites.LinkedInPage;
import com.jason.auto.pages.websites.SeleniumEasyPage;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PageObjectManager {
    private GooglePage googlePage;
    private LinkedInPage linkedInPage;
    private SeleniumEasyPage seleniumEasyPage;

    public GooglePage getGooglePage() {
        return (googlePage == null) ? googlePage = new GooglePage() : googlePage;
    }

    public LinkedInPage getLinkedInPage() {
        return (linkedInPage == null) ? linkedInPage = new LinkedInPage() : linkedInPage;
    }
    public SeleniumEasyPage getSeleniumEasyPage() {
        return (seleniumEasyPage == null) ? seleniumEasyPage = new SeleniumEasyPage() : seleniumEasyPage;
    }
}
