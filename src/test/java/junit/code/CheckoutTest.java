package junit.code;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

import static java.lang.Double.parseDouble;
import static org.junit.Assert.assertEquals;


public class CheckoutTest {
    WebDriver driver;
    WebDriverWait wait;
    String url = "https://www.saucedemo.com";
    String username = "standard_user";
    String password = "secret_sauce";
    List<WebElement> inventoryItems;
    WebElement inventoryItem1;
    WebElement inventoryItem2;
    WebElement inventoryItem3;
    WebElement goCart;
    WebElement goCheckout;
    WebElement firstName;
    WebElement lastName;
    WebElement postalCode;
    WebElement continueButton;
    List<WebElement> cartItems;
    WebElement subtotalPrice;
    WebElement goFinish;
    WebElement endOrder;


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
    public void CheckOutFinalPriceTest() {
        // Paso 5. Agregar al carrito los 3 productos elegidos al azar.
        inventoryItems = driver.findElements(By.xpath("//div[@class='inventory_item']"));
        int actualNum = inventoryItems.size();

        Random rand = new Random();
        Set<Integer> numerosAleatorios = new HashSet<>();
        while (numerosAleatorios.size() < 3) {
            int numeroAleatorio = rand.nextInt(actualNum);
            numerosAleatorios.add(numeroAleatorio);
        }
        List<Integer> listaNumerosAleatorios = new ArrayList<>(numerosAleatorios);
        Collections.sort(listaNumerosAleatorios);

        int num1 = listaNumerosAleatorios.get(0);
        int num2 = listaNumerosAleatorios.get(1);
        int num3 = listaNumerosAleatorios.get(2);

        wait = new WebDriverWait(driver, 20);
        inventoryItem1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='inventory_item'][" + num1 + "]/descendant::button")));
        wait = new WebDriverWait(driver, 20);
        inventoryItem1.click();

        wait = new WebDriverWait(driver, 20);
        inventoryItem2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='inventory_item'][" + num2 + "]/descendant::button")));
        wait = new WebDriverWait(driver, 20);
        inventoryItem2.click();

        wait = new WebDriverWait(driver, 20);
        inventoryItem3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='inventory_item'][" + num3 + "]/descendant::button")));
        wait = new WebDriverWait(driver, 20);
        inventoryItem3.click();

        // Paso 6. Ir al carrito.
        goCart = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        goCart.click();

        // Paso 7. Realizar el Checkout del producto.
        goCheckout = driver.findElement(By.xpath("//button[@data-test='checkout']"));
        goCheckout.click();

        // Paso 8. Rellenar datos del checkout y continuar.
        firstName = driver.findElement(By.xpath("//input[@data-test='firstName']"));
        firstName.sendKeys("Pepito");

        lastName = driver.findElement(By.xpath("//input[@data-test='lastName']"));
        lastName.sendKeys("Delospalotes");

        postalCode = driver.findElement(By.xpath("//input[@data-test='postalCode']"));
        postalCode.sendKeys("50009");

        wait = new WebDriverWait(driver, 20);
        continueButton = driver.findElement(By.xpath("//input[@data-test='continue']"));
        continueButton.click();

        // Paso 10. Validar que el precio total del pedido (Item total) es la suma del importe de los productos seleccionados en el inventario
        cartItems = driver.findElements(By.xpath("//div[@class='cart_item']/descendant::div[@class='inventory_item_price']"));

        double suma = 0.0;
        List<WebElement> cartItems = driver.findElements(By.xpath("//div[@class='cart_item']/descendant::div[@class='inventory_item_price']"));

        for (WebElement cartItem : cartItems) {
            String priceSinSigno = cartItem.getText().substring(1);
            double priceNumerico = parseDouble(priceSinSigno);
            suma += priceNumerico;
        }
        System.out.println("La suma de los precios es: $" + suma);

        // Paso 9. Finalizar Checkout
        subtotalPrice = driver.findElement(By.xpath("//div[@class='summary_subtotal_label']"));
        String subtotalPriceSinSigno = subtotalPrice.getText().substring(13);
        double subtotalPriceNumerico = parseDouble(subtotalPriceSinSigno);

        System.out.println("El subtotal es: $" + subtotalPriceNumerico);

        try {
            assertEquals(subtotalPriceNumerico, suma, 0.001);
            System.out.println("El precio esperado es: $" + subtotalPriceNumerico + ". Y el precio recibido es: $" + suma);

            goFinish = driver.findElement(By.xpath("//button[@data-test='finish']"));
            goFinish.click();


        } catch (AssertionError e) {
            System.out.println("ERROR: Los precios no son iguales. El precio esperado es: $" + subtotalPriceNumerico + ". Y el precio recibido es: $" + suma);
        }


    }

    @Test
    public void PlaceOrderTest() {
        // Paso 5. Agregar al carrito los 3 productos elegidos al azar.
        inventoryItems = driver.findElements(By.xpath("//div[@class='inventory_item']"));
        int actualNum = inventoryItems.size();

        Random rand = new Random();
        Set<Integer> numerosAleatorios = new HashSet<>();
        while (numerosAleatorios.size() < 1) {
            int numeroAleatorio = rand.nextInt(actualNum);
            numerosAleatorios.add(numeroAleatorio);
        }
        List<Integer> listaNumerosAleatorios = new ArrayList<>(numerosAleatorios);
        Collections.sort(listaNumerosAleatorios);

        int num1 = listaNumerosAleatorios.get(0);

        wait = new WebDriverWait(driver, 20);
        inventoryItem1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='inventory_item'][" + num1 + "]/descendant::button")));
        wait = new WebDriverWait(driver, 20);
        inventoryItem1.click();

        // Paso 6. Ir al carrito.
        goCart = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        goCart.click();

        // Paso 7. Realizar el Checkout del producto.
        goCheckout = driver.findElement(By.xpath("//button[@data-test='checkout']"));
        goCheckout.click();

        // Paso 8. Rellenar datos del checkout y continuar.
        firstName = driver.findElement(By.xpath("//input[@data-test='firstName']"));
        firstName.sendKeys("Pepito");

        lastName = driver.findElement(By.xpath("//input[@data-test='lastName']"));
        lastName.sendKeys("Delospalotes");

        postalCode = driver.findElement(By.xpath("//input[@data-test='postalCode']"));
        postalCode.sendKeys("50009");

        wait = new WebDriverWait(driver, 20);
        continueButton = driver.findElement(By.xpath("//input[@data-test='continue']"));
        continueButton.click();

        // Paso 9. Finalizar Checkout
        goFinish = driver.findElement(By.xpath("//button[@data-test='finish']"));
        goFinish.click();

        // Paso 10. Validar que el precio total del pedido (Item total) es la suma del importe de los productos seleccionados en el inventario
        try {
            String textesperado = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";
            endOrder = driver.findElement(By.xpath("//div[@class='complete-text']"));
            String endText = endOrder.getText();

            assertEquals(textesperado, endText);
            System.out.println("El texto esperado es: " + textesperado + ". Y el texto recibido es: " + endText);

        } catch (AssertionError e) {
            System.out.println("ERROR: Los textos no son iguales");
        }

    }


    @After
    public void tearDown() {
        wait = new WebDriverWait(driver, 3000);
        driver.quit();
    }
}
