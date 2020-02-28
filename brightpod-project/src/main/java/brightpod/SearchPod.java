package brightpod;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchPod extends BasePage {

    private final String POD_FOUND = "//div[@class='search_result']//div[@class='search_link']//a[text()='%s']";

    @FindBy(css = "a[title='Pods']")
    WebElement podTab;

    @FindBy(css = "input[type='text'][class='search_input form-control']")
    WebElement searchField;

    @FindBy(css = "p[class='search_error']")
    WebElement searchErrorText;

    @Override
    protected void waitUntilPageObjectIsLoaded() {
        webDriverWait.until(ExpectedConditions.visibilityOf(searchField));
    }

    private void setSearchField(final String podName) {
        searchField.sendKeys(podName);
        searchField.sendKeys(Keys.ENTER);
    }

    private WebElement getPod(final String podName) {
        return webDriver.findElement(By.xpath(String.format(POD_FOUND, podName)));
    }

    public String searchPodByName(final String podName) {
        setSearchField(podName);
        String podNameText = getPod(podName).getText();
        getPod(podName).click();
        return podNameText;
    }

    public String verifyDeletedPod(final String podName) {
        setSearchField(podName);
        return searchErrorText.getText();
    }
}
