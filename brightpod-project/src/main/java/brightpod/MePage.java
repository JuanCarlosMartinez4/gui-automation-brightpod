package brightpod;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MePage extends BasePage {

    private static final String USER_NAME = "//h3[contains(text(),'%s')]";

    private static final String USER_EMAIL = "//a[contains(text(),'%s')]";

    @FindBy(css = "a[title='Me']")
    private WebElement meTab;

    private void clickOnMeTab() {
        meTab.click();
    }

    @Override
    protected void waitUntilPageObjectIsLoaded() {
        webDriverWait.until(ExpectedConditions.visibilityOf(meTab));
    }

    private WebElement getWebElement(final String locator, final String name) {
        return webDriver.findElement(By.xpath(String.format(locator, name)));
    }

    private String matchUserName(final String userName) {
        return getWebElement(USER_NAME, userName).getText();
    }

    private String matchUserEmail(final String userEmail) {
        return getWebElement(USER_EMAIL, userEmail).getText();
    }

    public String verifyUserLogged(final String userName, final String userEmail) {
        clickOnMeTab();
        matchUserName(userName);
        return matchUserEmail(userEmail);
    }
}
