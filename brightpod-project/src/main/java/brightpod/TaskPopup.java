package brightpod;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;

public class TaskPopup extends BasePage {
    private HashMap<String, String> fieldsText;
    @FindBy(css = "a[class='task_delete_sidebar']")
    private WebElement removeTaskLink;

    @FindBy(css = "li[id='pods_tab']")
    private WebElement podsTabIcon;

    @FindBy(xpath = "//div[@class='task_name_header header-title-text']")
    private WebElement taskNameLabel;

    @FindBy(xpath = "//div[contains(@style, 'color:#333;font-size:14px;')]")
    private WebElement assignedName;

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

    private String getTaskName() {
        return taskNameLabel.getText();
    }

    private String getAssignedName() {
        return assignedName.getText();
    }

    public HashMap<String, String> getFieldsText() {
        fieldsText = new HashMap<>();
        fieldsText.put("name", getTaskName());
        fieldsText.put("member", getAssignedName());
        return fieldsText;
    }
}
