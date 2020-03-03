package brightpod;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.Map;

public class FormPodPage extends BasePage {

    private final String CALENDAR_DAY = "//td[contains(text(),'%s')]";

    private final String COLOR_ITEM = "//button[@data-value='%s']";

    private String CLIENT_COMBOBX = "//select[@id='companies']";

    private String PROJECT_LEAD_COMBOBOX = "//select[@name='project-lead']";

    private Map<String, String> podValues;

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

    private void clickOnMeIcon() {
        meIcon.click();
    }

//    private WebElement getUserNameWebElement(final String userName) {
//        return webDriver.findElement(By.xpath(String.format(USER_NAME, userName)));
//    }

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

    public Map<String, String> getPodInformation() {
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

    private void setPodInformation(final Map<String, String> podInformation) {
        clickOnStartDateTextBox(podInformation.get("startDate"));
        clickOnDueDateTextBox(podInformation.get("dueDate"));
        setBudgetTimeTextBox(podInformation.get("budgetTime"));
        selectClientComboBox(podInformation.get("client"));
        selectProjectLeadComboBox(podInformation.get("projectLead"));
        setDescriptionTextEditor(podInformation.get("description"));
    }

    public TaskListPage createNewPod(final Map<String, String> podInformation) {
        setProjectNameTextBox(podInformation.get("podName"));
        clickOnPodColorButton();
        setColorItem(podInformation.get("color"));
        setPodInformation(podInformation);
        clickOnCreatePodAndInvitePeopleButton();
        return new TaskListPage();
    }

    public TaskListPage updatePod(final Map<String, String> inputValues) {
        setProjectNameTextBox(inputValues.get("projectName"));
        clickOnSelectColorButton();
        setColorItem(inputValues.get("color"));
        setPodInformation(inputValues);
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
