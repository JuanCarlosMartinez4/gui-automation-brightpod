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

    @FindBy(css = "a[data-toggle='modal'][class='btn btn-default btn-success']")
    WebElement createNewPodButton;

    @FindBy(css = "div[class='alert alert-danger']")
    WebElement invalidLoginMessage;

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

    private String getCreateANewPodText() {
        return createNewPodButton.getText();
    }

    public String login(final String email, final String password) {
        setEmailTextBox(email);
        setPasswordTextBox(password);
        clickSignInButton();
        return getCreateANewPodText();
    }


    public String loginWithErrors(final String email, final String password) {
        setEmailTextBox(email);
        setPasswordTextBox(password);
        clickSignInButton();
        return invalidLoginMessage.getText();
    }


}
