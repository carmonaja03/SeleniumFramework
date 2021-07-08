package com.jason.auto.steps.ui;

import com.jason.auto.managers.PageObjectManager;
import com.jason.auto.pages.websites.SeleniumEasyPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class SeleniumEasySteps {

    private SeleniumEasyPage seleniumEasyPage;

    public SeleniumEasySteps() {
        seleniumEasyPage = PageObjectManager.getSeleniumEasyPage();
    }

    @Given("I open Selenium Easy website")
    public void openSeleniumEasyWebsite() {
        seleniumEasyPage.get();
        seleniumEasyPage.closePopUp();
    }

    @When("^I click (.*) in Navigation menu$")
    public void clickInputForms(String navName) {
        seleniumEasyPage.clickNavBar(navName);
    }

    @And("^I click (.*) in Sub menu$")
    public void clickSubMenu(String subNavName) {
        seleniumEasyPage.clickSubNavBar(subNavName);
    }

    @And("^I enter message (.*) in Single Input Field$")
    public void enterMessageInSingleInputField(String message) {
        seleniumEasyPage.enterMessageInSimpleInputField(message);
        seleniumEasyPage.simpleInputFieldShowMessageButton();
    }

    @Then("^I validate that Your Message displays (.*)$")
    public void validateThatYourMessageDisplays(String message) {
        Assert.assertEquals(message, seleniumEasyPage.simpleInputFieldYourMessageLabel(message));
    }
}
