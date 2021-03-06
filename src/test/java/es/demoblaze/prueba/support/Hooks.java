package es.demoblaze.prueba.support;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import es.demoblaze.prueba.pages.PagesFactory;
import es.demoblaze.prueba.utils.Flags;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.remote.BrowserType.EDGE;
import static org.openqa.selenium.remote.BrowserType.FIREFOX;

@Slf4j
public class Hooks {
    public static WebDriver driver;

    @Before()
    public static void before(Scenario scenario) {
        log.info("Starting test: " + scenario.getName());
        driver = createWebDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        PagesFactory.start(driver);
    }

    // comando para ejecutar un tag específico en firefox y headless
    // mvn clean test -Dcucumber.filter.tags="@testcase-01" -Dbrowser=firefox -Dheadless=true
    private static WebDriver createWebDriver() {
        String browser = Flags.getInstance().getBrowser();
        boolean isHeadless = Flags.getInstance().isHeadless();

        switch (browser) {
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (isHeadless) {
                    firefoxOptions.setHeadless(true);
                }
                return new FirefoxDriver(firefoxOptions);
            case EDGE:
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (isHeadless) {
                    chromeOptions.setHeadless(true);
                }
                return new ChromeDriver(chromeOptions);
        }
    }

    @After()
    public static void after(Scenario scenario) throws IOException {
        log.info("Ending test: " + scenario.getName());

        if(scenario.isFailed()){
            log.info(scenario.getName() + " is Fail");
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failure Image");
        }

        driver.close();
    }

}
