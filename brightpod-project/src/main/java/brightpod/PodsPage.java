package brightpod;

import core.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PodsPage extends BasePage {
    private WebDriver webDriver;

    public PodsPage(String browser, String url) {
        webDriver = WebDriverManager.getInstance().startBrowser(browser, url);
        PageFactory.initElements(webDriver, this);
    }
    @FindBy(how= How.XPATH, using="//li[@id='pods_tab'][@class='active']")
    WebElement podsTab;

    @Override
    public void executeAction() {
        podsTab.click();
    }
}
