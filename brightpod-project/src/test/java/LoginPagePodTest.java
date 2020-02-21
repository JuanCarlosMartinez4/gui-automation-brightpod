import brightpod.BasePage;
import brightpod.LoginPagePod;
import brightpod.LogoutPage;
import brightpod.NewPodModal;
import brightpod.PodsPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginPagePodTest {
    BasePage loginPagePod;
    BasePage podsPage;
    BasePage logoutPage;

    @Before
    public void initialize() {

    }

    @After
    public void TeardownTest()
    {

    }

    @Test
    public void test_loginInBrightpod() {
        String email = "juan.martinez.tacc11@gmail.com";
        String password = "passacction20B";
        String browser = "Chrome";
        String url = "https://app.brightpod.com/user";
        loginPagePod = new LoginPagePod(email, password, browser, url);
        loginPagePod.executeAction();
        podsPage = new PodsPage(browser, url);
        podsPage.executeAction();
        logoutPage = new PodsPage(browser, url);
        logoutPage.executeAction();
        logoutPage.quit();
    }

    @Test
    public void test_createANewPod() {
        String email = "juan.martinez.tacc11@gmail.com";
        String password = "passacction20B";
        String browser = "Chrome";
        String url = "https://app.brightpod.com/user";
        String newPodName = "Empty Pod";
        loginPagePod = new LoginPagePod(email, password, browser, url);
        loginPagePod.executeAction();
        podsPage = new NewPodModal(newPodName, browser, url);
        podsPage.executeAction();
        logoutPage = new LogoutPage(browser, url);
        logoutPage.executeAction();
        podsPage.quit();
    }
}
