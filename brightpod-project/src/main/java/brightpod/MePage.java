package brightpod;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MePage extends BasePage {

    private final String USER_NAME = "//h3[contains(text(),'%s')]";

    private final String USER_EMAIL = "//a[contains(text(),'%s')]";

    @FindBy(css = "a[title='Me']")
    WebElement meTab;

    private void clickOnMeTab() {
        meTab.click();
    }

    @Override
    protected void waitUntilPageObjectIsLoaded() {
        webDriverWait.until(ExpectedConditions.visibilityOf(meTab));
    }

    private WebElement getUserName(final String userName) {
        return webDriver.findElement(By.xpath(String.format(USER_NAME, userName)));
    }

    private String matchUserName(final String userName) {
        return getUserName(userName).getText();
    }

    private WebElement getUserEmail(final String userEmail) {
        return  webDriver.findElement(By.xpath(String.format(USER_EMAIL, userEmail)));
    }

    private String matchUserEmail(final String userEmail) {
        return getUserEmail(userEmail).getText();
    }

    public String verifyUserLogged(final String userName, final String userEmail) {
        clickOnMeTab();
        matchUserName(userName);
        return matchUserEmail(userEmail);
    }
}
