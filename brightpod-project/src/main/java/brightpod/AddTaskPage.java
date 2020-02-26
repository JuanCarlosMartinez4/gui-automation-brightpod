package brightpod;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Manages tasks
 */
public class AddTaskPage extends BasePage {

    @FindBy(xpath = "//*[contains(text(), 'My tasks')]")
    WebElement listName;

    @FindBy(xpath = "//*[contains(text(), 'Add Task')]")
    WebElement addTaskButton;

    @FindBy(xpath = "//textarea[@name='task_name']")
    WebElement taskTextArea;

    @FindBy(xpath = "//input[@type='submit'][@value='Add Task']")
    WebElement createTaskButton;

    /**
     * Adds a new Task
     * @param taskName to be created
     */
    public void addNewTask(String taskName) {
        listName.getText();
        addTaskButton.click();
        taskTextArea.sendKeys(taskName);
        createTaskButton.click();
    }

    /**
     * Allows to waits until the page object is loaded
     */
    @Override
    protected void waitUntilPageObjectIsLoaded() {
        webDriverWait.until(ExpectedConditions.visibilityOf(listName));
    }
}