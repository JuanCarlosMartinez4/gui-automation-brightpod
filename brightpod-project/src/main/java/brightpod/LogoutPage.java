package brightpod;

import core.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage extends BasePage {
    WebDriver webDriver;

    public LogoutPage(String browser, String url) {
        webDriver = WebDriverManager.getInstance().startBrowser(browser, url);
        PageFactory.initElements(webDriver, this);
    }
    @FindBy(how= How.XPATH, using="//a[@title='Logout']")
    WebElement logout;
    @FindBy(how=How.LINK_TEXT, using="Or, get back to the login page...")
    WebElement returnInitPage;

    @Override
    public void executeAction() {
        logout.click();
        returnInitPage.click();
    }
}
