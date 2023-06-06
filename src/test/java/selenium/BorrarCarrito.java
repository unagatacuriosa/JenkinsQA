package selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BorrarCarrito {

    public static void main(String[] args) throws InterruptedException {
        String web = "https://www.saucedemo.com/";

        // Cargamos los drivers del navegador
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();

        // Abrimos el navegador
        driver = new ChromeDriver(chromeOptions);
        WebDriverWait wait = new WebDriverWait(driver, 10);
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
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[@data-test='error']")));
            System.out.println("Login incorrecto");
        } catch (TimeoutException te) {
            System.out.println("Login correcto");
        }

        // Incrementar el carrito
        WebElement buttonAdd = driver.findElement(By.xpath("//div[contains(normalize-space(text()),normalize-space('Sauce Labs Bolt T-Shirt'))]/ancestor::div[@class='inventory_item_description']/descendant::button"));

        //following::span para el siguiente
        buttonAdd.click();

        Thread.sleep(5000);

        // Meterse en el carrito
        WebElement contentBuy = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        contentBuy.click();

        Thread.sleep(5000);

        // Borrar contenido
        WebElement deleteButton = driver.findElement(By.xpath("//button[@data-test='remove-sauce-labs-bolt-t-shirt']"));
        deleteButton.click();

        Thread.sleep(5000);

        // Mensaje por consola
        System.out.println("Borrado de producto correcto");

        // Esperamos un tiempo y cerramos navegador
        Thread.sleep(5000);
        driver.quit();

    }
}
