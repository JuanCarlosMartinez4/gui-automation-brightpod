package steps;

import brightpod.*;
import core.WebDriverManager;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class LoginSteps {

    @When("^Login with user credentials in \"([^\"]*)\"$")
    public void loginWithUserCredentials(String page) {
        String email = "juan.martinez.tacc11@gmail.com";
        String password = "passacction20B";
        PageTransporter.goToUrl(page);
        LoginPage loginPage = new LoginPage();
        PodsPage podsPage = loginPage.login(email, password);
    }

    @And("^Verify user authenticated$")
    public void verifyUserAuthenticated() {
        String userName = "juan martinez";
        String email = "juan.martinez.tacc11@gmail.com";
        MePage mePage = new MePage();
        String actual = mePage.verifyUserLogged(userName, email);
        Assert.assertEquals(": ", email, actual);
    }

    @When("^Selects on Logout$")
    public void selectsOnLogout() {
        MenuNavbar navbar = new MenuNavbar();
        LogoutPage logoutPage = navbar.logout();
        logoutPage.returnInitPage();
    }

    @And("^Quit session$")
    public void closeSession() {
        WebDriverManager.getInstance().quitDriver();
    }
}
