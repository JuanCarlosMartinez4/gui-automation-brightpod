package brightpod;

import core.WebDriverManager;
import entities.Context;
import entities.Pod;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class FormPodPageTest {
    private String podName = "Pod1";
    private Map<String, String> podInformation;
    private PodsPage podsPage;
    private FormPodPage formPod;
    private SearchPod search;
    private TaskListPage taskList;
    private SettingTextLink setting;
    private NewPodModal newPodModal;

    Context context;
    Pod pod;

    public FormPodPageTest(final Context context) {
        this.context = context;
        this.pod = context.getPod();
    }

    final static private String POD_NAME = "Pod Name";
    final static private String START_DATE = "Start Date";
    final static private String DUE_DATE = "Due Date";
    final static private String BUDGET_TIME = "Budget Time";
    final static private String CLIENT = "Client";
    final static private String POD_LEAD = "Project Lead";
    final static private String POD_COLOR = "Color";
    final static private String DESCRIPTION = "Description";

    private Map<String, String> actualPodValues;

    @Before
    public void setUp() {
        String email = "juan.martinez.at11cc@gmail.com";
        String password = "at11account2020";
        podInformation = new HashMap<>();
        podInformation.put(POD_NAME, podName);
        podInformation.put(START_DATE, "TODAY");
        podInformation.put(DUE_DATE, "TODAY");
        podInformation.put(BUDGET_TIME, "0.30");
        podInformation.put(CLIENT, "Jala-Fundation");
        podInformation.put(POD_LEAD, "juan martinez");
        podInformation.put(DESCRIPTION, "this is a description");
        LoginPage loginPage = new LoginPage();
        String page = "/user";
        PageTransporter.goToUrl(page);
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
        HashMap<String, String> expected = new HashMap<>();
        expected.put(POD_NAME, podName);
        expected.put(START_DATE, "TODAY");
        expected.put(DUE_DATE, "TODAY");
        expected.put(BUDGET_TIME, "0.30");
        expected.put(CLIENT, "Jala-Fundation");
        expected.put(POD_LEAD, "juan martinez");
        expected.put(DESCRIPTION, "this is a description");
        pod.setPodInformation(podInformation);
        podsPage = new PodsPage();
        newPodModal = podsPage.clickNewPodButton();
        formPod = newPodModal.createNewPod();
        taskList = formPod.createNewPod(pod, podInformation.keySet());
        SettingTextLink setting = new SettingTextLink();
        setting.editPod();
        actualPodValues = formPod.getPodInformation(pod.getPodInformation());
        for (String key : actualPodValues.keySet()) {
            Assert.assertEquals("message: ", expected.get(key), actualPodValues.get(key));
        }
    }

    @Test
    public void updatePod_podUpdated() {
        newPodModal = podsPage.clickNewPodButton();
        formPod = newPodModal.createNewPod();
//        taskList = formPod.createNewPod(texts);
        search = new SearchPod();
        search = search.searchElementByName(podName);
        setting = new SettingTextLink();
        setting.editPod();
        podInformation = new HashMap<>();
        podInformation.put(POD_NAME, "updatedPod");
        podInformation.put(DESCRIPTION, "updated description");

        HashMap<String, String> expected = new HashMap<>();
        expected.put(POD_NAME, "updatedPod");
        expected.put(DESCRIPTION, "updated description");
        formPod = new FormPodPage();
//        taskList = formPod.updatePod(texts);
        setting.editPod();
//        actualPodValues = formPod.getPodInformation();
        formPod.goBackToTheDashboardButton();
        for (String key : actualPodValues.keySet()) {
            Assert.assertEquals("message: ", expected.get(key), actualPodValues.get(key));
        }
    }
}