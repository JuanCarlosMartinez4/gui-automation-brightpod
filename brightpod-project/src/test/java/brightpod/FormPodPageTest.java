package brightpod;

import core.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class FormPodPageTest {
    private String podName = "Empty Pod1";
    private HashMap<String, String> texts;
    PodsPage podsPage;
    NewPodModal podModal;
    FormPodPage formPod;
    SearchPod search;
    TaskListPage taskList;
    SettingTextLink setting;

    private Map<String, String> actualPodValues;

    @Before
    public void setUp() {
        String email = "juan.martinez.tacc11@gmail.com";
        String password = "passacction20B";
        texts = new HashMap<>();
        texts.put("podName", podName);
        texts.put("startDate", "29");
        texts.put("dueDate", "1");
        texts.put("budgetTime", "0.30");
        texts.put("client", "fundacion");
        texts.put("projectLead", "juan martinez");
        texts.put("color", "#F83A22");
        texts.put("description", "this a description");
        LoginPage loginPage = new LoginPage();
        podsPage = loginPage.login(email, password);
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
        logoutPage.returnInitPage();
        WebDriverManager.getInstance().quitDriver();
    }

    @Test
    public void createNewPod_newPod() {
        podModal = podsPage.clickNewPodButton();
        formPod = podModal.createNewPod();

        HashMap<String, String> expected = new HashMap<>();
        expected.put("podName", podName);
        expected.put("startDate", "Feb 29, 2020");
        expected.put("dueDate", "Mar 01, 2020");
        expected.put("budgetTime", "0.30");
        expected.put("client", "fundacion");
        expected.put("projectLead", "You");
        expected.put("description", "this a description");
        TaskListPage taskList = formPod.createNewPod(texts);
        SettingTextLink setting = new SettingTextLink();
        setting.editPod();
        actualPodValues = formPod.getPodInformation();
        formPod.goBackToTheDashboardButton();
        for (String key: actualPodValues.keySet()) {
            Assert.assertEquals("message: ", expected.get(key), actualPodValues.get(key));
        }
    }

    @Test
    public void updatePod_podUpdated() {
        podModal = podsPage.clickNewPodButton();
        formPod = podModal.createNewPod();
        taskList = formPod.createNewPod(texts);
        search = new SearchPod();
        search = search.searchElementByName(podName);
        setting = new SettingTextLink();
        setting.editPod();
        texts = new HashMap<>();
        texts.put("podName", podName);
        texts.put("description", "updated description");

        HashMap<String, String> expected = new HashMap<>();
        expected.put("podName", podName);
        expected.put("startDate", "Feb 29, 2020");
        expected.put("dueDate", "Mar 01, 2020");
        expected.put("budgetTime", "0.30");
        expected.put("client", "fundacion");
        expected.put("projectLead", "You");
        expected.put("description", "updated description");
        formPod = new FormPodPage();
        taskList = formPod.updatePod(texts);
        setting.editPod();
        actualPodValues = formPod.getPodInformation();
        formPod.goBackToTheDashboardButton();
        for (String key: actualPodValues.keySet()) {
            Assert.assertEquals("message: ", expected.get(key), actualPodValues.get(key));
        }
    }
}