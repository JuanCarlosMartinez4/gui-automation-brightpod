package brightpod;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SettingTextLink extends BasePage {

    @FindBy(xpath = "//a[@class='stats_link'][@data-toggle='popover']")
    private WebElement settingIcon;

    @FindBy(linkText = "Settings")
    private WebElement settingsTextLink;

    @FindBy(css = "a[class='btn btn-default btn-warning archive_pod']")
    private WebElement archivePodButton;

    @FindBy(xpath = "//div[contains(@class,'col-lg-9 col-md-9')]")
    private WebElement projectSetting;

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

    private void clickOnArchivePod() {
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

    public void editPod() {
        clickOnOpenSetting();
        clickOnSettingsTextLink();
    }
}
