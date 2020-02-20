package brightpod;

import core.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Login {
    private WebDriver webDriver;
    private static final String URL_SIMPLE = "https://app.brightpod.com/user";
    LoginPage loginPage;

    public Login() {
        webDriver = WebDriverManager.getInstance().startBrowser("Chrome");
        loginPage = PageFactory.initElements(webDriver, LoginPage.class);
    }

    public void setLogin(String email, String password) {
        loginPage.loginToSite(email, password);
    }
}
