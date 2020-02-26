package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ReadProperties;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class WebDriverManager {
    private WebDriver webDriver;
    private WebDriverWait webDriverWait;

    private static WebDriverManager webDriverManager = null;
    private WebDriverFactory webDriverFactory = new WebDriverFactory();

    private WebDriverManager() throws IOException {
        Properties properties = ReadProperties.propertiesFileReader("config.properties");
        String browser = properties.getProperty("browser");
        String url = properties.getProperty("url");
        initialize(browser, url);

    }

    private void initialize(String browser, String url) {
        webDriver = webDriverFactory.getWebDriver(browser);
        webDriver.manage().window().maximize();
        webDriver.get(url);
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(webDriver, 30);
    }

    public static WebDriverManager getInstance() throws IOException {
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
