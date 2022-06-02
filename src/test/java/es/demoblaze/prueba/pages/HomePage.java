package es.demoblaze.prueba.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends AbstractPage {

    public static final String PAGE_URL = "https://www.demoblaze.com/index.html";

    @FindBy(id = "login2")
    private WebElement loginButton;

    @FindBy(id = "nameofuser")
    private WebElement nameOfUser;

    HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public WebElement getPageLoadedTestElement() {
        return loginButton;
    }

    public void clickLoginButton(){
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public boolean isNameOfUserDisplayed(){
        try{
            return wait.until(ExpectedConditions.visibilityOf(nameOfUser)).isDisplayed();
        } catch (NoSuchElementException nse){
            return false;
        }
    }



}
