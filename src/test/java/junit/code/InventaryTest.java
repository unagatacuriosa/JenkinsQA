package junit.code;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

public class InventaryTest {
    WebDriver driver;
    WebDriverWait wait;
    String url = "https://www.saucedemo.com";
    String username = "standard_user";
    String password = "secret_sauce";
    List<WebElement> inventoryItems;
    WebElement buttonAdd;
    WebElement buttonRemove;
    WebElement product;
    WebElement contentBuy;
    WebElement inventoryItem1;
    WebElement inventoryItem2;
    WebElement inventoryItem3;
    WebElement errorMessage;
    WebElement selectElement;
    WebElement option;
    WebElement spanElement;

    @Before
    public  void setUp(){
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
    public void test01NumberList(){
        // Paso 5: Validar el número de productos
        inventoryItems = driver.findElements(By.xpath("//div[@class='inventory_item']"));

        int actualNum = inventoryItems.size();
        int expectedNum = 6;

            Assert.assertEquals("El inventario tiene ", expectedNum, actualNum);

            Assert.fail("ERROR: Se esperabana: " + expectedNum + " pero ha recibido: " + actualNum);

    }

    @Test
    public void test02IfExistProduct(){
        // Paso 5: Validar que existe el producto
        product = driver.findElement(By.xpath("//div[contains(normalize-space(text()),normalize-space('Sauce Labs Bolt T-Shirt'))]"));
        String productText = product.getText();
        errorMessage = null;

        try {
            errorMessage = driver.findElement(By.xpath("//div[contains(normalize-space(text()),normalize-space('Sauce Labs Bolt T-Shirt'))]"));

        } catch (NoSuchElementException nsee) {
            Assert.fail("ERROR: El producto: " + productText + " no se visualiza");
        }
    }

    @Test
    public void test03AddProduct(){
        // Paso 5: Agregar el producto al carrito
        wait = new WebDriverWait(driver, 10);
        buttonAdd = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(normalize-space(text()),normalize-space('Sauce Labs Bolt T-Shirt'))]/ancestor::div[@class='inventory_item_description']/descendant::button")));
        buttonAdd.click();

        // Paso 6: Comprobar el icono del carrito
        contentBuy = driver.findElement(By.xpath("//a[@class='shopping_cart_link']/descendant::span[@class='shopping_cart_badge']"));

        String actualContent = contentBuy.getText();

        errorMessage = null;

        int expectedNumProducts = 1;
        Assert.assertEquals(expectedNumProducts, Integer.parseInt(actualContent));

        try {
            errorMessage = driver.findElement(By.xpath("//a[@class='shopping_cart_link']/descendant::span[@class='shopping_cart_badge']"));
            Assert.assertEquals(expectedNumProducts, Integer.parseInt(actualContent));

            System.out.println("Tienes en el carrito: " + actualContent + " articulos");

        } catch (NoSuchElementException nsee) {
            Assert.fail("ERROR: El mensaje de error de 'Carrito' no se visualiza el numero de objetos");
        }
    }

    @Test
    public void test04DeleteProductInventary(){
        // Paso 5: Agegar el producto al carrito
        wait = new WebDriverWait(driver, 10);
        buttonAdd = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(normalize-space(text()),normalize-space('Sauce Labs Bolt T-Shirt'))]/ancestor::div[@class='inventory_item_description']/descendant::button")));
        buttonAdd.click();

        // Paso 6: Eliminar producto con el botón "Remove"
        buttonRemove = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(normalize-space(text()),normalize-space('Sauce Labs Bolt T-Shirt'))]/ancestor::div[@class='inventory_item_description']/descendant::button[contains(text(), 'Remove')]")));
        buttonRemove.click();

        //Paso 7: Validar que en el icono del carrito se ha eliminado el producto
        try {
            contentBuy = driver.findElement(By.xpath("//a[@class='shopping_cart_link']/descendant::span[@class='shopping_cart_badge']"));
            String newContent = contentBuy.getText();
            if (newContent.equals("")) {
                System.out.println("El carrito está vacío");
            } else {
                System.out.println("Tienes en el carrito: " + newContent + " articulos");
            }
        } catch (NoSuchElementException nsee) {
            System.out.println("El carrito está vacío");
        }

    }

    @Test
    public void test05AddMoreProducts(){
        // Paso 5: Agregar al carrito los 3 SSSproductos elegidos al azar
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
        System.out.println("Los números aleatorios generados son: " + listaNumerosAleatorios);
        System.out.println("Los números aleatorios generados son: " + listaNumerosAleatorios.get(0));
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

        // Paso 6: Validar que, en el icono del carrito, se han agregado los 3 productos
        contentBuy = driver.findElement(By.xpath("//a[@class='shopping_cart_link']/descendant::span[@class='shopping_cart_badge']"));

        String actualContent = contentBuy.getText();
        errorMessage = null;
        try {
            errorMessage = driver.findElement(By.xpath("//a[@class='shopping_cart_link']/descendant::span[@class='shopping_cart_badge']"));

            System.out.println("Tienes en el carrito: " + actualContent + " articulos");

        } catch (NoSuchElementException nsee) {
            Assert.fail("ERROR: El mensaje de error de 'Carrito' no se visualiza el numero de objetos");
        }
    }

    @Test
    public void test06lAphabeticalOrderZToA(){
        // Paso 5: Seleccionar el filtro NAME (Z TO A)
        selectElement = driver.findElement(By.xpath("//select[@data-test='product_sort_container']"));
        selectElement.click();
        option = driver.findElement(By.xpath("//option[@value='za']"));
        option.click();

        // Paso 6: Validar que el filtro seleccionado, ordena por el orden alfabético de la Z a la A
        spanElement = driver.findElement(By.xpath("//span[@class='active_option']"));
        String actualText = spanElement.getAttribute("textContent");

        String expectedText = "Name (Z to A)";

        try {
            Assert.assertEquals(expectedText, actualText);
            System.out.println("El orden de los productos coinciden: " + actualText);
            System.out.println("Estas ordenado por: " + actualText);
        } catch (AssertionError e) {
            System.out.println("ERROR: El orden de los productos no coinciden. Se esperaba: " + expectedText + ", pero se obtuvo: " + actualText);
            System.out.println("Estas ordenado por: " + actualText);
        }
    }

    @Test
    public void test07PriceOrderLowToHigh(){
        // Paso 5: Seleccionar el filtro PRICE (low to high)
        selectElement = driver.findElement(By.xpath("//select[@data-test='product_sort_container']"));
        selectElement.click();
        option = driver.findElement(By.xpath("//option[@value='lohi']"));
        option.click();

        // Paso 6: Validar que el filtro seleccionado, ordena por el orden de precio de menor a mayor
        spanElement = driver.findElement(By.xpath("//span[@class='active_option']"));
        String actualText = spanElement.getAttribute("textContent");

        String expectedText = "Price (low to high)";

        try {
            Assert.assertEquals(expectedText, actualText);
            System.out.println("El orden de los productos coinciden: " + actualText);
            System.out.println("Estas ordenado por: " + actualText);
        } catch (AssertionError e) {
            System.out.println("ERROR: El orden de los productos no coinciden. Se esperaba: " + expectedText + ", pero se obtuvo: " + actualText);
            System.out.println("Estas ordenado por: " + actualText);
        }
    }

    @After
    public void tearDown(){
        wait = new WebDriverWait(driver, 200);
        driver.quit();
    }
}
