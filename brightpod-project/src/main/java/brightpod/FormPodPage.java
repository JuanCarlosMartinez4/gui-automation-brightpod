package brightpod;

import entities.Pod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.Set;

public class FormPodPage extends BasePage {

    private final String CALENDAR_DAY = "//td[contains(text(),'%s')]";

    private final String COLOR_ITEM = "//button[@data-value='%s']";

    private String CLIENT_COMBOBX = "//select[@id='companies']";

    private String PROJECT_LEAD_COMBOBOX = "//select[@name='project-lead']";

    private HashMap<String, String> podValues;

    @FindBy(id = "project-name")
    WebElement projectNameTextBox;

    @FindBy(id = "start_date_project")
    WebElement startDateTextBox;

    @FindBy(id = "end_date_project")
    WebElement dueDateTextBox;

    @FindBy(id = "budgeted_hours")
    WebElement budgetTimeTextBox;

    @FindBy(xpath = "//a[text()='Change color']")
    WebElement podColorButton;

    @FindBy(id = "trix_editor_pod_desc")
    WebElement descriptionTextEditor;

    @FindBy(css = "button[class='btn btn-success btn-primary new-project-button']")
    WebElement createPodAndInvitePeopleButton;

    @FindBy(css = "button[class='btn btn-primary new-project-button']")
    WebElement updatePodButton;

    @FindBy(css = "a[title='Go back to Pods']")
    WebElement goBackToPodsButton;

    @FindBy(xpath = "//a[text()='Select color']")
    WebElement selectColorButton;

    @FindBy(css = "div[class='col-lg-9 col-md-9']")
    WebElement podName;

    @FindBy(css = "button[class='btn btn-default']")
    WebElement goBackToTheDashboard;

    @FindBy(css = "i[class='fa fa-user']")
    WebElement meIcon;

    @FindBy(xpath = "//div[@class='col-lg-10 col-md-10']//h3")
    WebElement userName;

    @Override
    protected void waitUntilPageObjectIsLoaded() {
        webDriverWait.until(ExpectedConditions.visibilityOf(projectNameTextBox));
    }

    private void setProjectNameTextBox(final String podName) {
        projectNameTextBox.clear();
        projectNameTextBox.sendKeys(podName);
    }

    private WebElement getCalendarDay(final String numberDay) {
        return webDriver.findElement(By.xpath(String.format(CALENDAR_DAY, numberDay)));
    }


    private void clickOnStartDateTextBox(final String numberDay) {
        startDateTextBox.click();
        getCalendarDay(numberDay).click();
    }

    private void clickOnDueDateTextBox(final String numberDay) {
        dueDateTextBox.click();
        getCalendarDay(numberDay).click();
    }

    private void setBudgetTimeTextBox(final String time) {
        budgetTimeTextBox.clear();
        budgetTimeTextBox.sendKeys(time);
    }

    private Select getClientComboBox() {
        return new Select(webDriver.findElement(By.xpath(CLIENT_COMBOBX)));
    }

    private void selectClientComboBox(final String clientName) {
        getClientComboBox().selectByVisibleText(clientName);
    }

    private Select getProjectLeadComboBox() {
        return new Select(webDriver.findElement(By.xpath(PROJECT_LEAD_COMBOBOX)));
    }

    private void selectProjectLeadComboBox(final String memberName) {
        getProjectLeadComboBox().selectByVisibleText(memberName);
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

    public HashMap<String, String> getPodInformation() {
        podValues = new HashMap<>();
        podValues.put("podName", projectNameTextBox.getAttribute("value"));
        podValues.put("startDate", startDateTextBox.getAttribute("value"));
        podValues.put("dueDate", dueDateTextBox.getAttribute("value"));
        podValues.put("budgetTime", budgetTimeTextBox.getAttribute("value"));
        podValues.put("client", getClientComboBox().getFirstSelectedOption().getText());
        podValues.put("description", descriptionTextEditor.getText());
        clickOnMeIcon();
        podValues.put("projectLead", getUserName());
        return podValues;
    }

    public TaskListPage createNewPod(final Pod pod, final Set<String> fields) {
        HashMap<String, Runnable> strategyMap = composeStrategyMap(pod);
        fields.forEach(field -> strategyMap.get(field).run());
        clickOnCreatePodAndInvitePeopleButton();
        return new TaskListPage();
    }

    private HashMap<String, Runnable> composeStrategyMap(Pod pod) {
        HashMap<String, Runnable> strategyMap = new HashMap<>();

        strategyMap.put("Pod Name", () -> setProjectNameTextBox(pod.getPodName()));
        strategyMap.put("Start Date", () ->  clickOnStartDateTextBox(pod.getStartDate()));
        strategyMap.put("Due Date", () -> clickOnDueDateTextBox(pod.getDueDate()));
        strategyMap.put("Budget Time", () -> setBudgetTimeTextBox(pod.getBudgetedTime()));
        strategyMap.put("Client", () -> selectClientComboBox(pod.getClient()));
        strategyMap.put("Project Lead", () -> selectProjectLeadComboBox(pod.getPodLead()));
        strategyMap.put("Color", () -> setColorItemAdd(pod.getPodColor()));
        strategyMap.put("Description", () -> setDescriptionTextEditor(pod.getDescription()));
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

        strategyMapUpdate.put("Pod Name", () -> setProjectNameTextBox(pod.getPodName()));
        strategyMapUpdate.put("Start Date", () ->  clickOnStartDateTextBox(pod.getStartDate()));
        strategyMapUpdate.put("Due Date", () -> clickOnDueDateTextBox(pod.getDueDate()));
        strategyMapUpdate.put("Budget Time", () -> setBudgetTimeTextBox(pod.getBudgetedTime()));
        strategyMapUpdate.put("Client", () -> selectClientComboBox(pod.getClient()));
        strategyMapUpdate.put("Project Lead", () -> selectProjectLeadComboBox(pod.getPodLead()));
        strategyMapUpdate.put("Color", () -> setColorItemUpdate(pod.getPodColor()));
        strategyMapUpdate.put("Description", () -> setDescriptionTextEditor(pod.getDescription()));
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
