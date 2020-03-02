package brightpod;

import core.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class TaskListPageTest {
    private String podName = "Empty Pod1";
    private HashMap<String, String> texts;

    @Before
    public void setUp() {
        String email = "juan.martinez.tacc11@gmail.com";
        String password = "passacction20B";
        texts = new HashMap<>();
        texts.put("projectName", podName);
        texts.put("description", "this a description");
        LoginPage loginPage = new LoginPage();
        loginPage.login(email, password);
        PodsPage podsPage = new PodsPage();
        podsPage.displayNewPodModal();
        NewPodModal podsModal = new NewPodModal();
        podsModal.createNewPod();
        FormPodPage formPod = new FormPodPage();
        formPod.createNewPod(texts);
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
    public void addNewTaskList_taskList() {
        String listName = "My tasks";
        String listDescription = "This tasks are for week";
        boolean isVisible = true;
        TaskListPage taskList = new TaskListPage();
        HashMap<String, String> expected = new HashMap<>();
        expected.put("name", listName);
        expected.put("description", listDescription);
        HashMap<String, String> actual = taskList.addNewTaskList(listName, listDescription, isVisible);

        for (String key: actual.keySet()) {
            Assert.assertEquals("message: ", expected.get(key), actual.get(key));
        }
    }

    @Test
    public void updateTaskList_taskListUpdated() {
        String listName = "My tasks";
        String listDescription = "This tasks are for week";
        boolean isVisible = true;
        TaskListPage taskList = new TaskListPage();
        taskList.addNewTaskList(listName, listDescription, isVisible);
        texts = new HashMap<>();
        texts.put("listName", "updated list");
        texts.put("listDescription", "updated description");
        HashMap<String, String> expected = new HashMap<>();
        expected.put("listName", "updated list");
        expected.put("listDescription", "updated description");
        HashMap<String, String> actual = taskList.updateTaskList(listName, texts);

        for (String key: actual.keySet()) {
            Assert.assertEquals("message: ", expected.get(key), actual.get(key));
        }
    }

    @Test
    public void removeTaskList_taskListRemoved() {
        String listName = "MyTasks";
        String listDescription = "This tasks are for week";
        boolean isVisible = false;
        TaskListPage taskList = new TaskListPage();
        taskList.addNewTaskList(listName, listDescription, isVisible);
        SearchPod search = new SearchPod();
        search.searchElementByName(listName);
        taskList.removeTaskListSearched(listName);
        taskList.clickOnPodsTabIcon();
        String actual = search.verifyDeletedElement(listName);
        String expected = "Oops, there is nothing to show here.";
        taskList.clickOnPodsTabIcon();
        assert actual.equals(expected);
    }
}