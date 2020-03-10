package com.brightpod.selectsite.runner;

import brightpod.LoginPage;
import brightpod.LogoutPage;
import brightpod.MenuNavbar;
import brightpod.PageTransporter;
import brightpod.PodsPage;
import core.WebDriverManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import report.Report;

public class TestRunner extends AbstractTestNGCucumberTests {

    @BeforeTest
    public void beforeExecution() {
        String page = "/user";
        String email = "juan.martinez.at11cc@gmail.com";
        String password = "at11account2020";
        PageTransporter.goToUrl(page);
        LoginPage loginPage = new LoginPage();
            PodsPage podsPage = loginPage.login(email, password);
    }

    @AfterTest
    public void afterExecution() {
        MenuNavbar navbar = new MenuNavbar();
        LogoutPage logoutPage = navbar.logout();
        logoutPage.returnInitPage();
        WebDriverManager.getInstance().quitDriver();
        Report.getInstance().generateReport();
    }
}
