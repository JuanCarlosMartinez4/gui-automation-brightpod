package brightpod;

import core.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class AddTaskPageTest {
    private String podName = "Empty Pod1";
    private HashMap<String, String> texts;
    private String listName = "My tasks";
    private LoginPage loginPage;
    private PodsPage podsPage;
    private NewPodModal podsModal;
    private FormPodPage formPod;
    private TaskListPage taskList;
    private AddTaskPage addTask;

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

        String listDescription = "This tasks are for week";
        boolean isVisible = true;
        taskList = new TaskListPage();
//        taskList = taskList.addTaskListInformation(listName, listDescription, isVisible);
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
    public void createNewTask_returnsNewTask() {
        String taskName = "my new task";
        String memberName = "juan martinez (Pod Lead)";
        HashMap<String, String> expected = new HashMap<>();
        expected.put("name", taskName);
        expected.put("member", "juan martinez");
        addTask = new AddTaskPage();
//        addTask = addTask.createNewTask(listName, taskName, memberName);
        TaskPopup taskPopup = addTask.clickOnTaskNameLink(taskName);
//        HashMap<String, String> actual = taskPopup.getFieldsText();
//        for (String key : actual.keySet()) {
//            Assert.assertEquals("message: ", expected.get(key), actual.get(key));
//        }
    }

    @Test
    public void removeTask_removedTask() {
        String taskName = "myNewTask";
        String memberName = "juan martinez (Pod Lead)";
        AddTaskPage addTask = new AddTaskPage();
//        addTask.createNewTask(listName, taskName, memberName);
        SearchElement search = new SearchElement();
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