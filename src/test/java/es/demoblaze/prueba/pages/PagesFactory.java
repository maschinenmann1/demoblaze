package es.demoblaze.prueba.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;

@Getter
public class PagesFactory {

    private static PagesFactory pagesFactories;
    private final WebDriver driver;

    // Aquí se añaden las páginas que se creen
    private final LoginPage loginPage;
    private final HomePage homePage;


    public PagesFactory(WebDriver driver) {
        this.driver = driver;

        // Aquí se inicializan las páginas
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    public static void start(WebDriver driver) {
        pagesFactories = new PagesFactory(driver);
    }

    public static PagesFactory getInstance() {
        return pagesFactories;
    }


}
