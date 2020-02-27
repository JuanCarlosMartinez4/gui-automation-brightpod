package brightpod;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import sun.security.krb5.internal.PAForUserEnc;

public class SettingTextLink extends BasePage {

    private final String PROJECT_NAME = "//div[contains(text(),'%s')]";

    @FindBy(xpath = "//a[@class='stats_link'][@data-toggle='popover']")
    WebElement settingIcon;

    @FindBy(linkText = "Settings")
    WebElement settingsTextLink;

    @FindBy(linkText = "Archive Pod")
    WebElement archivePodButton;

    @FindBy(xpath = "//div[contains(@class,'col-lg-9 col-md-9')]")
    WebElement projectSetting;

    @Override
    protected void waitUntilPageObjectIsLoaded() {

        webDriverWait.until(ExpectedConditions.visibilityOf(projectSetting));
    }

    private void clickOnOpenSetting() {
        settingIcon.click();
    }

    private void clickOnSettingsTextLink() {
        settingsTextLink.click();
    }

    public void clickOnArchivePod() {
        archivePodButton.click();
    }

    private void clickOnAcceptAlert() {
        webDriverWait.until(ExpectedConditions.alertIsPresent());
        Alert alert = webDriver.switchTo().alert();
        alert.accept();
    }

    public void archivePod() {
        clickOnOpenSetting();
        clickOnSettingsTextLink();
        clickOnArchivePod();
        clickOnAcceptAlert();
    }
}
