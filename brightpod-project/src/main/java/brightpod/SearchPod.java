package brightpod;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchPod extends BasePage {

    @FindBy(css = "a[data-original-title='Pods']")
    WebElement podIcon;

    @FindBy(css = "input[type='text'][class='search_input form-control']")
    WebElement searchField;

    @FindBy(css = "div[class='search_result']")
    WebElement findPodName;

    @Override
    protected void waitUntilPageObjectIsLoaded() {
        webDriverWait.until(ExpectedConditions.visibilityOf(podIcon));
    }

    public PodsPage searchPodByName(final String podName) {
        clickOnPodIcon();
        setSearchField(podName);
        findPodByName(podName);
        return new PodsPage();
    }

    private void clickOnPodIcon() {
        podIcon.click();
    }

    private void setSearchField(final String podName) {
        searchField.sendKeys(podName);
        searchField.sendKeys(Keys.ENTER);
    }

    private void findPodByName(final String podName) {
        findPodName.findElement(new By.ByLinkText(podName));
    }
}
