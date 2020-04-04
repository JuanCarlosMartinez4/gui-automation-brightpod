package brightpod;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewPodModal extends BasePage {

    @FindBy(id = "myModalLabel")
    private WebElement modalLabel;

    @FindBy(linkText = "Create a Blank Pod")
    private WebElement blankPodButton;

    @Override
    protected void waitUntilPageObjectIsLoaded() {
        webDriverWait.until(ExpectedConditions.visibilityOf(modalLabel));
    }

    private void clickBlankPodButton() {
        blankPodButton.click();
    }

    public FormPodPage createNewPod() {
        clickBlankPodButton();
        return new FormPodPage();
    }
}
