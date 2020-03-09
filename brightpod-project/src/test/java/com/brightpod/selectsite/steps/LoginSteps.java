package com.brightpod.selectsite.steps;

import brightpod.MenuNavbar;
import brightpod.LogoutPage;
import brightpod.LoginPage;
import brightpod.MePage;
import brightpod.PageTransporter;
import brightpod.PodsPage;
import brightpod.ScreenShot;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginSteps {

    @When("^Login with user credentials in \"([^\"]*)\"$")
    public void loginWithUserCredentials(String page) {
        String email = "juan.martinez.at11cc@gmail.com";
        String password = "at11account2020";
        PageTransporter.goToUrl(page);
        LoginPage loginPage = new LoginPage();
        PodsPage podsPage = loginPage.login(email, password);
    }

    @And("^Verify user authenticated$")
    public void verifyUserAuthenticated() {
        String userName = "juan martinez";
        String email = "juan.martinez.at11cc@gmail.com";
        MePage mePage = new MePage();
        String actual = mePage.verifyUserLogged(userName, email);
        Assert.assertEquals(": ", email, actual);
        ScreenShot.captureScreenShot(actual);
    }

    @When("^Selects on Logout$")
    public void selectsOnLogout() {
        MenuNavbar navbar = new MenuNavbar();
        LogoutPage logoutPage = navbar.logout();
        logoutPage.returnInitPage();
    }
}