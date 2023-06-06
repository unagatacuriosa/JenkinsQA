package selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class IncrementarCarrito {

    public static void main(String[] args) throws InterruptedException {
        String web = "https://www.saucedemo.com/";

        // Cargamos los drivers del navegador
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();

        // Abrimos el navegador
        driver = new ChromeDriver(chromeOptions);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

        // Cargamos la web
        driver.get(web);

        // Obtenemos el usuario y lo ingresamos
        WebElement userInput = driver.findElement(By.xpath("//input[@data-test='username']"));
        userInput.sendKeys("standard_user");

        // Obtenemos el password y lo ingresamos
        WebElement userPass = driver.findElement(By.xpath("//input[@data-test='password']"));
        userPass.sendKeys("secret_sauce");

        // Obtenemos el botón y lo pulsamos
        WebElement loginButton = driver.findElement(By.xpath("//input[@data-test='login-button']"));
        loginButton.click();

        // Comprobamos si es correcto o incorrecto y enviamos un mensaje
        /*WebElement errorMesage = driver.findElement(By.xpath("//h3[@data-test='error']"));

        if (errorMesage.isDisplayed()) {
            System.out.println("Login Incorrecto");
        } else {
            System.out.println("Login Correcto");
        }*/

        // Incrementar el carrito
        WebElement buttonAdd = driver.findElement(By.xpath("//div[contains(normalize-space(text()),normalize-space('Sauce Labs Bolt T-Shirt'))]/ancestor::div[@class='inventory_item_description']/descendant::button"));

        buttonAdd.click();

        // Comprobar carrito
        WebElement contentBuy = driver.findElement(By.xpath("//a[@class='shopping_cart_link']/descendant::span[@class='shopping_cart_badge']"));
        String content = contentBuy.getText();
        System.out.println("Tienes en el carrito: " + content + " articulos");


        // Esperamos un tiempo y cerramos navegador
        Thread.sleep(5000);
        driver.quit();


    }
}
