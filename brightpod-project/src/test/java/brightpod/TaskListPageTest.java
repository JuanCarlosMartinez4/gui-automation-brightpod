package brightpod;

import core.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TaskListPageTest {
    private String podName = "Empty Pod1";
    private HashMap<String, String> texts;
    private TaskListPage taskList;
    private LoginPage loginPage;
    private PodsPage podsPage;
    private NewPodModal podsModal;
    private FormPodPage formPod;

    @Before
    public void setUp() {
        String email = "juan.martinez.tacc11@gmail.com";
        String password = "passacction20B";
        texts = new HashMap<>();
        texts.put("podName", podName);
        texts.put("description", "this a description");
        loginPage = new LoginPage();
        podsPage = loginPage.login(email, password);
        podsModal = podsPage.clickNewPodButton();
        formPod = podsModal.createNewPod();
//        taskList = formPod.createNewPod(texts);
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
    public void addNewTaskList_taskList() {
        String listName = "My tasks";
        String listDescription = "This tasks are for week";
        boolean isVisible = true;
        taskList = new TaskListPage();
        HashMap<String, String> expected = new HashMap<>();
        expected.put("name", listName);
        expected.put("description", listDescription);
//        taskList = taskList.addTaskListInformation(listName, listDescription, isVisible);
//        Map<String, String> actual = taskList.getCreatedTaskListInformation(listName, listDescription);
//        for (String key : actual.keySet()) {
//            Assert.assertEquals("message: ", expected.get(key), actual.get(key));
//        }
    }

    @Test
    public void updateTaskList_taskListUpdated() {
        String listName = "My tasks";
        String listDescription = "This tasks are for week";
        boolean isVisible = true;
//        taskList = taskList.addTaskListInformation(listName, listDescription, isVisible);
        SearchElement search = new SearchElement();
        search.searchElementByName(listName);
        taskList.editTaskListSearched(listName);
        texts = new HashMap<>();
        texts.put("listName", "updated list");
        texts.put("listDescription", "updated description");
        HashMap<String, String> expected = new HashMap<>();
        expected.put("listName", "updated list");
        expected.put("listDescription", "updated description");
        AddTaskPage taskPage;
//        taskPage = taskList.updateTaskListInformation(texts);
        Map<String, String> actual = taskList.getUpdatedTaskListInformation();
        for (String key : actual.keySet()) {
            Assert.assertEquals("message: ", expected.get(key), actual.get(key));
        }
    }

    @Test
    public void removeTaskList_taskListRemoved() {
        String listName = "MyTasks";
        String listDescription = "This tasks are for week";
        boolean isVisible = false;
        taskList = new TaskListPage();
//        taskList = taskList.addTaskListInformation(listName, listDescription, isVisible);
        SearchElement search = new SearchElement();
        search.searchElementByName(listName);
        taskList.removeTaskListSearched(listName);
        String actual = search.verifyDeletedElement(listName);
        String expected = "Oops, there is nothing to show here.";
        taskList.clickOnPodsTabIcon();
        assert actual.equals(expected);
    }
}