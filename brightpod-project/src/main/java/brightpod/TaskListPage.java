package brightpod;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;

public class TaskListPage extends BasePage {

    @FindBy(how = How.CSS, using = "input[type='button'][value='New Task List']")
    WebElement newTaskListButton;

    @FindBy(id = "task_list_name")
    WebElement listNameTextBox;

    @FindBy(id = "description")
    WebElement descriptionTextArea;

    @FindBy(how = How.CSS, using = "input[type='submit'][value='Add Task List']")
    WebElement addTaskListButton;

    public TaskListPage() throws IOException {
    }

    @Override
    protected void waitUntilPageObjectIsLoaded() {
        webDriverWait.until(ExpectedConditions.visibilityOf(newTaskListButton));
    }

    public PodsPage addNewTaskList(String listName, String description) throws IOException {
        clickOnNewTaskListButton();
        setListNameTextBox(listName);
        setDescriptionTextArea(description);
        clickOnAddTaskListButton();
        return new PodsPage();
    }

    private void clickOnNewTaskListButton() {
        newTaskListButton.click();
    }

    private void setListNameTextBox(String listName) {
        listNameTextBox.sendKeys(listName);
    }

    private void setDescriptionTextArea(String description) {
        descriptionTextArea.sendKeys(description);
    }

    private void clickOnAddTaskListButton() {
        addTaskListButton.click();
    }
}
