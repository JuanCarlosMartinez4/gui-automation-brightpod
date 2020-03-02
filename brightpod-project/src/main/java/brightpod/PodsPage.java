package brightpod;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PodsPage extends BasePage {

    @FindBy(xpath = "//li[@id='pods_tab'][@class='active']")
    WebElement podsTab;

    @FindBy(linkText = "Create a New Pod")
    WebElement newPodButton;

    @FindBy(css = "h3[id='myModalLabel'][class='brown_modal_title']")
    WebElement modalLabel;

    @Override
    protected void waitUntilPageObjectIsLoaded() {
        webDriverWait.until(ExpectedConditions.visibilityOf(podsTab));
    }

    private void clickOnPodsTab() {
        podsTab.click();
    }

    private String verifyPostContainer() {
        return newPodButton.getText();
    }

    public String activePodsTab() {
        clickOnPodsTab();
        return verifyPostContainer();
    }

    private void clickOnNewPodButton() {
        newPodButton.click();
    }

    private String getModalLabelText() {
        return modalLabel.getText();
    }

    public String displayNewPodModal() {
        clickOnNewPodButton();
        return getModalLabelText();
    }
}
