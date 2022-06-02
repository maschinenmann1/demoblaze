package es.demoblaze.prueba.stepdefs;

import es.demoblaze.prueba.pages.HomePage;
import es.demoblaze.prueba.pages.LoginPage;
import es.demoblaze.prueba.pages.PagesFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Collections;
import java.util.List;

public class LoginPageSteps {

    // Creamos una instancia de pages factory
    PagesFactory pf = PagesFactory.getInstance();
    HomePage hp = pf.getHomePage();
    LoginPage lg = pf.getLoginPage();

    @Given("the user is on the home page")
    public void userInHomePage() {
        hp.navigateTo(HomePage.PAGE_URL);
    }

    @And("the user clicks the login button in the home page")
    public void clickLoginButtonInHome() {
        hp.clickLoginButton();
    }


    @And("the user can see the login form")
    public void seeLoginButton() {
        Assert.assertTrue("Can't see the form", lg.isLoginLabelDisplayed());
    }

    @When("the user provides the username {string}")
    public void theUserProvidesTheUsername(String username) {
        lg.enterUsername(username);
    }

    @And("the user provides the password {string}")
    public void theUserProvidesThePassword(String password) {
        lg.enterPassword(password);
    }

    @And("the user clicks the login button in the form")
    public void clickLoginButtonInForm() {
        lg.clickLogin();
    }

    @Then("the user is logged successfully")
    public void theUserIsLoggedSuccessfully() {
        Assert.assertTrue("The user isnt logged", hp.isNameOfUserDisplayed());
    }
}
