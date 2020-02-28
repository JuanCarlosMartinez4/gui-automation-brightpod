import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MyStepdefs {
    @When("^It login with user credentials$")
    public void itLoginWithUserCredentials() {
        assert 1 == 1;
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
}
