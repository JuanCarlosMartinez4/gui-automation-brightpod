package brightpod;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TaskListPage extends BasePage {

    private final String TASK_LIST_NAME = "//span[starts-with(@id,'list-name-')][text()='%s']";

    @FindBy(how = How.CSS, using = "input[type='button'][value='New Task List']")
    WebElement newTaskListButton;

    @FindBy(id = "task_list_name")
    WebElement listNameTextBox;

    @FindBy(id = "description")
    WebElement descriptionTextArea;

    @FindBy(how = How.CSS, using = "input[type='submit'][value='Add Task List']")
    WebElement addTaskListButton;

    @Override
    protected void waitUntilPageObjectIsLoaded() {
        webDriverWait.until(ExpectedConditions.visibilityOf(newTaskListButton));
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

    private WebElement getTaskListNameByName(final String listName) {
        return webDriver.findElement(By.xpath(String.format(TASK_LIST_NAME, listName)));
    }

    public String addNewTaskList(String listName, String description) {
        clickOnNewTaskListButton();
        setListNameTextBox(listName);
        setDescriptionTextArea(description);
        clickOnAddTaskListButton();
        WebElement result = getTaskListNameByName(listName);
        return result.getText();
//        return new PodsPage();
    }
}
