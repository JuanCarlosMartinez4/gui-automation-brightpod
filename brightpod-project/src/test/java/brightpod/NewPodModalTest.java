package brightpod;

import core.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NewPodModalTest {

    private String podName = "Empty Pod1";

    @Before
    public void setUp() {
        String email = "juan.martinez.at11cc@gmail.com";
        String password = "passacction20B";
        LoginPage loginPage = new LoginPage();
        loginPage.login(email, password);
    }

    @After
    public void tearDown() {
        SearchElement searchElement = new SearchElement();
        searchElement.searchElementByName(podName);
        SettingTextLink setting = new SettingTextLink();
        setting.archivePod();
        MenuNavbar navbar = new MenuNavbar();
        navbar.logout();
        LogoutPage logoutPage = new LogoutPage();
        logoutPage.returnInitPage();
        WebDriverManager.getInstance().quitDriver();
    }

    @Test
    public void createNewPod_newPod() {
        PodsPage podsPage = new PodsPage();
        podsPage.clickNewPodButton();
        NewPodModal podsModal = new NewPodModal();
        podsModal.createNewPod();
    }

    @Test
    public void goBackToPodsPage() {
    }
}