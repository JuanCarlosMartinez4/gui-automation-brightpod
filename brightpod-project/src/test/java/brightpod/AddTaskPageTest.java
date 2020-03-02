package brightpod;

import core.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class AddTaskPageTest {
    private String podName = "Empty Pod1";
    private HashMap<String, String> texts;
    private String listName = "My tasks";

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

        String listDescription = "This tasks are for week";
        boolean isVisible = true;
        TaskListPage taskList = new TaskListPage();
        taskList.addNewTaskList(listName, listDescription, isVisible);
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
    public void createNewTask_returnsNewTask() {
        String taskName = "my new task";
        String memberName = "juan martinez (Pod Lead)";
        HashMap<String, String> expected = new HashMap<>();
        expected.put("name", taskName);
        expected.put("member", "juan martinez (Pod Lead)");
        AddTaskPage addTask = new AddTaskPage();
        HashMap<String, String> actual = addTask.createNewTask(listName, taskName, memberName);

        for (String key: actual.keySet()) {
            Assert.assertEquals("message: ", expected.get(key), actual.get(key));
        }
    }

    @Test
    public void removeTask_removedTask() {
        String taskName = "myNewTask";
        String memberName = "juan martinez (Pod Lead)";
        AddTaskPage addTask = new AddTaskPage();
        addTask.createNewTask(listName, taskName, memberName);
        SearchPod search = new SearchPod();
        search.searchElementByName(taskName);
        TaskPopup taskPopup = new TaskPopup();
        taskPopup.removeTask();
        taskPopup.clickOnPodsTabIcon();
        String actual = search.verifyDeletedElement(taskName);
        String expected = "Oops, there is nothing to show here.";
        assert actual.equals(expected);
        taskPopup.clickOnPodsTabIcon();
    }
}