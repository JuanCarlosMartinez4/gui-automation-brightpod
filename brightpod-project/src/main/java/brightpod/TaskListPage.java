package brightpod;

import entities.TaskList;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class TaskListPage extends BasePage {

    private static final String TASK_LIST_NAME = "//span[starts-with(@id,'list-name-')][text()='%s']";

    private static final String TASK_LIST_DROPDOWN_LIST = "//span[text()='%s']//ancestor::li//a[@id='dropdownMenuLink']";

    private static final String EDIT_TASK_LIST = "//span[text()='%s']//ancestor::li//a[@class='dropdown-item edit_tasklist tasklist_link']";

    private static final String REMOVE_TASK_LIST = "//span[text()='%s']//ancestor::li//a[@class='dropdown-item delete_tasklist tasklist_link']";

    private static final String LIST_FOUND_LINK = "//span[text()='%s']";

    private static final String TASK_LIST_DESCRIPTION = "//div[contains(text(),'%s')]";

    private HashMap<String, String> taskListInformation;

    private static final String NAME = "Name";
    private static final String DESCRIPTION = "Description";
    private static final String IS_VISIBLE = "Is Visible";

    @FindBy(css = "input[type='button'][value='New Task List']")
    private WebElement newTaskListButton;

    @FindBy(id = "task_list_name")
    private WebElement listNameTextBox;

    @FindBy(id = "description")
    private WebElement descriptionTextArea;

    @FindBy(id = "client_access")
    private WebElement visibleToClientCheckBox;

    @FindBy(css = "input[type='submit'][value='Add Task List']")
    private WebElement addTaskListButton;

    @FindBy(css = "input[name='task_list_name'][id^='task_list_name_']")
    private WebElement updateListNameTextBox;

    @FindBy(css = "textarea[name='description'][id^='tasklist_description_']")
    private WebElement updateDescriptionTextArea;

    @FindBy(css = "input[id^='client_access_']")
    private WebElement updateVisibleToClientCheckBox;

    @FindBy(css = "input[id='update_tasklist_button']")
    private WebElement updateTaskListButton;

    @FindBy(xpath = "//a[@class='delete_tasklist tasklist_link_list'][text()='Remove']")
    private WebElement removeListLink;

    @FindBy(xpath = "//a[@class='edit_tasklist tasklist_link_list'][text()='Edit']")
    private WebElement editListLink;

    @FindBy(css = "li[id='pods_tab']")
    private WebElement podsTabIcon;

    @FindBy(css = "span[class='list-name']")
    private WebElement listName;

    @FindBy(css = "div[class='tasklist-desc']")
    private WebElement listDescription;

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

    private void checkVisibleToClientsCheckBox(final boolean isVisible) {
        if (isVisible)
            visibleToClientCheckBox.click();
    }

    private void clickOnAddTaskListButton() {
        addTaskListButton.click();
    }

    private WebElement getTaskListNameByName(final String listName) {
        return webDriver.findElement(By.xpath(String.format(TASK_LIST_NAME, listName)));
    }

    private String getTaskListNameText(final String listName) {
        return getTaskListNameByName(listName).getText();
    }

    private WebElement getTaskListDescription(final String description) {
        return webDriver.findElement(By.xpath(String.format(TASK_LIST_DESCRIPTION, description)));
    }

    private String getTaskListDescriptionText(final String description) {
        return getTaskListDescription(description).getText();
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
//        if (listName == null)
//            return;
        updateListNameTextBox.clear();
        updateListNameTextBox.sendKeys(listName);
    }

    private void setUpdateDescriptionTextArea(final String description) {
//        if (description == null)
//            return;
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

    private void clickOnEditListLink() {
        editListLink.click();
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

    private String getListName() {
        return listName.getText();
    }

    private String getListDescription() {
        return listDescription.getText();
    }

    public HashMap<String, String> getCreatedTaskListInformation(final HashMap<String, String> listInformation) {
        taskListInformation = new HashMap<>();
        HashMap<String, Supplier> strategyMapGet = composeStrategyMapGet(listInformation);

        for (String field : listInformation.keySet()) {
            taskListInformation.put(field, strategyMapGet.get(field).get().toString());
        }
        return taskListInformation;
    }

    private HashMap<String, Supplier> composeStrategyMapGet(final HashMap<String, String>listInformation) {
        HashMap<String, Supplier> strategyMapGet = new HashMap<>();
        strategyMapGet.put(NAME, () -> getTaskListNameText(listInformation.get(NAME)));
        strategyMapGet.put(DESCRIPTION, () -> getTaskListDescriptionText(listInformation.get(DESCRIPTION)));
        strategyMapGet.put(IS_VISIBLE, () -> listInformation.get(IS_VISIBLE));
        return strategyMapGet;
    }

    public Map<String, String> getUpdatedTaskListInformation() {
        taskListInformation = new HashMap<>();
        taskListInformation.put(NAME, getListName());
        taskListInformation.put(DESCRIPTION, getListDescription());
        return taskListInformation;
    }

    public TaskListPage addTaskListInformation(final TaskList taskList, final Set<String> fields) {
        clickOnNewTaskListButton();
        HashMap<String, Runnable> strategyMap = composeStrategyMap(taskList);
        fields.forEach(field -> strategyMap.get(field).run());
        clickOnAddTaskListButton();
        return new TaskListPage();
    }

    private HashMap<String, Runnable> composeStrategyMap(TaskList taskList) {
        HashMap<String, Runnable> strategyMap = new HashMap<>();

        strategyMap.put(NAME, () -> setListNameTextBox(taskList.getName()));
        strategyMap.put(DESCRIPTION, () -> setDescriptionTextArea(taskList.getDescription()));
        strategyMap.put(IS_VISIBLE, () -> checkVisibleToClientsCheckBox(taskList.isVisibleToClients()));
        return strategyMap;
    }

    public AddTaskPage updateTaskListInformation(final TaskList taskList, final Set<String> fields) {
        HashMap<String, Runnable> strategyMapUpdate = composeStrategyMapUpdate(taskList);
        fields.forEach(field -> strategyMapUpdate.get(field).run());
        clickOnUpdateTaskListButton();
        return new AddTaskPage();
    }

    private HashMap<String, Runnable> composeStrategyMapUpdate(TaskList taskList) {
        HashMap<String, Runnable> strategyMap = new HashMap<>();

        strategyMap.put(NAME, () -> setUpdateListNameTextBox(taskList.getName()));
        strategyMap.put(DESCRIPTION, () -> setUpdateDescriptionTextArea(taskList.getDescription()));
        strategyMap.put(IS_VISIBLE, () -> checkUpdateVisibleToClientsCheckBox(taskList.isVisibleToClients()));
        return strategyMap;
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

    public void editTaskListSearched(final String listName) {
        mouseOverFoundLink(listName);
        clickOnEditListLink();
    }
}
