package brightpod;

import core.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public abstract class BasePage {

    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait;

    public BasePage() throws IOException {
        webDriver = WebDriverManager.getInstance().getWebDriver();
        webDriverWait = WebDriverManager.getInstance().getWebDriverWait();
        PageFactory.initElements(webDriver, this);
//        waitUntilPageObjectIsLoaded();
    }

    protected abstract void waitUntilPageObjectIsLoaded();
}
