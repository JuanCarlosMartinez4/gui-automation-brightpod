package brightpod;

import core.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginPageTest {
    LoginPage loginPage;

    @Before
    public void setUp() {
        loginPage = new LoginPage();
    }

    @After
    public void tearDown() {
        WebDriverManager.getInstance().quitDriver();
    }

    @Test
    public void login_returnMePage() {
        String userName = "juan martinez";
        String email = "juan.martinez.tacc11@gmail.com";
        String password = "passacction20B";
        loginPage.login(email, password);
        MePage mePage = new MePage();
        String actual = mePage.verifyUserLogged(userName, email);

        assert actual.equals(email);

        MenuNavbar navbar = new MenuNavbar();
        navbar.logout();
        LogoutPage logoutPage = new LogoutPage();
        logoutPage.logout();
    }

    @Test
    public void loginWithErrors() {
        String email = "juan.martinez.tacc11@gmail.co";
        String password = "passacction20B";
        String expected = "Oops! Invalid login. Please try again.";
        String actual = loginPage.loginWithErrors(email, password);

        assert actual.equals(expected);
    }
}