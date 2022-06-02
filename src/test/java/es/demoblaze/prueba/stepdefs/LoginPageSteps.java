package es.demoblaze.prueba.stepdefs;

import es.demoblaze.prueba.pages.BasePage;
import es.demoblaze.prueba.pages.LoginPage;
import es.demoblaze.prueba.pages.PagesFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginPageSteps {

    // Creamos una instancia de pages factory
    PagesFactory pf = PagesFactory.getInstance();
    BasePage bp = pf.getBasePage();
    LoginPage lp = pf.getLoginPage();

    @Given("the user is on the home page")
    public void userInHomePage() {
        bp.navigateTo(BasePage.PAGE_URL);
    }

    @And("the user clicks the login button in the home page")
    public void clickLoginButtonInHome() {
        bp.clickLoginButton();
    }


    @And("the user can see the login form")
    public void seeLoginButton() {
        Assert.assertTrue("Can't see the form", lp.isLoginLabelDisplayed());
    }

    @When("the user provides the username {string}")
    public void theUserProvidesTheUsername(String username) {
        lp.enterUsername(username);
    }

    @And("the user provides the password {string}")
    public void theUserProvidesThePassword(String password) {
        lp.enterPassword(password);
    }

    @And("the user clicks the login button in the form")
    public void clickLoginButtonInForm() {
        lp.clickLogin();
    }

    @Then("the user is logged successfully")
    public void theUserIsLoggedSuccessfully() {
        Assert.assertTrue("The user isnt logged", bp.isNameOfUserDisplayed());
    }

    @Then("the user can see the error alert")
    public void theUserCanSeeTheError() {
        Assert.assertTrue("The error message isn't visible", lp.isAlertDisplayed());
    }

    @Then("the user can see the specific error {string}")
    public void theUserCanSeeTheSpecificError(String message) {
        Assert.assertEquals("The error message isn't visible", message, lp.getAlertMessage());
    }
}
