package brightpod;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;

public class SettingTextLink extends BasePage {

    @FindBy(xpath = "//a[@class='stats_link'][@data-toggle='popover']")
    WebElement settingIcon;

    @FindBy(linkText = "Settings")
    WebElement settingsTextLink;

    @FindBy(linkText = "Archive Pod")
    WebElement archivePodButton;

    public SettingTextLink() throws IOException {
    }

    @Override
    protected void waitUntilPageObjectIsLoaded() {
        webDriverWait.until(ExpectedConditions.visibilityOf(settingsTextLink));
    }

    public void openSettings() {
        settingIcon.click();
        settingsTextLink.click();
    }

    public void archivePod() {
        archivePodButton.click();
        webDriver.switchTo().alert().accept();
    }
}
