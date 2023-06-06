package junit.page;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

@Slf4j
public abstract class AbstractPage {
    private final WebDriver driver;
    protected Wait<WebDriver> wait;

    AbstractPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10, 500);
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected boolean isPageLoaded(WebElement element) {
        boolean isLoad = false;
        try {
            isLoad = element.isDisplayed();
        } catch (NoSuchElementException elementException) {
            elementException.printStackTrace();
        }
        return isLoad;
    }

    public abstract WebElement getPageLoadedTestElement();

    public void navigateTo(String url) {
        try {
            driver.navigate().to(url);
        } catch (java.lang.Exception e) {
            if (e instanceof TimeoutException) {
                log.info("Timeout loading home page");
            } else if (e instanceof ScriptTimeoutException) {
                log.info("Script timeout loading home page");
            } else {
                log.error(e.getMessage());
            }
        }
    }
}

