package brightpod;

import core.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Allows to initialize page values.
 */
public abstract class BasePage {

    // Variable of api.
    protected WebDriver webDriver;

    // Waiting time.
    protected WebDriverWait webDriverWait;

    /**
     * Constructor of Base Page.
     */
    public BasePage() {
        webDriver = WebDriverManager.getInstance().getWebDriver();
        webDriverWait = WebDriverManager.getInstance().getWebDriverWait();
        PageFactory.initElements(webDriver, this);
        waitUntilPageObjectIsLoaded();
    }

    /**
     * Allows to wait for some element.
     */
    protected abstract void waitUntilPageObjectIsLoaded();
}
