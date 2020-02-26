package brightpod;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;

public class NewPodModal extends BasePage {

    @FindBy(how = How.LINK_TEXT, using = "Create a New Pod")
    WebElement newPodButton;
    @FindBy(how = How.LINK_TEXT, using = "Create a Blank Pod")
    WebElement blankPodButton;
    @FindBy(id = "project-name")
    WebElement projectNameTextBox;
    @FindBy(how = How.XPATH, using = "//button[@class='btn btn-success btn-primary new-project-button']")
    WebElement createPodAndInvitePeopleButton;

    public NewPodModal() throws IOException {
    }

    @Override
    protected void waitUntilPageObjectIsLoaded() {
        webDriverWait.until(ExpectedConditions.visibilityOf(newPodButton));
    }

    public PodsPage createNewPod(String podName) throws IOException {
        clickNewPodButton();
        clickBlankPodButton();
        setProjectNameTextBox(podName);
        clickCreatePodAndInvitePeopleButton();
        return new PodsPage();
    }

    private void clickNewPodButton() {
        newPodButton.click();
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
}
