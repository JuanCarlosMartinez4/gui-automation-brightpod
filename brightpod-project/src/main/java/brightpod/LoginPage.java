package brightpod;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @FindBy(id = "username_text")
    WebElement emailTextBox;

    @FindBy(id = "password")
    WebElement passwordTextBox;

    @FindBy(how = How.XPATH, using="//button[@type='submit'][@class='btn btn-default btn-success signin']")
    WebElement signInButton;

    @Override
    protected void waitUntilPageObjectIsLoaded() {
        webDriverWait.until(ExpectedConditions.visibilityOf(signInButton));
    }

    private void setEmailTextBox(final String email) {
        emailTextBox.clear();
        emailTextBox.sendKeys(email);
    }

    private void setPasswordTextBox(final String password) {
        passwordTextBox.clear();
        passwordTextBox.sendKeys(password);
    }

    private void clickSignInButton() {
        signInButton.click();
    }

    public PodsPage login(final String email, final String password) {
        setEmailTextBox(email);
        setPasswordTextBox(password);
        clickSignInButton();
        return new PodsPage();
    }


    public LoginPage loginWithErrors(final String email, final String password) {
        setEmailTextBox(email);
        setPasswordTextBox(password);
        clickSignInButton();
        return this;
    }


}
