import brightpod.LoginPage;
import brightpod.LogoutPage;
import brightpod.PodsPage;
import core.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginPageTest {

    @Before
    public void initialize() {

    }

    @After
    public void TeardownTest()
    {
        WebDriverManager.getInstance().quitDriver();
    }

    @Test
    public void test_loginInBrightpod() {
        String email = "juan.martinez.tacc11@gmail.com";
        String password = "passacction20B";
        LoginPage loginPage = new LoginPage();
        Object actual = loginPage.login(email, password);
        PodsPage podsPage = new PodsPage();
        assert actual.getClass() == podsPage.getClass();

        LogoutPage logoutPage = new LogoutPage();
        Object logout = logoutPage.logout();
        assert loginPage.getClass() == logout.getClass();
    }

    @Test
    public void test_createANewPod() {
        String email = "juan.martinez.tacc11@gmail.com";
        String password = "passacction20B";
        String newPodName = "Empty Pod";
//        loginPagePod = new LoginPage();
//        podsPage = new NewPodModal();
//        logoutPage = new LogoutPage();
    }
}
