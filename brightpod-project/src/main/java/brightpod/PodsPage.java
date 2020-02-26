package brightpod;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;

public class PodsPage extends BasePage {

    @FindBy(how= How.XPATH, using="//li[@id='pods_tab'][@class='active']")
    WebElement podsTab;

    public PodsPage() throws IOException {
    }

    @Override
    protected void waitUntilPageObjectIsLoaded() {
        webDriverWait.until(ExpectedConditions.visibilityOf(podsTab));
    }
}
