package brightpod;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PodsPage extends BasePage {

    @FindBy(xpath = "//li[@id='pods_tab'][@class='active']")
    WebElement podsTab;

    @FindBy(linkText = "Create a New Pod")
    WebElement createANewPodButton;

//    @FindBy(css = "h3[id='myModalLabel'][class='brown_modal_title']")
//    WebElement modalLabel;

    @Override
    protected void waitUntilPageObjectIsLoaded() {
        webDriverWait.until(ExpectedConditions.visibilityOf(createANewPodButton));
    }

    private void clickOnPodsTab() {
        podsTab.click();
    }

    private String verifyPostContainer() {
        return createANewPodButton.getText();
    }

    public String activePodsTab() {
        clickOnPodsTab();
        return verifyPostContainer();
    }

    private void clickOnCreateANewPodButton() {
        createANewPodButton.click();
    }

//    private String getModalLabelText() {
//        return modalLabel.getText();
//    }

    public NewPodModal clickNewPodButton() {
        clickOnCreateANewPodButton();
        return new NewPodModal();
    }
}
