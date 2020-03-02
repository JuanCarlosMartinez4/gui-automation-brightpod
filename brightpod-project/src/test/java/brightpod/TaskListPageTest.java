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
    TaskListPage taskList;
    LoginPage loginPage;
    PodsPage podsPage;
    NewPodModal podsModal;
    FormPodPage formPod;

    @Before
    public void setUp() {
        String email = "juan.martinez.tacc11@gmail.com";
        String password = "passacction20B";
        texts = new HashMap<>();
        texts.put("projectName", podName);
        texts.put("description", "this a description");
        loginPage = new LoginPage();
        podsPage = loginPage.login(email, password);
//        podsPage = new PodsPage();
        podsModal = podsPage.displayPodModal();
//        podsModal = new NewPodModal();
        formPod = podsModal.createNewPod();
//        FormPodPage formPod = new FormPodPage();
        taskList = formPod.createNewPod(texts);
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
    public void addNewTaskList_taskList() {
        String listName = "My tasks";
        String listDescription = "This tasks are for week";
        boolean isVisible = true;
        taskList = new TaskListPage();
        HashMap<String, String> expected = new HashMap<>();
        expected.put("name", listName);
        expected.put("description", listDescription);
        taskList = taskList.addNewTaskList(listName, listDescription, isVisible);
        HashMap<String, String> actual = taskList.getFieldsText(listName, listDescription);
        for (String key: actual.keySet()) {
            Assert.assertEquals("message: ", expected.get(key), actual.get(key));
        }
    }

    @Test
    public void updateTaskList_taskListUpdated() {
        String listName = "My tasks";
        String listDescription = "This tasks are for week";
        boolean isVisible = true;
//        taskList = new TaskListPage();
        taskList = taskList.addNewTaskList(listName, listDescription, isVisible);
        SearchPod search = new SearchPod();
        search.searchElementByName(listName);
        taskList.editTaskListSearched(listName);
        texts = new HashMap<>();
        texts.put("listName", "updated list");
        texts.put("listDescription", "updated description");
        HashMap<String, String> expected = new HashMap<>();
        expected.put("listName", "updated list");
        expected.put("listDescription", "updated description");
        taskList = taskList.updateTaskList(listName, texts);
        HashMap<String, String> actual;
//        for (String key: actual.keySet()) {
//            Assert.assertEquals("message: ", expected.get(key), actual.get(key));
//        }
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