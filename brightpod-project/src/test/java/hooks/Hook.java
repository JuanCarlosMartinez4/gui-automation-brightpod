package hooks;

import brightpod.LoginPage;
import brightpod.PageTransporter;
import brightpod.PodsPage;
import core.WebDriverManager;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import entities.Context;
import entities.Pod;

public class Hook {
    Context context;
    Pod pod;

    public Hook(final Context context) {
        this.context = context;
        this.pod = context.getPod();
    }

    @Before
    public void beforeScenario() {
        String page = "/user";
        String email = "juan.martinez.tacc11@gmail.com";
        String password = "passacction20B";
        PageTransporter.goToUrl(page);
        LoginPage loginPage = new LoginPage();
        PodsPage podsPage = loginPage.login(email, password);
    }

    @After
    public void afterScenario() {
        WebDriverManager.getInstance().quitDriver();
    }
}
