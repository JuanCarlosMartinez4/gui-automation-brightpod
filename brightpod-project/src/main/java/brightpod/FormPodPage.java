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

    private HashMap<String, String> texts;

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

    private HashMap<String, String> getTextOfFields() {
        texts = new HashMap<>();
        texts.put("projectName", projectNameTextBox.getAttribute("value"));
        texts.put("startDate", startDateTextBox.getAttribute("value"));
        texts.put("dueDate", dueDateTextBox.getAttribute("value"));
        texts.put("budgetTime", budgetTimeTextBox.getAttribute("value"));
        texts.put("client", getClientComboBox().getFirstSelectedOption().getText());
        texts.put("projectLead", getProjectLeadComboBox().getFirstSelectedOption().getText());
        texts.put("description", descriptionTextEditor.getText());
        return texts;
    }

    public HashMap<String, String> createNewPod(HashMap<String, String> values) {
        setProjectNameTextBox(values.get("projectName"));
        clickOnStartDateTextBox(values.get("startDate"));
        clickOnDueDateTextBox(values.get("dueDate"));
        setBudgetTimeTextBox(values.get("budgetTime"));
        selectClientComboBox(values.get("client"));
        selectProjectLeadComboBox(values.get("projectLead"));
        clickOnPodColorButton();
        setColorItem(values.get("color"));
        setDescriptionTextEditor(values.get("description"));
        getTextOfFields();
        clickOnCreatePodAndInvitePeopleButton();
        return texts;
    }

    public HashMap<String, String> updatePod(HashMap<String, String> values) {
        setProjectNameTextBox(values.getOrDefault("projectName", " "));
        clickOnStartDateTextBox(values.get("startDate"));
        clickOnDueDateTextBox(values.get("dueDate"));
        setBudgetTimeTextBox(values.get("budgetTime"));
        selectClientComboBox(values.get("client"));
        selectProjectLeadComboBox(values.get("projectLead"));
        clickOnSelectColorButton();
        setColorItem(values.get("color"));
        setDescriptionTextEditor(values.get("description"));
        getTextOfFields();
        clickOnUpdatePodButton();
        return texts;
    }

    public String goBackToPodsPage() {
        clickOnGoBackToPodsButton();
        return goBackToPodsButton.getText();
    }
}
