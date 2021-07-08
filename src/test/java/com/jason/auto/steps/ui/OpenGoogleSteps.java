package com.jason.auto.steps.ui;

import com.jason.auto.managers.PageObjectManager;
import com.jason.auto.pages.websites.GooglePage;
import com.jason.auto.utilities.UrlOperationUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

@Slf4j
public class OpenGoogleSteps {

    private GooglePage googlePage;

    public OpenGoogleSteps() {
       googlePage = PageObjectManager.getGooglePage();
    }

    @Given("^I open google website$")
    public void iOpenGoogleWebsite() {
       googlePage.get();
    }

    @Then("^I search for (.*) and press enter$")
    public void searchForSomething(String search) {
        googlePage.search(search);
    }

}
