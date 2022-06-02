package es.demoblaze.prueba.pages;

import io.cucumber.java.mk_latn.No;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

// Recordar añadir el extend AbstractPage
public class LoginPage extends AbstractPage{

    // Variables

    @FindBy(id = "logInModalLabel")
    private WebElement loginLabel;

    @FindBy(id = "loginusername")
    private WebElement usernameInput;

    @FindBy(id ="loginpassword")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[text() = 'Log in']")
    private WebElement loginButton;

    // Constructor de la clase
    LoginPage(WebDriver driver) {
        super(driver);

        // Sin esta línea no funcionan los @FindBy
        PageFactory.initElements(driver, this);

    }

    // Método heredaro del padre AbstractPage
    @Override
    public WebElement getPageLoadedTestElement() {
        return loginLabel;
    }

    //Métodos
    public void enterUsername(String username){
        // Recordar usar wait para que espere un poco por si la página va lenta y el elemento no ha cargado
        wait.until(ExpectedConditions.visibilityOf(usernameInput)).sendKeys(username);
    }

    public void enterPassword(String password){
        wait.until(ExpectedConditions.visibilityOf(passwordInput)).sendKeys(password);
    }

    public void clickLogin(){
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public boolean isLoginLabelDisplayed(){
        // Recordar los .isDisplayed() tienen que estar en trycatch
        // Porque el método .isDisplayed() solo devuelve true o NoSuchElementException
        // Nosotros queremos controlarla para que salga false
        try{
            return wait.until(ExpectedConditions.visibilityOf(loginLabel)).isDisplayed();
        } catch (NoSuchElementException nse){
            return false;
        }
    }

}
