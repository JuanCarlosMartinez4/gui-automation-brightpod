package brightpod;

import core.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NewPodModalTest {

    private String podName = "Empty Pod1";
    @Before
    public void setUp() {
        String email = "juan.martinez.tacc11@gmail.com";
        String password = "passacction20B";
        LoginPage loginPage = new LoginPage();
        String actual = loginPage.login(email, password);
    }

    @After
    public void tearDown() {
        SearchPod searchPod = new SearchPod();
        searchPod.searchPodByName(podName);
        SettingTextLink setting = new SettingTextLink();
        setting.archivePod();
        MenuNavbar navbar = new MenuNavbar();
        navbar.logout();
        LogoutPage logoutPage = new LogoutPage();
        logoutPage.logout();
        WebDriverManager.getInstance().quitDriver();
    }

    @Test
    public void createNewPod_newPod() {
        PodsPage podsPage = new PodsPage();
        String value = podsPage.displayNewPodModal();
        NewPodModal podsModal = new NewPodModal();
        String actual = podsModal.createNewPod(podName);

        assert actual.equals(podName);
    }

    @Test
    public void goBackToPodsPage() {
    }
}