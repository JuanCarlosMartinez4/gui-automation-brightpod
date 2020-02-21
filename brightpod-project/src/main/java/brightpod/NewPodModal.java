package brightpod;

import core.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class NewPodModal extends BasePage {
    private String newPodName;
    private WebDriver webDriver;

    public NewPodModal(String newPodName, String browser, String url) {
        webDriver = WebDriverManager.getInstance().startBrowser(browser, url);
        PageFactory.initElements(webDriver, this);
        this.newPodName = newPodName;
    }

    @FindBy(how= How.LINK_TEXT, using="Create a New Pod")
    WebElement newPodButton;
    @FindBy(how= How.LINK_TEXT, using="Create a Blank Pod")
    WebElement blankPodButton;
    @FindBy(how= How.XPATH, using="//input[@type='text'][@id='project-name']")
    WebElement projectNameTextBox;
    @FindBy(how= How.XPATH, using="//button[@class='btn btn-success btn-primary new-project-button']")
    WebElement createPodAndInvitePeopleButton;

    @Override
    public void executeAction() {
        newPodButton.click();
        blankPodButton.click();
        projectNameTextBox.sendKeys(newPodName);
        createPodAndInvitePeopleButton.click();
    }
}