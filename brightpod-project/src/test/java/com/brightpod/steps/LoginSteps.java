package com.brightpod.steps;

import brightpod.MenuNavbar;
import brightpod.LogoutPage;
import brightpod.LoginPage;
import brightpod.MePage;
import brightpod.PageTransporter;
import brightpod.PodsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginSteps {

    @When("^I login with user credentials in \"([^\"]*)\"$")
    public void login(String page) {
        String email = "juan.martinez.at11cc@gmail.com";
        String password = "at11account2020";
        PageTransporter.goToUrl(page);
        LoginPage loginPage = new LoginPage();
        PodsPage podsPage = loginPage.login(email, password);
    }

    @And("^I verify authenticated user$")
    public void verifyUserAuthenticated() {
        String userName = "juan martinez";
        String email = "juan.martinez.at11cc@gmail.com";
        MePage mePage = new MePage();
        String actual = mePage.verifyUserLogged(userName, email);
        Assert.assertEquals(": ", email, actual);
    }

    @When("^I selects on Logout$")
    public void Logout() {
        MenuNavbar navbar = new MenuNavbar();
        LogoutPage logoutPage = navbar.logout();
        logoutPage.returnInitPage();
    }
}
