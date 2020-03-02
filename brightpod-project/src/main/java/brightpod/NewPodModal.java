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

    @FindBy(xpath = "//div[contains(@class,'col-lg-9 col-md-9')]")
    WebElement projectName;

    @FindBy(linkText = "Create a New Pod")
    WebElement newPodButton;

    @Override
    protected void waitUntilPageObjectIsLoaded() {
        webDriverWait.until(ExpectedConditions.visibilityOf(modalLabel));
    }

    private void clickBlankPodButton() {
        blankPodButton.click();
    }

    private String getProjectName() {
        return projectName.getText();
    }

    public String createNewPod() {
        clickBlankPodButton();
        return  getProjectName();
    }
}
