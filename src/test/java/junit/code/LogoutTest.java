package junit.code;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogoutTest {
    WebDriver driver;
    WebDriverWait wait;
    String url = "https://www.saucedemo.com";
    String username = "standard_user";
    String password = "secret_sauce";
    String expectedURL = "https://www.saucedemo.com/";
    WebElement logoutButton;
    WebElement menuButton;

    @Before
    public  void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();

        driver = new ChromeDriver(chromeOptions);

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 10);

        // Paso 1: Ir a la página web
        driver.get(url);
    }

    @Test
    public void validationLoginCorrect(){
        // Paso 2: Escribir username
        driver.findElement(By.xpath("//input[@data-test='username']")).sendKeys(username);

        // Paso 3: Escribir password
        driver.findElement(By.xpath("//input[@data-test='password']")).sendKeys(password);

        // Paso 4: Pulsar botón login
        driver.findElement(By.xpath("//input[@data-test='login-button']")).click();

        // Paso 5: Realizar el Logout.
        wait = new WebDriverWait(driver, 20);
        menuButton = driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']"));
        menuButton.click();

        wait = new WebDriverWait(driver, 20);
        logoutButton = driver.findElement(By.xpath("//a[contains(@id, 'logout_sidebar_link') and contains(text(), 'Logout')]"));
        wait = new WebDriverWait(driver, 20);
        logoutButton.click();

        // Paso 6: Validar que el logout se ha realizado llevándonos a la página del Login.
        String actualURL = driver.getCurrentUrl();

        Assert.assertEquals("ERROR: No nos encontramos en la pagina que corresponde" + actualURL, expectedURL, actualURL);
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
