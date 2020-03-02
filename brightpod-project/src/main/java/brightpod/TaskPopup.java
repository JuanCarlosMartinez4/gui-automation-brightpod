package brightpod;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TaskPopup extends BasePage {

    @FindBy(css = "a[class='task_delete_sidebar']")
    WebElement removeTaskLink;

    @FindBy(css = "li[id='pods_tab']")
    WebElement podsTabIcon;

    @Override
    protected void waitUntilPageObjectIsLoaded() {

    }

    private void clickOnRemoveTaskLink() {
        removeTaskLink.click();
    }

    private void clickOnAcceptAlert() {
        webDriverWait.until(ExpectedConditions.alertIsPresent());
        Alert alert = webDriver.switchTo().alert();
        alert.accept();
    }

    public void removeTask() {
        clickOnRemoveTaskLink();
        clickOnAcceptAlert();
    }

    private void clickOnPodTabIcon() {
        podsTabIcon.click();
    }

    public void clickOnPodsTabIcon() {
        clickOnPodTabIcon();
    }
}
