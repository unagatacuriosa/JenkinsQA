package junit.support;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.page.PagesFactory;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@Slf4j
public class Hooks {
    public static WebDriver driver;

    @Before
    public void before(Scenario scenario) {
        log.info("Starting: " + scenario.getName());
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        PagesFactory.start(driver);
    }

    @After
    public void after(Scenario scenario) {
        log.info("Ending: " + scenario);
        driver.close();
    }
}
