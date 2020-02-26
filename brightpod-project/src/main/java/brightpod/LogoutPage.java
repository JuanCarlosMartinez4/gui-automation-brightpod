package brightpod;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LogoutPage extends BasePage {

    @FindBy(xpath = "//*[contains(text(),'You have been logged out.')]")
    WebElement loggedOutText;

    @FindBy(how=How.LINK_TEXT, using="Or, get back to the login page...")
    WebElement returnInitPageLinkText;

    @Override
    protected void waitUntilPageObjectIsLoaded() {
        webDriverWait.until(ExpectedConditions.visibilityOf(loggedOutText));
    }

    private void clickOnReturnInitPageLinkText() {
        returnInitPageLinkText.click();
    }

    public void logout() {
        clickOnReturnInitPageLinkText();
//        return new LoginPage();
    }

    public LogoutPage logoutWithErrors() {
        clickOnReturnInitPageLinkText();
        return this;
    }
}
