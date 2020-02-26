package brightpod;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;

public class AddTaskPage extends BasePage {

    @FindBy(xpath = "//*[contains(text(), 'My tasks')]")
    WebElement listName;

    @FindBy(xpath = "//*[contains(text(), 'Add Task')]")
    WebElement addTaskButton;

    @FindBy(xpath = "//textarea[@name='task_name']")
    WebElement taskTextArea;

    @FindBy(xpath = "//input[@type='submit'][@value='Add Task']")
    WebElement createTaskButton;

    public AddTaskPage() throws IOException {
    }

    public void addNewTask(String taskName) {
        listName.getText();
        addTaskButton.click();
        taskTextArea.sendKeys(taskName);
        createTaskButton.click();
    }

    @Override
    protected void waitUntilPageObjectIsLoaded() {
        webDriverWait.until(ExpectedConditions.visibilityOf(listName));
    }
}