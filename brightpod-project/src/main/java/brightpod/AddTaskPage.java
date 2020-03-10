package brightpod;

import brightpod.constants.TaskConstant;
import entities.Task;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.Set;

/**
 * Manages tasks
 */
public class AddTaskPage extends BasePage {
    private static final String ADD_NEW_TASK_BUTTON = "//span[text()='%s']//ancestor::li//a[contains(@class,'add_new_task')]";

    private static final String TASK_TEXTAREA = "//span[text()='%s']//ancestor::li//textarea[contains(@name,'task_name')]";

    private static final String COMBOBOX_SELECTOR = "//span[text()='%s']//ancestor::li//select[starts-with(@id,'task_assign_to_')]";

    private static final String HIGH_PRIORITY_CHECKBOX = "//span[text()='%s']//ancestor::li//label[@class='checkbox-inline']";

    private static final String ADD_TASK_BUTTON = "//span[text()='%s']//ancestor::li//input[contains(@class,'add-task-button')]";

    private static final String CREATED_TASK_NAME = "//span[text()='%s']//ancestor::li//a[contains(text(),'%s')]";

    private static final String TASK_NAME_LINK = "//a[contains(text(),'%s')]";

    private static final String MEMBER_COMBOBOX = "//div//form[@id='task_form']//select[starts-with(@id,'task_assign_to_')]";

    private static final String SET_YEAR = "//span[text()[. = '%s']]";

    private static final String SET_MONTH = "//span[text()[. = '%s']]";

    private static final String SET_DAY = "//td[text()[. = '%s']]";

    private static final int YEAR_VALUE = 2;

    private static final int MONTH_VALUE = 0;

    private static final int DAY_VALUE = 1;

    @FindBy(css = "a[class='btn btn-default btn-xs btn-success add_new_task']")
    private WebElement addTaskButton;

    @FindBy(xpath = "//div//form[@id='task_form']//textarea[starts-with(@id,'task_name_')]")
    private WebElement taskNameTextArea;

    @FindBy(xpath = "//div//form[@id='task_form']//input[starts-with(@id,'task_due_date_')]")
    private WebElement dueDateTextBox;

    @FindBy(xpath = "//div//form[@id='task_form']//input[starts-with(@id,'mark_as_important_')]")
    private WebElement highPriorityCheckbox;

    @FindBy(xpath = "//div[starts-with(@class, 'add-task-area')]//input[@class='btn btn-default btn-success add-task-button']")
    private WebElement addTaskButtonToSave;

    @FindBy(xpath = "//div[@class='datepicker-days']/table//th[@class='datepicker-switch']")
    private WebElement onDaysButton;

    @FindBy(xpath = "//div[@class='datepicker-months']/table//th[@class='datepicker-switch']")
    private WebElement onMonthsButton;

    @FindBy(xpath = "//div[@class='datepicker-years']/table//th[@class='datepicker-switch']")
    private WebElement onYearsButton;

    /**
     * Allows to waits until the page object is loaded
     */
    @Override
    protected void waitUntilPageObjectIsLoaded() {
        webDriverWait.until(ExpectedConditions.visibilityOf(addTaskButton));
    }

    /**
     * Gets a web element from xpath.
     * @param webElementPath xpath.
     * @param listName value.
     * @return webDriver webElement.
     */
    private WebElement getWebElement(final String webElementPath, final String listName) {
        return webDriver.findElement(By.xpath(String.format(webElementPath, listName)));
    }

    /**
     * Gets a combobox selector web element.
     * @param listName value.
     * @return selector web element.
     */
    private Select getCombobox(final String listName) {
        return new Select(webDriver.findElement(By.xpath(String.format(COMBOBOX_SELECTOR, listName))));
    }

    /**
     * Clicks on add new task button.
     * @param listName value.
     */
    private void clickOnAddNewTaskButton(final String listName) {
        getWebElement(ADD_NEW_TASK_BUTTON, listName).click();
    }

    /**
     * Sets task name.
     * @param listName value.
     * @param taskName value.
     */
    private void setTaskTextArea(final String listName, final String taskName) {
        getWebElement(TASK_TEXTAREA, listName).sendKeys(taskName);
    }

    /**
     * Sets member to task.
     * @param listName value.
     * @param memberName value
     */
    private void setMemberToTask(final String listName, final String memberName) {
        getCombobox(listName).selectByVisibleText(memberName);
    }

    /**
     * Sets high priority value.
     * @param listName value.
     */
    private void setHighPriorityCheckbox(final String listName) {
        getWebElement(HIGH_PRIORITY_CHECKBOX, listName).click();
    }

    /**
     * Clicks on add task button.
     * @param listName value.
     */
    private void clickOnAddTaskButton(final String listName) {
        getWebElement(ADD_TASK_BUTTON, listName).click();
    }

    /**
     * Clicks on task name link.
     * @param taskName value.
     * @return task popup instance.
     */
    public TaskPopup clickOnTaskNameLink(final String taskName) {
        getWebElement(TASK_NAME_LINK, taskName).click();
        return new TaskPopup();
    }

    /**
     * Clicks on add task button.
     */
    private void clickOnAddTaskButton() {
        addTaskButton.click();
    }

    /**
     * Sets on task name text area.
     * @param taskName value.
     */
    private void setTaskNameTextArea(final String taskName) {
        taskNameTextArea.clear();
        taskNameTextArea.sendKeys(taskName);
    }

    /**
     * Gets member selector web element.
     * @return selector web element.
     */
    private Select getMemberComboBox() {
        return new Select(webDriver.findElement(By.xpath(MEMBER_COMBOBOX)));
    }

    /**
     * Selects member on combobox.
     * @param memberName value.
     */
    private void selectMemberComboBox(final String memberName) {
        getMemberComboBox().selectByVisibleText(memberName);
    }

    /**
     * Splits date value.
     * @param date value.
     * @return date as a list.
     */
    private String[] splitDate(final String date) {
        String actualDate = date;
        String[] dateValues = actualDate.split("[ ]");
        String day = dateValues[DAY_VALUE];
        day = day.substring(0, day.length() - 1);

        if (day.startsWith("0"))
            day = day.substring(DAY_VALUE);

        dateValues[DAY_VALUE] = day;
        return dateValues;
    }

    /**
     * Gets data value.
     * @param webElementXPath value.
     * @param dateValue value.
     * @return web element value.
     */
    private WebElement getDate(final String webElementXPath, final String dateValue) {
        return webDriver.findElement(By.xpath(String.format(webElementXPath, dateValue)));
    }

    /**
     * Clicks on days button.
     */
    private void clickOnDaysButton() {
        onDaysButton.click();
    }

    /**
     * Clicks on month button.
     */
    private void clickOnMonthsButton() {
        onMonthsButton.click();
    }

    /**
     * Clicks on year button.
     */
    private void clickOnYearsButton() {
        onYearsButton.click();
    }

    /**
     * Clicks on calendar.
     */
    private void clickOnCalendarPickerButton() {
        clickOnDaysButton();
        clickOnMonthsButton();
        clickOnYearsButton();
    }

    /**
     * Sets calendar value.
     * @param webElementXPath value.
     * @param dateValue value.
     */
    private void setCalendarValues(final String webElementXPath, final String dateValue) {
        getDate(webElementXPath, dateValue).click();
    }

    /**
     * Clicks on due date.
     * @param date value.
     */
    private void clickOnDueDateTextBox(final String date) {
        dueDateTextBox.click();
        String[] splitDate = splitDate(date);
        clickOnCalendarPickerButton();

        setCalendarValues(SET_YEAR, splitDate[YEAR_VALUE]);
        setCalendarValues(SET_MONTH, splitDate[MONTH_VALUE]);
        setCalendarValues(SET_DAY, splitDate[DAY_VALUE]);
    }

    private void clickOnHighPriorityCheckBox(boolean isVisible) {
        if (isVisible)
            highPriorityCheckbox.click();
    }

    public void clickOnAddTaskButtonToSave() {
        webDriverWait.until(ExpectedConditions.visibilityOf(addTaskButtonToSave));
        addTaskButtonToSave.click();
    }

    public void addTaskInformation(final Task task, final Set<String> fields) {
        clickOnAddTaskButton();
        HashMap<String, Runnable> strategyMap = composeStrategyMap(task);
        fields.forEach(field -> strategyMap.get(field).run());
    }

    private HashMap<String, Runnable> composeStrategyMap(final Task task) {
        HashMap<String, Runnable> strategyMap = new HashMap<>();

        strategyMap.put(TaskConstant.TASK_NAME, () -> setTaskNameTextArea(task.getTaskName()));
        strategyMap.put(TaskConstant.MEMBER, () -> selectMemberComboBox(task.getMember()));
        strategyMap.put(TaskConstant.DUE_DATE, () -> clickOnDueDateTextBox(task.getDueDate()));
        strategyMap.put(TaskConstant.HIGH_PRIORITY, () -> clickOnHighPriorityCheckBox(task.isHighPriority()));
        return strategyMap;
    }
}
