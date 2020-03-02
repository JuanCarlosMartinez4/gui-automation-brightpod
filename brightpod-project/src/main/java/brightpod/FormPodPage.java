package brightpod;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;

public class FormPodPage extends BasePage {

    private final String CALENDAR_DAY = "//td[contains(text(),'%s')]";

    private final String COLOR_ITEM = "//button[@data-value='%s']";

    private String CLIENT_COMBOBX = "//select[@id='companies']";

    private String PROJECT_LEAD_COMBOBOX = "//select[@name='project-lead']";

    private HashMap<String, String> values;

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

    @Override
    protected void waitUntilPageObjectIsLoaded() {
        webDriverWait.until(ExpectedConditions.visibilityOf(projectNameTextBox));
    }

    private void setProjectNameTextBox(final String podName) {
        if (podName == null)
            return;
        projectNameTextBox.clear();
        projectNameTextBox.sendKeys(podName);
    }

    private WebElement getCalendarDay(final String numberDay) {
        return webDriver.findElement(By.xpath(String.format(CALENDAR_DAY, numberDay)));
    }


    private void clickOnStartDateTextBox(final String numberDay) {
        if (numberDay == null)
            return;
        startDateTextBox.click();
        getCalendarDay(numberDay).click();
    }

    private void clickOnDueDateTextBox(final String numberDay) {
        if (numberDay == null)
            return;
        dueDateTextBox.click();
        getCalendarDay(numberDay).click();
    }

    private void setBudgetTimeTextBox(final String time) {
        if (time == null)
            return;
        budgetTimeTextBox.clear();
        budgetTimeTextBox.sendKeys(time);
    }

    private Select getClientComboBox() {
        return new Select(webDriver.findElement(By.xpath(CLIENT_COMBOBX)));
    }

    private void selectClientComboBox(final String clientName) {
        if (clientName == null)
            return;
        getClientComboBox().selectByVisibleText(clientName);
    }

    private Select getProjectLeadComboBox() {
        return new Select(webDriver.findElement(By.xpath(PROJECT_LEAD_COMBOBOX)));
    }

    private void selectProjectLeadComboBox(final String memberName) {
        if (memberName == null)
            return;
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

    private void setColorItem(final String colorCode) {
        if (colorCode == null)
            return;
        getColorItem(colorCode).click();
    }

    private void setDescriptionTextEditor(final String description) {
        if (description == null)
            return;
        descriptionTextEditor.clear();
        descriptionTextEditor.sendKeys(description);
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

    public HashMap<String, String> getValues() {
        values = new HashMap<>();
        values.put("projectName", projectNameTextBox.getAttribute("value"));
        values.put("startDate", startDateTextBox.getAttribute("value"));
        values.put("dueDate", dueDateTextBox.getAttribute("value"));
        values.put("budgetTime", budgetTimeTextBox.getAttribute("value"));
        values.put("client", getClientComboBox().getFirstSelectedOption().getText());
        values.put("projectLead", getProjectLeadComboBox().getFirstSelectedOption().getText());
        values.put("description", descriptionTextEditor.getText());
        return values;
    }

    private void setValuesToFields(HashMap<String, String> inputValues) {
        clickOnStartDateTextBox(inputValues.get("startDate"));
        clickOnDueDateTextBox(inputValues.get("dueDate"));
        setBudgetTimeTextBox(inputValues.get("budgetTime"));
        selectClientComboBox(inputValues.get("client"));
        selectProjectLeadComboBox(inputValues.get("projectLead"));

        setDescriptionTextEditor(inputValues.get("description"));
    }

    public TaskListPage createNewPod(HashMap<String, String> inputValues) {
        setProjectNameTextBox(inputValues.getOrDefault("projectName", " "));
        clickOnPodColorButton();
        setColorItem(inputValues.get("color"));
        setValuesToFields(inputValues);
        clickOnCreatePodAndInvitePeopleButton();
        return new TaskListPage();
    }

    public TaskListPage updatePod(HashMap<String, String> inputValues) {
        setProjectNameTextBox(inputValues.get("projectName"));
        clickOnSelectColorButton();
        setColorItem(inputValues.get("color"));
        setValuesToFields(inputValues);
        clickOnUpdatePodButton();
        return new TaskListPage();
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
