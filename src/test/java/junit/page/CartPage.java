package junit.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends AbstractPage{
    CartPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @Override
    public WebElement getPageLoadedTestElement() {
        return null;
    }
}
