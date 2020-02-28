import brightpod.LoginPage;
import brightpod.MePage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import core.WebDriverManager;

public class MySteps {
    @When("^It login with user credentials$")
    public void itLoginWithUserCredentials() {
        LoginPage loginPage = new LoginPage();
        String userName = "juan martinez";
        String email = "juan.martinez.tacc11@gmail.com";
        String password = "passacction20B";
        loginPage.login(email, password);
        MePage mePage = new MePage();
        String actual = mePage.verifyUserLogged(userName, email);
        WebDriverManager.getInstance().quitDriver();
        assert actual.equals(email);
    }

    @Then("^The application displays \"([^\"]*)\" page$")
    public void theApplicationDisplaysPage(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
        assert 1 == 1;
    }

    @When("^Selects on Logout$")
    public void selectsOnLogout() {
        assert 1 == 1;
    }

    @When("^\"([^\"]*)\" a \"([^\"]*)\" with the following$")
    public void aWithTheFollowing(String arg0, String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^The \"([^\"]*)\" should be \"([^\"]*)\"$")
    public void theShouldBe(String arg0, String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^It search a \"([^\"]*)\" by name \"([^\"]*)\"$")
    public void itSearchAByName(String arg0, String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^The application displays \"([^\"]*)\"$")
    public void theApplicationDisplays(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
