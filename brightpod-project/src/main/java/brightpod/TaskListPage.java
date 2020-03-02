package brightpod;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;

public class TaskListPage extends BasePage {

    private final String TASK_LIST_NAME = "//span[starts-with(@id,'list-name-')][text()='%s']";

    private final String TASK_LIST_DROPDOWN_LIST = "//span[text()='%s']//ancestor::li//a[@id='dropdownMenuLink']";

    private final String EDIT_TASK_LIST = "//span[text()='%s']//ancestor::li//a[@class='dropdown-item edit_tasklist tasklist_link']";

    private final String REMOVE_TASK_LIST = "//span[text()='%s']//ancestor::li//a[@class='dropdown-item delete_tasklist tasklist_link']";

    private final String LIST_FOUND_LINK = "//span[text()='%s']";

    private HashMap<String, String> fieldsText;

    @FindBy(css = "input[type='button'][value='New Task List']")
    WebElement newTaskListButton;

    @FindBy(id = "task_list_name")
    WebElement listNameTextBox;

    @FindBy(id = "description")
    WebElement descriptionTextArea;

    @FindBy(id = "client_access")
    WebElement visibleToClientCheckBox;

    @FindBy(css = "input[type='submit'][value='Add Task List']")
    WebElement addTaskListButton;

    @FindBy(css = "input[name='task_list_name'][id^='task_list_name_']")
    WebElement updateListNameTextBox;

    @FindBy(css = "textarea[name='description'][id^='tasklist_description_']")
    WebElement updateDescriptionTextArea;

    @FindBy(css = "input[id^='client_access_']")
    WebElement updateVisibleToClientCheckBox;

    @FindBy(css = "input[id='update_tasklist_button']")
    WebElement updateTaskListButton;

    @FindBy(xpath = "//a[@class='delete_tasklist tasklist_link_list'][text()='Remove']")
    WebElement removeListLink;

    @FindBy(css = "li[id='pods_tab']")
    WebElement podsTabIcon;

    @Override
    protected void waitUntilPageObjectIsLoaded() {
        webDriverWait.until(ExpectedConditions.visibilityOf(newTaskListButton));
    }

    private void clickOnNewTaskListButton() {
        newTaskListButton.click();
    }

    private void setListNameTextBox(final String listName) {
        if (listName == null)
            return;
        listNameTextBox.clear();
        listNameTextBox.sendKeys(listName);
    }

    private void setDescriptionTextArea(final String description) {
        if (description == null)
            return;
        descriptionTextArea.clear();
        descriptionTextArea.sendKeys(description);
    }

    private void checkVisibleToClientsCheckBox(boolean isVisible) {
        if (isVisible)
            visibleToClientCheckBox.click();
    }

    private void clickOnAddTaskListButton() {
        addTaskListButton.click();
    }

    private WebElement getTaskListNameByName(final String listName) {
        return webDriver.findElement(By.xpath(String.format(TASK_LIST_NAME, listName)));
    }

    private WebElement getTaskListDropdownList(final String listName) {
        return webDriver.findElement(By.xpath(String.format(TASK_LIST_DROPDOWN_LIST, listName)));
    }

    private void clickOnTaskListDropdownList(final String listName) {
        getTaskListDropdownList(listName).click();
    }

    private WebElement getEditTaskListLink(final String listName) {
        return webDriver.findElement(By.xpath(String.format(EDIT_TASK_LIST, listName)));
    }

    private void clickOnEditTaskListLink(final String listName) {
        getEditTaskListLink(listName).click();
    }

    private WebElement getRemoveTaskListLink(final String listName) {
        return webDriver.findElement(By.xpath(String.format(REMOVE_TASK_LIST, listName)));
    }

    private void clickOnRemoveTaskListLink(final String listName) {
        getRemoveTaskListLink(listName).click();
    }

    private void setUpdateListNameTextBox(final String listName) {
        if (listName == null)
            return;
        updateListNameTextBox.clear();
        updateListNameTextBox.sendKeys(listName);
    }

    private void setUpdateDescriptionTextArea(final String description) {
        if (description == null)
            return;
        updateDescriptionTextArea.clear();
        updateDescriptionTextArea.sendKeys(description);
    }

    private void checkUpdateVisibleToClientsCheckBox(boolean isVisible) {
        if (isVisible)
            updateVisibleToClientCheckBox.click();
    }

    private WebElement getListFoundLink(final String listName) {
        return webDriver.findElement(By.xpath(String.format(LIST_FOUND_LINK, listName)));
    }

    private void mouseOverFoundLink(final String listMame) {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(getListFoundLink(listMame)).perform();
    }

    private void clickOnRemoveListLink() {
        removeListLink.click();
    }

    private void clickOnUpdateTaskListButton() {
        updateTaskListButton.click();
    }

    private void clickOnPodTabIcon() {
        podsTabIcon.click();
    }

    public void clickOnPodsTabIcon() {
        clickOnPodTabIcon();
    }

    private HashMap<String, String> getFieldsText() {
        fieldsText = new HashMap<>();
        fieldsText.put("name", listNameTextBox.getAttribute("value"));
        fieldsText.put("description", descriptionTextArea.getAttribute("value"));
        return fieldsText;
    }

    private HashMap<String, String> getUpdatedFieldsText() {
        fieldsText = new HashMap<>();
        fieldsText.put("listName", updateListNameTextBox.getAttribute("value"));
        fieldsText.put("listDescription", updateDescriptionTextArea.getAttribute("value"));
        return fieldsText;
    }

    public HashMap<String, String> addNewTaskList(final String listName, final String description, boolean isVisible) {
        clickOnNewTaskListButton();
        setListNameTextBox(listName);
        setDescriptionTextArea(description);
        checkVisibleToClientsCheckBox(isVisible);
        getFieldsText();
        clickOnAddTaskListButton();
        return fieldsText;
    }

    public HashMap<String, String> updateTaskList(final String listName, HashMap<String, String> values) {
        clickOnTaskListDropdownList(listName);
        clickOnEditTaskListLink(listName);
        setUpdateListNameTextBox(values.get("listName"));
        setUpdateDescriptionTextArea(values.get("listDescription"));
        checkUpdateVisibleToClientsCheckBox(Boolean.parseBoolean(values.get("isVisible")));
        getUpdatedFieldsText();
        clickOnUpdateTaskListButton();
        return fieldsText;
    }

    private void clickOnAcceptAlert() {
        webDriverWait.until(ExpectedConditions.alertIsPresent());
        Alert alert = webDriver.switchTo().alert();
        alert.accept();
    }

    public void removeTaskList(final String listName) {
        clickOnRemoveTaskListLink(listName);
        clickOnAcceptAlert();
    }

    public void removeTaskListSearched(final String listName) {
        mouseOverFoundLink(listName);
        clickOnRemoveListLink();
        clickOnAcceptAlert();
    }
}
