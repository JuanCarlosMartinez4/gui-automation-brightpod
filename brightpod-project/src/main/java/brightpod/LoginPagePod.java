package brightpod;

import core.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPagePod  {
    private WebDriver webDriver;
    private static final String URL_SIMPLE = "https://app.brightpod.com/user";

    public LoginPagePod() {
        webDriver = WebDriverManager.getInstance().startBrowser("Firefox");
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(how=How.XPATH, using="//input[@type='text'][@id='username_text']")
    WebElement emailTextBox;
    @FindBy(how=How.XPATH, using="//input[@type='password'][@id='password']")
    WebElement passwordTextBox;
    @FindBy(how=How.XPATH, using="//button[@type='submit'][@class='btn btn-default btn-success signin']")
    WebElement signinButton;

    public void setEmail(String strEmail){
        emailTextBox.sendKeys(strEmail);
    }

    public void setPassword(String strPassword) {
        passwordTextBox.sendKeys(strPassword);
    }

    public void clickOnLoginButton(){
        signinButton.click();
    }

    public void quit() {
        WebDriverManager.getInstance().quitDriver();
    }
}
