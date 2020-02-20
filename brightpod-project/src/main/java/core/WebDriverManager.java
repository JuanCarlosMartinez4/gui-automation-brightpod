package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverManager {
    private WebDriver webDriver;
    private static WebDriverManager webDriverManager = null;

    private WebDriverManager() {
    }

    public static WebDriverManager getInstance() {
        if (webDriverManager == null) {
            webDriverManager = new WebDriverManager();
        }
        return webDriverManager;
    }

    public WebDriver startBrowser(String browserName) {
        if (browserName.equalsIgnoreCase("Chrome")) {
            if (webDriverManager.webDriver == null) {
                webDriver = new ChromeDriver();
            }
        }

        else if (browserName.equalsIgnoreCase("Firefox")) {
            if (webDriverManager.webDriver == null) {
                webDriver = new FirefoxDriver();
            }
        }
        return webDriver;
    }

    public void quitDriver() {
        webDriver.quit();
        webDriver = null;
    }
}
