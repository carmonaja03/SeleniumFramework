package com.jason.auto.pages.websites;

import com.jason.auto.pages.basepage.BasePage;
import com.jason.auto.utilities.UrlOperationUtils;
import com.jason.auto.utilities.WaitUtils;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Slf4j
public class YahooFinancePage extends BasePage<YahooFinancePage> {
    private static final String URL = "https://finance.yahoo.com/";

    // == web elements ==

    // == By locators ==
    private By searchStockCodeField = By.xpath("//*[@id='yfin-usr-qry']");
    private By stockCodeSearchResult = By.xpath("//*[@id='result-quotes-0']");

    public YahooFinancePage() {
        driver.get(URL);
        log.info("im in " + URL + " page");
    }

    public void searchStockCode(String stockCode){
    //    driver.get(URL+"qoute/"+stockCode+"?p="+stockCode+"&.tsrc=fin-srch");
        WaitUtils.getElementWhenClickable(searchStockCodeField).sendKeys(stockCode);
        WaitUtils.getElementWhenClickable(stockCodeSearchResult).click();
    }


    @Override
    protected void load() throws Error {
        log.info("loading "+URL);
    }

    @Override
    protected void isLoaded() throws Error {
        log.info(URL + "page has loaded");
    }


}
