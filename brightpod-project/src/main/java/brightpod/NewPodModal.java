package brightpod;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewPodModal extends BasePage {

    @FindBy(id = "myModalLabel")
    WebElement modalLabel;

    @FindBy(how = How.LINK_TEXT, using = "Create a Blank Pod")
    WebElement blankPodButton;

    @FindBy(id = "project-name")
    WebElement projectNameTextBox;

    @FindBy(how = How.XPATH, using = "//button[@class='btn btn-success btn-primary new-project-button']")
    WebElement createPodAndInvitePeopleButton;

    @FindBy(css = "a[title='Go back to Pods']")
    WebElement goBackToPodsButton;

    @FindBy(xpath = "//div[contains(@class,'col-lg-9 col-md-9')]")
    WebElement projectName;

    @Override
    protected void waitUntilPageObjectIsLoaded() {
        webDriverWait.until(ExpectedConditions.visibilityOf(modalLabel));
    }

    private void clickBlankPodButton() {
        blankPodButton.click();
    }

    private void setProjectNameTextBox(final String podName) {
        this.projectNameTextBox.sendKeys(podName);
    }

    private void clickCreatePodAndInvitePeopleButton() {
        createPodAndInvitePeopleButton.click();
    }

    private String getProjectName() {
        return projectName.getText();
    }

    public String createNewPod(String podName) {
        clickBlankPodButton();//new_podPage
        setProjectNameTextBox(podName);
        clickCreatePodAndInvitePeopleButton();
        return  getProjectName();
//        return new TaskListPage();
    }

    private void clickOnGoBackToPodsButton() {
        goBackToPodsButton.click();
    }

    public PodsPage goBackToPodsPage() {
        clickBlankPodButton();
        clickOnGoBackToPodsButton();
        return new PodsPage();
    }
}
