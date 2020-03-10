package brightpod;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.function.Supplier;

public class TaskPopup extends BasePage {
    private HashMap<String, String> taskInformation;

    private static final String TASK_NAME = "Task Name";
    private static final String MEMBER = "Member";
    private static final String DUE_DATE = "Due Date";
    private static final String HIGH_PRIORITY = "High Priority";

    @FindBy(css = "a[class='task_delete_sidebar']")
    private WebElement removeTaskLink;

    @FindBy(css = "li[id='pods_tab']")
    private WebElement podsTabIcon;

    @FindBy(css = "div[class='task_name_header header-title-text']")
    private WebElement taskNameLabel;

    @FindBy(xpath = "//div[contains(@style, 'color:#333;font-size:14px;')]")
    private WebElement assignedName;

    @FindBy(css = "span[id^= 'due_date_']")
    WebElement dueDateLabel;

    @FindBy(xpath = "//div[@id='myModalLabel']//a[@class='important_task asterisk']")
    WebElement highPriority;

    @FindBy(css = "button[class='close']")
    WebElement closePopup;

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

    private String getDueDate() {
        return dueDateLabel.getText();
    }

    private String getHighPriority() {
        return highPriority.getCssValue("data-original-title");
    }

    public void clickOnClosePopUp() {
        closePopup.click();
    }

    public HashMap<String, String> getTaskInformation(final HashMap<String, String> taskInformationValues) {
        taskInformation = new HashMap<>();
        HashMap<String, Supplier> strategyMapGet = composeStrategyMapGet(taskInformationValues);

        for (String field : taskInformationValues.keySet()) {
            taskInformation.put(field, strategyMapGet.get(field).get().toString());
        }
        return taskInformation;
    }

    private HashMap<String, Supplier> composeStrategyMapGet(final HashMap<String, String>listInformationValues) {
        HashMap<String, Supplier> strategyMapGet = new HashMap<>();
        strategyMapGet.put(TASK_NAME, () -> getTaskName());
        strategyMapGet.put(MEMBER, () -> getAssignedName());
        strategyMapGet.put(DUE_DATE, () -> getDueDate());
        strategyMapGet.put(HIGH_PRIORITY, () -> getHighPriority());
        return strategyMapGet;
    }
}
