package brightpod;

import core.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MePageTest {

    private String email = "juan.martinez.tacc11@gmail.com";
    @Before
    public void setUp() {
        LoginPage loginPage = new LoginPage();
        String password = "passacction20B";
        loginPage.login(email, password);
    }

    @After
    public void tearDown() {
        MenuNavbar navbar = new MenuNavbar();
        navbar.logout();
        LogoutPage logoutPage = new LogoutPage();
        logoutPage.logout();
        WebDriverManager.getInstance().quitDriver();

    }

    @Test
    public void verifyUserLogged() {
        MePage mePage = new MePage();
        String userName = "juan martinez";
        String actual = mePage.verifyUserLogged(userName, email);

        assert actual.equals(email);
    }
}