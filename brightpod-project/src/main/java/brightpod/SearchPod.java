package brightpod;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchPod extends BasePage {

    private final String ELEMENT_FOUND = "//div[@class='search_result']//div[@class='search_link']//a[text()='%s']";

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

    private WebElement getFindElement(final String elementName) {
        return webDriver.findElement(By.xpath(String.format(ELEMENT_FOUND, elementName)));
    }

    public String searchElementByName(final String elementName) {
        setSearchField(elementName);
        String elementNameText = getFindElement(elementName).getText();
        getFindElement(elementName).click();
        return elementNameText;
    }

    public String verifyDeletedElement(final String elementName) {
        setSearchField(elementName);
        return searchErrorText.getText();
    }
}
