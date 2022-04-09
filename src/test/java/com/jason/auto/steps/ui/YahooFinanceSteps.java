package com.jason.auto.steps.ui;

import com.jason.auto.managers.PageObjectManager;
import com.jason.auto.pages.websites.YahooFinancePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class YahooFinanceSteps {
    private YahooFinancePage yahooFinancePage;

    public YahooFinanceSteps() {
        yahooFinancePage = PageObjectManager.getYahooFiancePage();
    }

    @Given("^I open Yahoo Finance website$")
    public void openSeleniumEasyWebsite() {
        yahooFinancePage.get();
    }

    @And("^I search for (.*) stock$")
    public void searchForTSLAStock(String stockCode) {
        yahooFinancePage.searchStockCode(stockCode);
    }
}
