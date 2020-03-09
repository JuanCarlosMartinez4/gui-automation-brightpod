package brightpod;

import core.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginPageTest {
    private LoginPage loginPage;

    @Before
    public void setUp() {
        loginPage = new LoginPage();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void login_returnMePage() {
        String userName = "juan martinez";
        String email = "juan.martinez.at11cc@gmail.com";
        String password = "at11account2020";
        String page = "/user";
        PageTransporter.goToUrl(page);
        PodsPage podsPage = loginPage.login(email, password);
        MePage mePage = new MePage();
        String actual = mePage.verifyUserLogged(userName, email);

        assert actual.equals(email);

        MenuNavbar navbar = new MenuNavbar();
        LogoutPage logoutPage = navbar.logout();
        logoutPage.returnInitPage();
        WebDriverManager.getInstance().quitDriver();
    }

    @Test
    public void loginWithErrors() {
        String email = "juan.martinez.at11cc@gmail.co";
        String password = "invalidPassword";
        String expected = "Oops! Invalid login. Please try again.";
        loginPage = loginPage.loginWithErrors(email, password);
        assert expected.equals(loginPage.getInvalidLoginMessage());
        WebDriverManager.getInstance().quitDriver();
    }
}