package com.jason.auto.steps.ui;

import com.jason.auto.managers.PageObjectManager;
import com.jason.auto.pages.websites.LinkedInPage;
import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LinkedInSteps {
    private LinkedInPage linkedInPage;

    public LinkedInSteps() {
        linkedInPage = PageObjectManager.getLinkedInPage();
    }

    @Given("^I open linkedin website$")
    public void openLinkedInWebsite() {
            linkedInPage.get();
        }

//
//    public LinkedInSteps() {
//        linkedInPage = PageObjectManager.;
//        @Given("^I open linkedin website$", (
//                lin
//        ) -> {
//        });
//    }


}
