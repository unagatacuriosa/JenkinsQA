package junit.page;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
@Getter
public class PagesFactory {
    private static PagesFactory pageFactories;

    private final WebDriver driver;
    private final LoginPage loginPage;
    private final InventaryPage inventaryPage;
    private final CartPage cartPage;
    private final CheckoutPage checkoutPage;
    private final LogoutPage logoutPage;


    private PagesFactory(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
        inventaryPage = new InventaryPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        logoutPage = new LogoutPage(driver);
    }

    public static void start(WebDriver driver) {
        pageFactories = new PagesFactory(driver);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public static PagesFactory getInstance(){
        return pageFactories;
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }
}
