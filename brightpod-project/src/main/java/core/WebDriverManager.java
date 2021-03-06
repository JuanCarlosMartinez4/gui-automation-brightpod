package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WebDriverManager {
    private WebDriver webDriver;
    private WebDriverWait webDriverWait;

    private static WebDriverManager webDriverManager = null;
    private WebDriverFactory webDriverFactory;

    private WebDriverManager() {
        try {
//            String browser = System.getProperty("browser");
//            String url = System.getProperty("url");
            initialize();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void initialize() {
        String browser = "Chrome";
        String url = "https://app.brightpod.com/user";
        webDriver = WebDriverFactory.getWebDriver(browser);
        webDriver.manage().window().maximize();
        webDriver.get(url);
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(webDriver, 30);
    }

    public static WebDriverManager getInstance() {
        if (webDriverManager == null || webDriverManager.webDriver == null) {
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
