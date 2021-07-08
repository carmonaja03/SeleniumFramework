package com.jason.auto.utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class WaitUtils {
    private static final Logger log = LoggerFactory.getLogger(WaitUtils.class);
    private static final long VERY_SHORT_TIMEOUT = 2L;
    private static final long SHORT_TIMEOUT = 5L;
    private static final long MEDIUM_TIMEOUT = 10L;
    private static final long LONG_TIMEOUT = 30L;
    private static final long XLONG_TIMEOUT = 60L;
    private static final long XXLONG_TIMEOUT = 90L;
    private static By loadingImage = By.xpath("//*[@id='loading']");
    private static WebDriver driver = DriverFactory.getDriver();
    private HelperUtils helperUtils;


//    public WaitUtils(WebDriver driver) {
//        WaitUtils.driver = driver;
//    }

    private static ExpectedCondition<WebElement> steadinessOfElementLocated(final By locator) {
        return new ExpectedCondition<WebElement>() {
            private WebElement _element = null;
            private Point _location = null;

            public WebElement apply(WebDriver driver) {
                if (this._element == null) {
                    try {
                        this._element = driver.findElement(locator);
                    } catch (NoSuchElementException var4) {
                        return null;
                    }
                }

                try {
                    if (this._element.isDisplayed()) {
                        Point location = this._element.getLocation();
                        if (location.equals(this._location)) {
                            return this._element;
                        }

                        this._location = location;
                    }
                } catch (StaleElementReferenceException var3) {
                    this._element = null;
                }

                return null;
            }

            public String toString() {
                return "steadiness of element located by " + locator;
            }
        };
    }

    private static ExpectedCondition<WebElement> elementLosesClass(final By locator, final String cls) {
        return new ExpectedCondition<WebElement>() {
            private WebElement element = null;

            public WebElement apply(WebDriver driver) {
                if (this.element == null) {
                    try {
                        this.element = driver.findElement(locator);
                    } catch (NoSuchElementException var3) {
                        return null;
                    }
                }

                String[] classStrings = this.element.getAttribute("class").split(" ");
                Stream var10000 = Arrays.stream(classStrings);
                String var10001 = cls;
                var10001.getClass();
                return var10000.anyMatch(var10001::equals) ? null : this.element;
            }

            public String toString() {
                return "element to lose class " + cls;
            }
        };
    }

    private static void printExceptionMessage(Exception exception) {
        String message = exception.getMessage();
        if (message.contains("\n")) {
            message = message.substring(0, message.indexOf(10)) + "\n";
        }

        log.info(message);
    }

    public static WebElement getElementWhenVisibleAfterVeryShortWait(By locator) {
        return getElementWhenVisible(locator, 2L);
    }

    public static WebElement getElementWhenVisibleAfterShortWait(By locator) {
        return getElementWhenVisible(locator, 5L);
    }

    public static WebElement getElementWhenVisibleAfterMediumWait(By locator) {
        return getElementWhenVisible(locator, 10L);
    }

    public static WebElement getElementWhenVisibleAfterLongWait(By locator) {
        return getElementWhenVisible(locator, 30L);
    }

    private static WebElement getElementWhenVisible(By locator, long timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            return (WebElement)wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException var4) {
            printExceptionMessage(var4);
            return null;
        }
    }

    private static WebElement getElementWhenVisible(WebElement element, long timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            return (WebElement)wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException var4) {
            printExceptionMessage(var4);
            return null;
        }
    }

    public static WebElement getElementWhenVisibleAfterVeryShortWait(WebElement element) {
        return getElementWhenVisible(element, 2L);
    }

    public static WebElement getElementWhenVisibleAfterShortWait(WebElement element) {
        return getElementWhenVisible(element, 5L);
    }

    public static WebElement getElementWhenVisibleAfterMediumWait(WebElement element) {
        return getElementWhenVisible(element, 10L);
    }

    public static WebElement getElementWhenVisibleAfterLongWait(WebElement element) {
        return getElementWhenVisible(element, 30L);
    }

    private static List<WebElement> getWebElementsWhenVisible(List<WebElement> elements, long timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            return (List)wait.until(ExpectedConditions.visibilityOfAllElements(elements));
        } catch (TimeoutException var4) {
            printExceptionMessage(var4);
            return Collections.emptyList();
        }
    }

    public static List<WebElement> getWebElementsWhenVisibleAfterLongWait(List<WebElement> elements) {
        return getWebElementsWhenVisible(elements, 30L);
    }

    public static List<WebElement> getWebElementsWhenVisibleAfterShortWait(List<WebElement> elements) {
        return getWebElementsWhenVisible(elements, 5L);
    }

    public static List<WebElement> getElementsWhenVisibleAfterShortWait(By locator) {
        return getElementsWhenVisible(locator, 5L);
    }

    public static List<WebElement> getElementsWhenVisible(By locator, long longTimeout) {
        Object elements = new ArrayList();

        try {
            getElementWhenVisible(locator, longTimeout);
            elements = driver.findElements(locator);
        } catch (NoSuchElementException | TimeoutException var5) {
            var5.printStackTrace();
        }

        return (List)elements;
    }

    public static void waitForElementToBeEnabled(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10L);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement getElementWhenEnabled(WebElement element) {
        waitForElementToBeEnabled(element);
        return element;
    }

    public static void waitForElementToBeEnabled(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10L);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForElementToDisappear(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10L);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static void waitForElementToDisappear(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10L);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public static void waitForElementToFlicker(By by) {
        waitForSpinnerToDisappear(by, 2L, 2L);
    }

    public static WebElement getElementAfterAnimationCompletes(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 5L);
        return (WebElement)wait.until(steadinessOfElementLocated(by));
    }

    public static WebElement getElementAfterLosesClass(By by, String cls) {
        WebDriverWait wait = new WebDriverWait(driver, 5L);
        return (WebElement)wait.until(elementLosesClass(by, cls));
    }

    public static void waitForSpinnerToDisappear() {
        waitForSpinnerToDisappear(loadingImage, 2L, 30L);
    }

    private static void waitForSpinnerToDisappear(By locator, long appearSeconds, long disappearSeconds) {
        int appearTimeSpent = waitForCssValue(locator, "display", "block", appearSeconds);
        if ((long)appearTimeSpent < appearSeconds) {
            int disappearTimeSpent = waitForCssValue(locator, "display", "none", disappearSeconds);
            if ((long)disappearTimeSpent >= disappearSeconds) {
                log.warn("[Loading Spinner] Wait at most " + disappearSeconds + " seconds for spinner to disappear: FAIL (Time spent: >" + disappearTimeSpent + " sec)");
            }
        } else {
            log.warn("[Loading Spinner] Wait at most " + appearSeconds + " seconds for spinner to appear: NOT FOUND (Time spent: >" + appearTimeSpent + " sec)");
        }

    }

    private static int waitForCssValue(By locator, String key, String value, long timeout) throws TimeoutException {
        int i;
        for(i = 0; (long)i < timeout; ++i) {
            if (driver.findElement(locator).getCssValue(key).equals(value)) {
                return i;
            }

            try {
                Thread.sleep(1000L);
            } catch (Exception var7) {
                log.warn(var7.getMessage());
            }
        }

        return i;
    }

    public static Boolean isElementDisplayed(By locator) {
        Boolean elementIsDisplayed = false;

        try {
            waitForSpinnerToDisappear();
            elementIsDisplayed = driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException var3) {
            log.info(var3.getMessage());
        }

        return elementIsDisplayed;
    }

    public static boolean waitForAjaxToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 5L);
        ExpectedCondition<Boolean> libraryLoad = (driver) -> {
            try {
                return (Boolean)((Boolean)(driver != null ? ((JavascriptExecutor)driver).executeScript("return angular.element(document.body).injector().get('$http').pendingRequests.length == 0;", new Object[0]) : null));
            } catch (Exception var2) {
                log.info("Not found: return angular.element(document.body).injector().get('$http').pendingRequests.length == 0;");
                return true;
            }
        };
        ExpectedCondition<Boolean> javascriptLoad = (driver) -> {
            return driver != null && ((JavascriptExecutor)driver).executeScript("return document.readyState;", new Object[0]).toString().equals("complete");
        };
        return (Boolean)wait.until(libraryLoad) && (Boolean)wait.until(javascriptLoad);
    }

    public static Function<WebDriver, WebElement> presenceOfElementLocated(final By locator) {
        return new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return WaitUtils.getStaleElem(locator);
            }
        };
    }

    public static WebElement getStaleElem(By by) {
        try {
            return driver.findElement(by);
        } catch (NoSuchElementException | StaleElementReferenceException var2) {
            log.info("Attempting to recover from " + var2.getClass().getSimpleName() + "...");
            return getStaleElem(by);
        }
    }

    public static WebElement getElementWhenClickable(WebElement element, long timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            return (WebElement)wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException var4) {
            printExceptionMessage(var4);
            return null;
        }
    }

    public static WebElement getElementWhenClickableAfterShortWait(WebElement element) {
        return getElementWhenClickable(element, 5L);
    }

    public static void waitForPageToLoadByJs() {
        (new WebDriverWait(driver, 10L)).until((webDriver) -> {
            return ((JavascriptExecutor)webDriver).executeScript("return document.readyState", new Object[0]).equals("complete");
        });
    }

    public static void waitForPageToLoadByElement(WebElement element) {
        try {
            element.isDisplayed();
        } catch (NoSuchElementException | NullPointerException var2) {
            getElementWhenVisibleAfterShortWait(element);
        }

    }

    /**
     * Uses VERY_SHORT_TIMEOUT to check if the loading spinner is displayed
     * Uses LONG_TIMEOUT to wait for the loading spinner to disappear
     */
    public static WebElement getElementWhenClickable(By locator, long timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException | StaleElementReferenceException e) {
            String message = "Locator " + locator + " not clickable after " + timeout + " seconds. " + e.getMessage();
            log.warn(message);
            return null;
        }
    }

    public static WebElement getElementWhenClickable(By locator) {
        return getElementWhenClickable(locator, MEDIUM_TIMEOUT);
    }

    public static void waitForElementToAppear(By locator) {
        waitForElementToAppear(locator, MEDIUM_TIMEOUT);
    }

    public static void waitForElementToAppear(By locator, long timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch(TimeoutException te) {
            log.warn("Element {} didn't appear after {} seconds.", locator, timeout);
        }
    }
//
//    public void waitForElementToAppearThenDisappear(By locator, long appearTimeout, long disappearTimeout) {
//        waitForElementToAppear(locator, appearTimeout);
//        waitForElementToDisappear(locator, disappearTimeout);
//    }
//
//    public void waitForLoadingSpinnerToDisappearV2() {
//        waitForElementToAppearThenDisappear(loadingImage, SHORT_TIMEOUT, XXLONG_TIMEOUT);
//    }
//    public Boolean isElementDisplayedByLocator(By locator){
//        Boolean elementIsDisplayed = false;
//        try {
//            waitForLoadingSpinnerToDisappearV2();
//            helperUtils.scrollElementIntoCenterView(driver.findElement(locator));
//            elementIsDisplayed = getElementWhenVisibleAfterShortWait(locator).isDisplayed();
//        } catch(NullPointerException | NoSuchElementException | StaleElementReferenceException | TimeoutException e){
//            log.warn("Element '{}' is not displayed: {}", locator, e.getMessage());
//        }
//        return elementIsDisplayed;
//    }
//    public void waitForElementToDisappear(By locator, long timeout){
//        WebDriverWait wait = new WebDriverWait(driver, timeout);
//        try {
//            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
//        } catch(TimeoutException te) {
//            log.warn("Element {} didn't disappear after {} seconds.", locator, timeout);
//        }
//    }

    public static void hardWait(long mills) throws InterruptedException {
        try {
            Thread.sleep(mills);
        } catch (Throwable var3) {
            throw var3;
        }
    }

    private WaitUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
