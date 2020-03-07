package hooks;

import brightpod.LoginPage;
import brightpod.PageTransporter;
import brightpod.PodsPage;
import core.WebDriverManager;
//import cucumber.api.java.After;
//import cucumber.api.java.Before;
import entities.Context;
import entities.Pod;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hook {
    private Context context;
    private Pod pod;

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
        WebDriverManager.getInstance().quitDriver();
    }
}
