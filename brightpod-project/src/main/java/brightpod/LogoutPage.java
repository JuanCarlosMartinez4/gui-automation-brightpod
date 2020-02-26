package brightpod;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;

public class LogoutPage extends BasePage {

    @FindBy(how= How.XPATH, using="//a[@title='Logout']")
    WebElement logoutButton;
    @FindBy(how=How.LINK_TEXT, using="Or, get back to the login page...")
    WebElement returnInitPageLinkText;

    public LogoutPage() throws IOException {
    }

    @Override
    protected void waitUntilPageObjectIsLoaded() {
        webDriverWait.until(ExpectedConditions.visibilityOf(logoutButton));
    }

    public LoginPage logout() throws IOException {
        clickOnLogoutButton();
        clickOnInitPageLinkText();
        return new LoginPage();
    }

    public LogoutPage logoutWithErrors() {
        clickOnLogoutButton();
        clickOnInitPageLinkText();
        return this;
    }

    private void clickOnLogoutButton() {
        logoutButton.click();
    }

    private void clickOnInitPageLinkText() {
        returnInitPageLinkText.click();
    }
}
