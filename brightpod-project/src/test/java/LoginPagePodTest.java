import brightpod.LoginPagePod;
import brightpod.PodsPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginPagePodTest {
    LoginPagePod loginPagePod;
    PodsPage podsPage;

    @Before
    public void initialize() {
        loginPagePod = new LoginPagePod();
        podsPage = new PodsPage();
    }

    @After
    public void TeardownTest()
    {
        podsPage.quit();
    }

    @Test
    public void test_setLogin() {
        loginPagePod.setEmail("juan.martinez.tacc11@gmail.com");
        loginPagePod.setPassword("passacction20B");
        loginPagePod.clickOnLoginButton();

        podsPage.clickOnPodsTab();
        podsPage.clickOnLogout();
        podsPage.clickOnReturnInit();
    }
}
