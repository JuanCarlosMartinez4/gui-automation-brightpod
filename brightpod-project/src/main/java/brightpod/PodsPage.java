package brightpod;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PodsPage extends BasePage {

    @FindBy(how= How.XPATH, using="//li[@id='pods_tab'][@class='active']")
    WebElement podsTab;

    @FindBy(xpath = "//*[contains(text(),'All Pods')]")
    WebElement podsContainerTitle;

    @FindBy(how = How.LINK_TEXT, using = "Create a New Pod")
    WebElement newPodButton;

    @Override
    protected void waitUntilPageObjectIsLoaded() {
        webDriverWait.until(ExpectedConditions.visibilityOf(podsTab));
    }

    private void clickOnPodsTab() {
        podsTab.click();
    }

    private void verifyPostContainer() {
        System.out.println(podsContainerTitle.getText());
    }

    public PodsPage activePodsTab() {
        clickOnPodsTab();
        verifyPostContainer();
        return this;
    }

    private void clickOnNewPodButton() {
        newPodButton.click();
    }

    public NewPodModal createNewPod() {
        clickOnNewPodButton();
        return new NewPodModal();
    }
}
