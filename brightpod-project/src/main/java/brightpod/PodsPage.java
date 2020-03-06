package brightpod;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PodsPage extends BasePage {

    @FindBy(linkText = "Create a New Pod")
    WebElement createANewPodButton;

    @Override
    protected void waitUntilPageObjectIsLoaded() {
        webDriverWait.until(ExpectedConditions.visibilityOf(createANewPodButton));
    }

    private void clickOnCreateANewPodButton() {
        createANewPodButton.click();
    }

    public NewPodModal clickNewPodButton() {
        clickOnCreateANewPodButton();
        return new NewPodModal();
    }
}
