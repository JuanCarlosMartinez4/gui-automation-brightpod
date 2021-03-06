package brightpod;

import brightpod.constants.PodConstant;
import entities.Pod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.Set;
import java.util.function.Supplier;

public class FormPodPage extends BasePage {

    private static final String COLOR_ITEM = "//button[@data-value='%s']";

    private static final String CLIENT_COMBOBOX = "//select[@id='companies']";

    private static final String PROJECT_LEAD_COMBOBOX = "//select[@name='project-lead']";

    private static final String SET_YEAR = "//span[text()[. = '%s']]";

    private static final String SET_MONTH = "//span[text()[. = '%s']]";

    private static final String SET_DAY = "//td[text()[. = '%s']]";

    private static final int YEAR_VALUE = 2;

    private static final int MONTH_VALUE = 0;

    private static final int DAY_VALUE = 1;

    private HashMap<String, String> podValues;

    private String projectLead;

    @FindBy(id = "project-name")
    private WebElement projectNameTextBox;

    @FindBy(id = "start_date_project")
    private WebElement startDateTextBox;

    @FindBy(id = "end_date_project")
    private WebElement dueDateTextBox;

    @FindBy(id = "budgeted_hours")
    private WebElement budgetTimeTextBox;

    @FindBy(xpath = "//a[text()='Change color']")
    private WebElement podColorButton;

    @FindBy(id = "trix_editor_pod_desc")
    private WebElement descriptionTextEditor;

    @FindBy(css = "button[class='btn btn-success btn-primary new-project-button']")
    private WebElement createPodAndInvitePeopleButton;

    @FindBy(css = "button[class='btn btn-primary new-project-button']")
    private WebElement updatePodButton;

    @FindBy(css = "a[title='Go back to Pods']")
    private WebElement goBackToPodsButton;

    @FindBy(xpath = "//a[text()='Select color']")
    private WebElement selectColorButton;

    @FindBy(css = "div[class='col-lg-9 col-md-9']")
    private WebElement podName;

    @FindBy(css = "button[class='btn btn-default']")
    private WebElement goBackToTheDashboard;

    @FindBy(css = "i[class='fa fa-user']")
    private WebElement meIcon;

    @FindBy(xpath = "//div[@class='col-lg-10 col-md-10']//h3")
    private WebElement userName;

    @FindBy(xpath = "//div[@class='datepicker-days']/table//th[@class='datepicker-switch']")
    private WebElement onDaysButton;

    @FindBy(xpath = "//div[@class='datepicker-months']/table//th[@class='datepicker-switch']")
    private WebElement onMonthsButton;

    @FindBy(xpath = "//div[@class='datepicker-years']/table//th[@class='datepicker-switch']")
    private WebElement onYearsButton;

    @Override
    protected void waitUntilPageObjectIsLoaded() {
        webDriverWait.until(ExpectedConditions.visibilityOf(projectNameTextBox));
    }

    private void setProjectNameTextBox(final String podName) {
        projectNameTextBox.clear();
        projectNameTextBox.sendKeys(podName);
    }

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

    private WebElement getDate(final String webElementXPath, final String dateValue) {
        return webDriver.findElement(By.xpath(String.format(webElementXPath, dateValue)));
    }

    private void clickOnDaysButton() {
        onDaysButton.click();
    }

    private void clickOnMonthsButton() {
        onMonthsButton.click();
    }

    private void clickOnYearsButton() {
        onYearsButton.click();
    }

    private void clickOnCalendarPickerButton() {
        clickOnDaysButton();
        clickOnMonthsButton();
        clickOnYearsButton();
    }

    private void setCalendarValues(final String webElementXPath, final String dateValue) {
        getDate(webElementXPath, dateValue).click();
    }

    private void clickOnStartDateTextBox(final String date) {
        startDateTextBox.click();
        String[] splitDate = splitDate(date);
        clickOnCalendarPickerButton();
        setCalendarValues(SET_YEAR, splitDate[YEAR_VALUE]);
        setCalendarValues(SET_MONTH, splitDate[MONTH_VALUE]);
        setCalendarValues(SET_DAY, splitDate[DAY_VALUE]);
    }

    private void clickOnDueDateTextBox(final String date) {
        dueDateTextBox.click();
        String[] splitDate = splitDate(date);
        clickOnCalendarPickerButton();

        setCalendarValues(SET_YEAR, splitDate[YEAR_VALUE]);
        setCalendarValues(SET_MONTH, splitDate[MONTH_VALUE]);
        setCalendarValues(SET_DAY, splitDate[DAY_VALUE]);
    }

    private void setBudgetTimeTextBox(final String time) {
        budgetTimeTextBox.clear();
        budgetTimeTextBox.sendKeys(time);
    }

    private Select getSelect(final String xpathLocator) {
        return new Select(webDriver.findElement(By.xpath(xpathLocator)));
    }

    private void selectClientComboBox(final String clientName) {
        getSelect(CLIENT_COMBOBOX).selectByVisibleText(clientName);
    }

    private void selectProjectLeadComboBox(final String memberName) {
        getSelect(PROJECT_LEAD_COMBOBOX).selectByVisibleText(memberName);
    }

    private void clickOnPodColorButton() {
        podColorButton.click();
    }

    private void clickOnSelectColorButton() {
        selectColorButton.click();
    }

    private WebElement getColorItem(final String colorCode) {
        return webDriver.findElement(By.xpath(String.format(COLOR_ITEM, colorCode)));
    }

    private void setColorItemAdd(final String colorCode) {
        clickOnPodColorButton();
        getColorItem(colorCode).click();
    }

    private void setColorItemUpdate(final String colorCode) {
        clickOnSelectColorButton();
        getColorItem(colorCode).click();
    }

    private void setDescriptionTextEditor(final String description) {
        descriptionTextEditor.clear();
        descriptionTextEditor.sendKeys(description);
    }

    private void clickOnMeIcon() {
        meIcon.click();
    }

    private String getUserName() {
        String user = userName.getText();
        user = user.replaceAll("[A-Z]", "");
        return user.substring(0, user.length() - 1);
    }

    private void clickOnCreatePodAndInvitePeopleButton() {
        createPodAndInvitePeopleButton.click();
    }

    private void clickOnUpdatePodButton() {
        updatePodButton.click();
    }

    private void clickOnGoBackToPodsButton() {
        goBackToPodsButton.click();
    }

    public HashMap<String, String> getPodInformation(final HashMap<String, String> podInformation) {
        podValues = new HashMap<>();
        projectLead = podInformation.get(PodConstant.POD_LEAD);
        podInformation.remove(PodConstant.POD_LEAD);
        HashMap<String, Supplier> strategyMapGet = composeStrategyMapGet();

        for (String field : podInformation.keySet()) {
            podValues.put(field, strategyMapGet.get(field).get().toString());
        }
        if (projectLead != null) {
            HashMap<String, Supplier> strategyMapGetPodLead = composeStrategyMapGetPodLead();
            podValues.put(PodConstant.POD_LEAD, strategyMapGetPodLead.get(PodConstant.POD_LEAD).get().toString());
        }
        return podValues;
    }

    private HashMap<String, Supplier> composeStrategyMapGetPodLead() {
        HashMap<String, Supplier> strategyMapGet = new HashMap<>();
        clickOnMeIcon();
        strategyMapGet.put(PodConstant.POD_LEAD, () -> getUserName());
        return strategyMapGet;
    }

    private HashMap<String, Supplier> composeStrategyMapGet() {
        HashMap<String, Supplier> strategyMapGet = new HashMap<>();
        strategyMapGet.put(PodConstant.POD_NAME, () -> projectNameTextBox.getAttribute(PodConstant.VALUE));
        strategyMapGet.put(PodConstant.START_DATE, () -> startDateTextBox.getAttribute(PodConstant.VALUE));
        strategyMapGet.put(PodConstant.DUE_DATE, () -> dueDateTextBox.getAttribute(PodConstant.VALUE));
        strategyMapGet.put(PodConstant.BUDGET_TIME, () -> budgetTimeTextBox.getAttribute(PodConstant.VALUE));
        strategyMapGet.put(PodConstant.CLIENT, () -> getSelect(CLIENT_COMBOBOX).getFirstSelectedOption().getText());
        strategyMapGet.put(PodConstant.DESCRIPTION, () -> descriptionTextEditor.getText());
        return strategyMapGet;
    }

    public TaskListPage createNewPod(final Pod pod, final Set<String> fields) {
        HashMap<String, Runnable> strategyMap = composeStrategyMap(pod);
        fields.forEach(field -> strategyMap.get(field).run());
        clickOnCreatePodAndInvitePeopleButton();
        return new TaskListPage();
    }

    private HashMap<String, Runnable> composeStrategyMap(Pod pod) {
        HashMap<String, Runnable> strategyMap = new HashMap<>();

        strategyMap.put(PodConstant.POD_NAME, () -> setProjectNameTextBox(pod.getPodName()));
        strategyMap.put(PodConstant.START_DATE, () -> clickOnStartDateTextBox(pod.getStartDate()));
        strategyMap.put(PodConstant.DUE_DATE, () -> clickOnDueDateTextBox(pod.getDueDate()));
        strategyMap.put(PodConstant.BUDGET_TIME, () -> setBudgetTimeTextBox(pod.getBudgetedTime()));
        strategyMap.put(PodConstant.CLIENT, () -> selectClientComboBox(pod.getClient()));
        strategyMap.put(PodConstant.POD_LEAD, () -> selectProjectLeadComboBox(pod.getPodLead()));
        strategyMap.put(PodConstant.POD_COLOR, () -> setColorItemAdd(pod.getPodColor()));
        strategyMap.put(PodConstant.DESCRIPTION, () -> setDescriptionTextEditor(pod.getDescription()));
        return strategyMap;
    }

    public TaskListPage updatePod(final Pod pod, final Set<String> fields) {
        HashMap<String, Runnable> strategyMapUpdate = composeStrategyMapUpdate(pod);
        fields.forEach(field -> strategyMapUpdate.get(field).run());
        clickOnUpdatePodButton();
        return new TaskListPage();
    }

    private HashMap<String, Runnable> composeStrategyMapUpdate(Pod pod) {
        HashMap<String, Runnable> strategyMapUpdate = new HashMap<>();

        strategyMapUpdate.put(PodConstant.POD_NAME, () -> setProjectNameTextBox(pod.getPodName()));
        strategyMapUpdate.put(PodConstant.START_DATE, () -> clickOnStartDateTextBox(pod.getStartDate()));
        strategyMapUpdate.put(PodConstant.DUE_DATE, () -> clickOnDueDateTextBox(pod.getDueDate()));
        strategyMapUpdate.put(PodConstant.BUDGET_TIME, () -> setBudgetTimeTextBox(pod.getBudgetedTime()));
        strategyMapUpdate.put(PodConstant.CLIENT, () -> selectClientComboBox(pod.getClient()));
        strategyMapUpdate.put(PodConstant.POD_LEAD, () -> selectProjectLeadComboBox(pod.getPodLead()));
        strategyMapUpdate.put(PodConstant.POD_COLOR, () -> setColorItemUpdate(pod.getPodColor()));
        strategyMapUpdate.put(PodConstant.DESCRIPTION, () -> setDescriptionTextEditor(pod.getDescription()));
        return strategyMapUpdate;
    }

    private void clickOnGoBackToTheDashboardButton() {
        goBackToTheDashboard.click();
    }

    public TaskListPage goBackToTheDashboardButton() {
        clickOnGoBackToTheDashboardButton();
        return new TaskListPage();
    }

    public PodsPage goBackToPodsPage() {
        clickOnGoBackToPodsButton();
        return new PodsPage();
    }
}
