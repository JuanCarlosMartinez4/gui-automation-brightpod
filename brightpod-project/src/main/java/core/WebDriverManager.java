package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

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

    public WebDriver startBrowser(String browserName, String url) {
        if (browserName.equalsIgnoreCase("Chrome")) {
            if (webDriverManager.webDriver == null) {
                webDriver = new ChromeDriver();
                webDriver.manage().window().maximize();
                webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                webDriver.get(url);
            }
        }

        else if (browserName.equalsIgnoreCase("Firefox")) {
            if (webDriverManager.webDriver == null) {
                webDriver = new FirefoxDriver();
                webDriver.manage().window().maximize();
                webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                webDriver.get(url);
            }
        }
        return webDriver;
    }

    public void quitDriver() {
        webDriver.quit();
        webDriver = null;
    }
}
