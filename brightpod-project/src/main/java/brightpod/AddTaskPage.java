package brightpod;

import com.sun.scenario.effect.impl.prism.PrImage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

/**
 * Manages tasks
 */
public class AddTaskPage extends BasePage {
    private final String ADD_NEW_TASK_BUTTON = "//span[text()='%s']//ancestor::li//a[contains(@class,'add_new_task')]";

    private final String TASK_TEXTAREA = "//span[text()='%s']//ancestor::li//textarea[contains(@name,'task_name')]";

    private final String COMBOBOX_SELECTOR = "//span[text()='%s']//ancestor::li//select[starts-with(@id,'task_assign_to_')]";

    private final String HIGH_PRIORITY_CHECKBOX = "//span[text()='%s']//ancestor::li//label[@class='checkbox-inline']";

    private final String ADD_TASK_BUTTON = "//span[text()='%s']//ancestor::li//input[contains(@class,'add-task-button')]";

    private final String CREATED_TASK_NAME = "//span[text()='%s']//ancestor::li//a[contains(text(),'%s')]";

    @FindBy(xpath = "//*[contains(text(), 'Add Task')]")
    WebElement addTaskButton;

    private WebElement getAddNewTaskButton(final String listName) {
        return webDriver.findElement(By.xpath(String.format(ADD_NEW_TASK_BUTTON, listName)));
    }

    private WebElement getTaskTextArea(final String listName) {
        return webDriver.findElement(By.xpath(String.format(TASK_TEXTAREA, listName)));
    }

    private Select getCombobox(final String listName) {
        return new Select(webDriver.findElement(By.xpath(String.format(COMBOBOX_SELECTOR, listName))));
    }

    private WebElement getHighPriorityCheckbox(final String listName) {
        return webDriver.findElement(By.xpath(String.format(HIGH_PRIORITY_CHECKBOX, listName)));
    }

    private WebElement getAddTaskButton(final String listName) {
        return webDriver.findElement(By.xpath(String.format(ADD_TASK_BUTTON, listName)));
    }

    private WebElement getCreatedTaskName(final String listName, final String taskName) {
        return webDriver.findElement(By.xpath(String.format(CREATED_TASK_NAME, listName, taskName)));
    }

    private void clickOnAddNewTaskButton(final String listName) {
        getAddNewTaskButton(listName).click();
    }

    private void setTaskTextArea(final String listName, final String taskName) {
        getTaskTextArea(listName).sendKeys(taskName);
    }

    private void setMemberToTask(final String listName, final String memberName) {
        getCombobox(listName).selectByVisibleText(memberName);
    }

    private void setHighPriorityCheckbox(final String listName) {
        getHighPriorityCheckbox(listName).click();
    }

    private void clickOnAddTaskButton(final String listName) {
        getAddTaskButton(listName).click();
    }

    private String getCreatedTaskNameText(final String listName, final String taskName) {
        return getCreatedTaskName(listName, taskName).getText();
    }

    public String createNewTask(final String listName, final String taskName, final String memberName) {
        clickOnAddNewTaskButton(listName);
        setTaskTextArea(listName, taskName);
        setMemberToTask(listName, memberName);
        setHighPriorityCheckbox(listName);
        clickOnAddTaskButton(listName);
        return getCreatedTaskNameText(listName, taskName);
    }

    /**
     * Allows to waits until the page object is loaded
     */
    @Override
    protected void waitUntilPageObjectIsLoaded() {
        webDriverWait.until(ExpectedConditions.visibilityOf(addTaskButton));
    }
}