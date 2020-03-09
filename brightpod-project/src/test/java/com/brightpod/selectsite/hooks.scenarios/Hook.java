package com.brightpod.selectsite.hooks.scenarios;

import brightpod.LoginPage;
import brightpod.LogoutPage;
import brightpod.MenuNavbar;
import brightpod.PageTransporter;
import brightpod.PodsPage;
import entities.Context;
import entities.Pod;
import io.cucumber.java.After;
import io.cucumber.java.Before;

/**
 * Executes actions for scenarios.
 */
public class Hook {
    private Context context;
    private Pod pod;

    /**
     * Generates instance of Hook.
     * @param context value.
     */
    public Hook(final Context context) {
        this.context = context;
        this.pod = context.getPod();
    }

    @Before
    public void beforeScenario() {
        String page = "/user";
        String email = "juan.martinez.at11cc@gmail.com";
        String password = "at11account2020";
        PageTransporter.goToUrl(page);
        LoginPage loginPage = new LoginPage();
        PodsPage podsPage = loginPage.login(email, password);
    }

    @After
    public void afterScenario() {
        MenuNavbar navbar = new MenuNavbar();
        LogoutPage logoutPage = navbar.logout();
        logoutPage.returnInitPage();
    }
}
