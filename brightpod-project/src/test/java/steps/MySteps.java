package steps;

import brightpod.*;
import core.WebDriverManager;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class MySteps {
    HashMap<String, String> actual;
    @When("^It login with user credentials$")
    public void itLoginWithUserCredentials() {
        LoginPage loginPage = new LoginPage();
        String email = "juan.martinez.tacc11@gmail.com";
        String password = "passacction20B";
        loginPage.login(email, password);
    }

    @Then("^The application displays \"([^\"]*)\" page$")
    public void theApplicationDisplaysPage(String endpoint) {
        PageTransporter.goToUrl(endpoint);
    }

    @And("^Verify user authenticated$")
    public void verifyUserAuthenticated() {
        MePage mePage = new MePage();
        String userName = "juan martinez";
        String email = "juan.martinez.tacc11@gmail.com";
        String actual = mePage.verifyUserLogged(userName, email);
        Assert.assertEquals(": ", email, actual);
    }

    @When("^Selects on Logout$")
    public void selectsOnLogout() {
        LogoutPage logoutPage = new LogoutPage();
        logoutPage.returnInitPage();
    }

    @When("Create a \"([^\"]*)\" with the following$")
    public void aWithTheFollowing(String element, Map<String, String> data) {
        PodsPage podsPage = new PodsPage();
        podsPage.displayPodModal();
        NewPodModal podsModal = new NewPodModal();
        podsModal.createNewPod();
        FormPodPage formPod = new FormPodPage();
        HashMap<String, String> values = new HashMap<>();
        values.putAll(data);
        formPod.createNewPod(values);
    }

    @And("^Verify Pod$")
    public void verifyCreated(Map<String, String> data) {
        HashMap<String, String> expected = new HashMap<>();
        expected.putAll(data);
        for (String key: expected.keySet()) {
            Assert.assertEquals("message: ", expected.get(key), actual.get(key));
        }
    }

    @When("^Search a \"([^\"]*)\" by name \"([^\"]*)\"$")
    public void itSearchAByName(String type, String podName) {
        SearchPod searchPod = new SearchPod();
        searchPod.searchElementByName(podName);
    }

    @When("^Remove pod$")
    public void removePod() {
        SettingTextLink setting = new SettingTextLink();
        setting.archivePod();
        MenuNavbar navbar = new MenuNavbar();
        navbar.logout();
        LogoutPage logoutPage = new LogoutPage();
        logoutPage.returnInitPage();
    }

    @And("^The \"([^\"]*)\" should be \"([^\"]*)\"$")
    public void theShouldBe(String arg0, String arg1) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^The application displays \"([^\"]*)\"$")
    public void theApplicationDisplays(String type) {
        WebDriverManager.getInstance().quitDriver();
    }

    @When("Edit a \"([^\"]*)\" with the following$")
    public void editWithTheFollowing(String element, Map<String, String> data) {
        SettingTextLink setting = new SettingTextLink();
        setting.editPod();
        FormPodPage formPod = new FormPodPage();
        HashMap<String, String> values = new HashMap<>();
        values.putAll(data);
        formPod.updatePod(values);
    }
}
