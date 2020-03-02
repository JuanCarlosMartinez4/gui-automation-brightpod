package brightpod;

import core.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LogoutPageTest {

    @Before
    public void setUp() {
        LoginPage loginPage = new LoginPage();
        String email = "juan.martinez.tacc11@gmail.com";
        String password = "passacction20B";
        loginPage.login(email, password);
    }

    @After
    public void tearDown() {
        WebDriverManager.getInstance().quitDriver();
    }

    @Test
    public void logout() {
        MenuNavbar navbar = new MenuNavbar();
        navbar.logout();
        LogoutPage logoutPage = new LogoutPage();
        logoutPage.returnInitPage();
    }

    @Test
    public void logoutWithErrors() {
    }
}