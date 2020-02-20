package brightpod;

import core.WebDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {
//    @FindBy(how= How.ID,using="username_text")
    private WebElement username_text;

//    @FindBy(how=How.ID,using="password")
    private WebElement password;

    @FindBy(how=How.XPATH,using="//*[@id=\"login-form\"]/div/div[2]/fieldset/div[5]/div/button")
    private WebElement signInButton;

    public void loginToSite(String inputEmail, String inputPassword) {
        username_text.sendKeys(inputEmail);
        password.sendKeys(inputPassword);
        signInButton.click();
        WebDriverManager.getInstance().quitDriver();
    }
}
