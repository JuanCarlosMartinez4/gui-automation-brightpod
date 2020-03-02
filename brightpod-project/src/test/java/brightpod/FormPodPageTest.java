package brightpod;

import core.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class FormPodPageTest {
    private String podName = "Empty Pod1";
    private HashMap<String, String> texts;

    @Before
    public void setUp() {
        String email = "juan.martinez.tacc11@gmail.com";
        String password = "passacction20B";
        texts = new HashMap<>();
        texts.put("projectName", podName);
        texts.put("startDate", "29");
        texts.put("dueDate", "1");
        texts.put("budgetTime", "0.30");
        texts.put("client", "fundacion");
        texts.put("projectLead", "juan martinez");
        texts.put("color", "#F83A22");
        texts.put("description", "this a description");
        LoginPage loginPage = new LoginPage();
        loginPage.login(email, password);
    }

    @After
    public void tearDown() {
        SearchPod searchPod = new SearchPod();
        searchPod.searchElementByName(podName);
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
        podsPage.displayNewPodModal();
        NewPodModal podsModal = new NewPodModal();
        podsModal.createNewPod();
        FormPodPage formPod = new FormPodPage();
        HashMap<String, String> expected = new HashMap<>();
        expected.put("projectName", podName);
        expected.put("startDate", "Jan 29, 2020");
        expected.put("dueDate", "Jan 31, 2020");
        expected.put("budgetTime", "0.30");
        expected.put("client", "fundacion");
        expected.put("projectLead", "juan martinez");
        expected.put("description", "this a description");
        HashMap<String, String> actual = formPod.createNewPod(texts);
        for (String key: actual.keySet()) {
            Assert.assertEquals("message: ", expected.get(key), actual.get(key));
        }
    }

    @Test
    public void updatePod_podUpdated() {
        PodsPage podsPage = new PodsPage();
        podsPage.displayNewPodModal();
        NewPodModal podsModal = new NewPodModal();
        podsModal.createNewPod();
        FormPodPage formPod = new FormPodPage();
        formPod.createNewPod(texts);
        SearchPod search = new SearchPod();
        search.searchElementByName(podName);
        SettingTextLink setting = new SettingTextLink();
        setting.editPod();
        texts = new HashMap<>();
        texts.put("projectName", podName);
        texts.put("description", "updated description");

        HashMap<String, String> expected = new HashMap<>();
        expected.put("projectName", podName);
        expected.put("startDate", "Jan 29, 2020");
        expected.put("dueDate", "Jan 31, 2020");
        expected.put("budgetTime", "0.30");
        expected.put("client", "fundacion");
        expected.put("projectLead", "you");
        expected.put("description", "updated description");

        HashMap<String, String> actual = formPod.updatePod(texts);
        for (String key: actual.keySet()) {
            Assert.assertEquals("message: ", expected.get(key), actual.get(key));
        }
    }

    @Test
    public void goBackToPodsPage_podPage() {
        PodsPage podsPage = new PodsPage();
        podsPage.displayNewPodModal();
        NewPodModal podsModal = new NewPodModal();
        podsModal.createNewPod();
        FormPodPage formPod = new FormPodPage();
        formPod.goBackToPodsPage();
    }
}