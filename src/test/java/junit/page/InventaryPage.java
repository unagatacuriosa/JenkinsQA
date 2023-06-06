package junit.page;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Slf4j
public class InventaryPage extends AbstractPage {
    public static final String PAGE_URL = "https://www.saucedemo.com/inventory.html";

    // Localizadores de elementos web usando la anotación @FindBy
    @FindBy(className = "inventory_item_name")
    private List<WebElement> inventoryItemsName;
    @FindBy(className = "inventory_item_price")
    private List<WebElement> inventoryItemsPrice;
    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItems;
    /*@FindBy(xpath = "//div[contains(normalize-space(text()),normalize-space('" + inventoryItemsName[num] + "'))]")
    private WebElement product;*/
    @FindBy(xpath = ".//button[contains(text(), 'Add to cart')]")
    private WebElement addButton;
    @FindBy(xpath = ".//button[contains(text(), 'Remove')]")
    private WebElement removeButton;
    @FindBy(id = "react-burger-menu-btn")
    private WebElement openMenu;
    @FindBy(className = "shopping_cart_link")
    private WebElement cartButton;
    @FindBy(className = "product_sort_container")
    private WebElement filterButton;

    // Constructor de la página que inicializa los elementos web con PageFactory

    InventaryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Método que debe ser implementado por cada página para retornar el elemento web que indica que la página ha cargado
    @Override
    public WebElement getPageLoadedTestElement() {
        return null;
    }

    // Métodos
    public int getInventorySize() {
        int expectedInventorySize = 6;
        return inventoryItems.size();
    }
    public String getNameProduct(){
        int numKey = 1;
        return inventoryItems.get(numKey).getText();
    }
    public void addProductToCart() {
        addButton.click();
        log.info("Product add to the cart");
    }
    public void removeProductToCart() {
        removeButton.click();
        log.info("Product remove to the cart");
    }
    public void goCart() {
        cartButton.click();
        log.info("Go to cart");
    }
    public void filterItems() {
        filterButton.click();
        log.info("Go to filter items");
    }

}
