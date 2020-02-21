package brightpod;

import core.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PodsPage {
    private WebDriver webDriver;

    public PodsPage() {
        webDriver = WebDriverManager.getInstance().startBrowser("Firefox");
        PageFactory.initElements(webDriver, this);
    }
    @FindBy(how= How.XPATH, using="//li[@id='pods_tab'][@class='active']")
    WebElement podsTab;
    @FindBy(how=How.XPATH, using="//a[@title='Logout']")
    WebElement logout;
    @FindBy(how=How.LINK_TEXT, using="Or, get back to the login page...")
    WebElement returnInitPage;

    public void clickOnPodsTab(){
        podsTab.click();
    }

    public void clickOnLogout(){
        logout.click();
    }

    public void clickOnReturnInit(){
        returnInitPage.click();
    }

    public void quit() {
        WebDriverManager.getInstance().quitDriver();
    }
}
