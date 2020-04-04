package brightpod;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LogoutPage extends BasePage {

    @FindBy(xpath = "//*[contains(text(),'You have been logged out.')]")
    private WebElement loggedOutText;

    @FindBy(linkText = "Or, get back to the login page...")
    private WebElement returnInitPageLinkText;

    @Override
    protected void waitUntilPageObjectIsLoaded() {
        webDriverWait.until(ExpectedConditions.visibilityOf(loggedOutText));
    }

    private void clickOnReturnInitPageLinkText() {
        returnInitPageLinkText.click();
    }

    public void returnInitPage() {
        clickOnReturnInitPageLinkText();
    }

    public LogoutPage logoutWithErrors() {
        clickOnReturnInitPageLinkText();
        return this;
    }
}
