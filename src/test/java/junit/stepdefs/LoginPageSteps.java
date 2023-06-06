package junit.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.page.InventaryPage;
import junit.page.LoginPage;
import junit.page.PagesFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

@Slf4j
public class LoginPageSteps {
    LoginPage loginPage = PagesFactory.getInstance().getLoginPage();

    @Given("the user is on the home page")
    public void theUserIsOnTheHomePage() {
        log.info("the user is on the home page");
        loginPage.navigateTo(LoginPage.PAGE_URL);
    }

    @And("the user provide the username {string} and password {string}")
    public void theUserProvideTheUsernameAndPassword(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("the user clicks the login button")
    public void theUserClicksTheLoginButton() {
        loginPage.clickLogin();
    }

    @Then("the user is logged successfully and is into the inventory page")
    public void theUserIsLoggedSuccessfullyAndIsIntoTheInventoryPage() {
        Assert.assertEquals("The URL is not inventory page",
                InventaryPage.PAGE_URL,
                PagesFactory.getInstance().getDriver().getCurrentUrl());
    }

    @Then("the user should be shown an invalid message")
    public void theUserShouldBeShownAnInvalidMessage() {
        Assert.assertTrue(loginPage.hasUsernamePasswordError());
    }
}
