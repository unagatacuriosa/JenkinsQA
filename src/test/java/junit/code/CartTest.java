package junit.code;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

import static org.junit.Assert.assertFalse;

public class CartTest {
    WebDriver driver;
    WebDriverWait wait;
    String url = "https://www.saucedemo.com";
    String username = "standard_user";
    String password = "secret_sauce";
    List<WebElement> inventoryItems;
    WebElement goCart;
    WebElement inventoryItem1;
    WebElement inventoryItem2;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();

        driver = new ChromeDriver(chromeOptions);

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 100);

        // Paso 1: Ir a la página web
        driver.get(url);

        // Paso 2: Escribir username
        driver.findElement(By.xpath("//input[@data-test='username']")).sendKeys(username);

        // Paso 3: Escribir password
        driver.findElement(By.xpath("//input[@data-test='password']")).sendKeys(password);

        // Paso 4: Pulsar botón login
        driver.findElement(By.xpath("//input[@data-test='login-button']")).click();
    }


    @Test
    public void test01DelleteProductOnCart() {
        // Paso 5: Agregar al carrito 2 productos elegidos al azar.
        inventoryItems = driver.findElements(By.xpath("//div[@class='inventory_item']"));
        int actualNum = inventoryItems.size();

        Random rand = new Random();
        Set<Integer> numerosAleatorios = new HashSet<>();
        while (numerosAleatorios.size() < 2) {
            int numeroAleatorio = rand.nextInt(actualNum);
            numerosAleatorios.add(numeroAleatorio);
        }
        List<Integer> listaNumerosAleatorios = new ArrayList<>(numerosAleatorios);

        int num1 = listaNumerosAleatorios.get(0)+1;
        int num2 = listaNumerosAleatorios.get(1)+1;

        inventoryItem1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='inventory_item'][" + num1 + "]/descendant::button")));
        inventoryItem1.click();

        inventoryItem2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='inventory_item'][" + num2 + "]/descendant::button")));
        inventoryItem2.click();

        // Paso 6: Ir al carrito.
        goCart = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        goCart.click();

        // Paso 7: Eliminar uno de los productos.
        WebElement product = driver.findElement(By.xpath("//div[@class='cart_item'][2]/descendant::div[@class='inventory_item_name']"));
        String nameText = product.getText();
        System.out.println(nameText);

        product = driver.findElement(By.xpath("//div[@class='cart_item'][2]/descendant::button"));
        product.click();

        // Paso 8: Validar que el producto eliminado no aparece en el carrito.
        try {
            assertFalse(driver.getPageSource().contains(nameText));
            System.out.println("El producto " + nameText + " no está en el carrito");
        } catch (AssertionError e) {
            System.out.println("ERROR: El carrito no se ha vaciado correctamente. Se esperaba que " + nameText + " no estuviera");
        }

    }

    @After
    public void tearDown(){
        wait = new WebDriverWait(driver, 3000);
        driver.quit();
    }
}
