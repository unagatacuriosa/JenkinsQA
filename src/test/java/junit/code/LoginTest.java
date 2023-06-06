package junit.code;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.page.LoginPage;
import junit.page.InventaryPage;
import junit.page.PagesFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTest {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 10);

        PagesFactory.start(driver);

        // Paso 1: Ir a la página web
        driver.get(LoginPage.PAGE_URL);
    }

    @Test
    public void validationLoginCorrect() {
        PagesFactory pagesFactory = PagesFactory.getInstance();
        LoginPage loginPage = pagesFactory.getLoginPage();

        // Paso 2: Escribir username
        loginPage.enterUsername("standard_user");

        // Paso 3: Escribir password
        loginPage.enterPassword("secret_sauce");

        // Paso 4: Pulsar botón login
        loginPage.clickLogin();

        // Paso 5: Validar URL
        Assert.assertEquals("ERROR: URL equals", InventaryPage.PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    public void validationLoginIncorrect() {
        PagesFactory pagesFactory = PagesFactory.getInstance();
        LoginPage loginPage = pagesFactory.getLoginPage();

        // Paso 2: Escribir username mal
        loginPage.enterUsername("standard_use");

        // Paso 3: Escribir password
        loginPage.enterPassword("secret_sauce");

        // Paso 4: Pulsar botón login
        loginPage.clickLogin();

        // Paso 5: Validar login incorrecto
        try {
            Assert.assertTrue("the user is correct", loginPage.hasUsernamePasswordError());
            System.out.println("Test OK");
        } catch (NoSuchElementException nsee) {
            System.out.println("Test KO");
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
