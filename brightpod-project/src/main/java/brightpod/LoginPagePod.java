package brightpod;

import core.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPagePod extends BasePage {
    private WebDriver webDriver;
    private static final String URL_SIMPLE = "https://app.brightpod.com/user";
    private String email;
    private String password;

    public LoginPagePod(String email, String password, String browser, String url) {
        webDriver = WebDriverManager.getInstance().startBrowser(browser, url);
        PageFactory.initElements(webDriver, this);
        this.email = email;
        this.password = password;
    }

    @FindBy(how=How.XPATH, using="//input[@type='text'][@id='username_text']")
    WebElement emailTextBox;
    @FindBy(how=How.XPATH, using="//input[@type='password'][@id='password']")
    WebElement passwordTextBox;
    @FindBy(how=How.XPATH, using="//button[@type='submit'][@class='btn btn-default btn-success signin']")
    WebElement signinButton;

    @Override
    public void executeAction() {
        emailTextBox.sendKeys(email);
        passwordTextBox.sendKeys(password);
        signinButton.click();
    }
}
