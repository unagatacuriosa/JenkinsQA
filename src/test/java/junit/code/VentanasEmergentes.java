package junit.code;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class VentanasEmergentes {
    WebDriver driver;
    WebDriverWait wait;
    @Before
    public  void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();

        driver = new ChromeDriver(chromeOptions);

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void ventanaEmergente(){
        driver.get("https://demoqa.com/alerts");

    }

    @Test
    public void moseHover(){
        driver.get("https://demoqa.com/menu/");

    }

    @Test
    public void moseHoverv2(){
        String web = "https://the-internet.herokuapp.com/hovers";

        driver.get(web);
        WebElement img3 = driver.findElement(By.xpath("//div[@class='example']/div[3]"));

        Actions action = new Actions(driver);
        action.moveToElement(img3).build().perform();

        WebElement view = driver.findElement(By.xpath("//div[@class='example']/div[3]//a[contains(text(), 'View profile')]"));

        action.moveToElement(view).click().build().perform();

        String actualWeb = "https://the-internet.herokuapp.com/users/";
        String expectedUrl = driver.getCurrentUrl();
        Assert.assertEquals("La URL esperada es: " + actualWeb + " - La URL recibida es: " + expectedUrl + ".", actualWeb, expectedUrl);
    }

    /*@After
    public void tearDown(){
        driver.quit();
    }*/
}

