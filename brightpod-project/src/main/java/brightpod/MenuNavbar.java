package brightpod;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MenuNavbar extends BasePage {

    @FindBy(xpath = "//a[@title='Logout']")
    private WebElement logoutButton;

    @Override
    protected void waitUntilPageObjectIsLoaded() {
        webDriverWait.until(ExpectedConditions.visibilityOf(logoutButton));
    }

    private void clickOnLogoutButton() {
        logoutButton.click();
    }

    public LogoutPage logout() {
        clickOnLogoutButton();
        return new LogoutPage();
    }
}
