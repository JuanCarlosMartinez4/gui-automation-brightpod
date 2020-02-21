package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WebDriverManager {
    private WebDriver webDriver;
    private WebDriverWait webDriverWait;

    private static WebDriverManager webDriverManager = null;
    private WebDriverFactory webDriverFactory = new WebDriverFactory();

    private WebDriverManager() {
        // Leer del gradle.properties con que browsere va a correr
        String browser = "Firefox";
        String url = "https://app.brightpod.com/user";
        initialize(browser, url);

    }

    private void initialize(String browser, String url) {
        webDriver = webDriverFactory.getWebDriver(browser);
        webDriver.manage().window().maximize();
        webDriver.get(url);
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(webDriver, 30);
    }

    public static WebDriverManager getInstance() {
        if (webDriverManager == null) {
            webDriverManager = new WebDriverManager();
        }
        return webDriverManager;
    }

    public void quitDriver() {
        webDriver.quit();
        webDriver = null;
    }

    public WebDriverWait getWebDriverWait() {
        return webDriverWait;
    }

    public WebDriver getWebDriver() {
    return webDriver;
    }
}
