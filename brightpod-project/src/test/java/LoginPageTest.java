import brightpod.AddTaskPage;
import brightpod.LoginPage;
import brightpod.LogoutPage;
import brightpod.NewPodModal;
import brightpod.PodsPage;
import brightpod.SettingTextLink;
import brightpod.TaskListPage;
import core.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class LoginPageTest {

    @Before
    public void initialize() {

    }

    @After
    public void TeardownTest() throws IOException {
        LogoutPage logoutPage = new LogoutPage();
        logoutPage.logout();
        WebDriverManager.getInstance().quitDriver();
    }

    @Test
    public void test_loginInBrightpod_homePage() throws IOException {
        String email = "juan.martinez.tacc11@gmail.com";
        String password = "passacction20B";
        LoginPage loginPage = new LoginPage();
        Object actual = loginPage.login(email, password);
        PodsPage podsPage = new PodsPage();
        assert actual.getClass() == podsPage.getClass();
    }

    @Test
    public void test_createANewPod_newPod() throws IOException {
        String email = "juan.martinez.tacc11@gmail.com";
        String password = "passacction20B";
        String newPodName = "Empty Pod1";
        LoginPage loginPage = new LoginPage();
        loginPage.login(email, password);
        NewPodModal podsModal = new NewPodModal();
        Object actual = podsModal.createNewPod(newPodName);
        PodsPage podsPage = new PodsPage();
        assert actual.getClass() == podsPage.getClass();
    }

    @Test
    public void test_createANewTaskList_newTaskList() throws IOException {
        String email = "juan.martinez.tacc11@gmail.com";
        String password = "passacction20B";
        String newPodName = "Empty Pod2";
        String listName = "My tasks";
        String listDescription = "This tasks are for week";
        LoginPage loginPage = new LoginPage();
        loginPage.login(email, password);
        NewPodModal podsModal = new NewPodModal();
        podsModal.createNewPod(newPodName);
        TaskListPage taskList = new TaskListPage();
        Object actual = taskList.addNewTaskList(listName, listDescription);
        PodsPage podsPage = new PodsPage();
        assert actual.getClass() == podsPage.getClass();
    }

    @Test
    public void test_createANewTask_newTask() throws IOException {
        String email = "juan.martinez.tacc11@gmail.com";
        String password = "passacction20B";
        String newPodName = "Empty Pod2";
        String listName = "My tasks";
        String listDescription = "This tasks are for week";
        String taskName = "my new task";
        LoginPage loginPage = new LoginPage();
        loginPage.login(email, password);
        NewPodModal podsModal = new NewPodModal();
        podsModal.createNewPod(newPodName);
        TaskListPage taskList = new TaskListPage();
        taskList.addNewTaskList(listName, listDescription);
        AddTaskPage addTask = new AddTaskPage();
        addTask.addNewTask(taskName);
    }

    @Test
    public void test_archiveAPod_podArchived() throws IOException {
        String email = "juan.martinez.tacc11@gmail.com";
        String password = "passacction20B";
        String newPodName = "Empty Pod3";
        LoginPage loginPage = new LoginPage();
        loginPage.login(email, password);
        NewPodModal podsModal = new NewPodModal();
        podsModal.createNewPod(newPodName);
        SettingTextLink setting = new SettingTextLink();
        setting.openSettings();
        setting.archivePod();
    }
}