package es.demoblaze.prueba.pages;

import io.cucumber.java.mk_latn.No;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

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

    public boolean isAlertDisplayed(){
        try{
            wait.until(ExpectedConditions.alertIsPresent()); // Espera hasta que la alerta esté presente
            Alert alert = getDriver().switchTo().alert(); // Se posiciona en la alerta
            alert.accept(); // Acepta la alerta
            return true; // Como la alerta estaba devuelve true
        } catch (NoAlertPresentException noAlert) { // Si la alerta no está presente da excepción
            return false; // Controlamos la excepción para que de false
        }
    }

    public String getAlertMessage(){
        String message = "";
        try{
            wait.until(ExpectedConditions.alertIsPresent()); // Espera hasta que la alerta esté presente
            Alert alert = getDriver().switchTo().alert(); // Se posiciona en la alerta
            message = alert.getText(); // Coge el texto de la alerta y se lo asigna a la variable message
            alert.accept(); // Acepta la alerta
            return message; // Devuelve el mensaje
        } catch (NoAlertPresentException noAlert) {
            return message; // Devuelve el mensaje pero vacío
        }
    }

}
