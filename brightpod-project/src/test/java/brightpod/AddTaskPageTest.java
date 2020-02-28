package brightpod;

import core.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddTaskPageTest {

    @Before
    public void setUp() {
        String email = "juan.martinez.tacc11@gmail.com";
        String password = "passacction20B";
        LoginPage loginPage = new LoginPage();
        String actual = loginPage.login(email, password);
        String expected = "Create a New Pod";

        assert actual.equals(expected);
    }

    @After
    public void tearDown() {
        MenuNavbar navbar = new MenuNavbar();
        navbar.logout();
        LogoutPage logoutPage = new LogoutPage();
        logoutPage.logout();
        WebDriverManager.getInstance().quitDriver();
    }

    @Test
    public void createNewTask_returnsNewTask() {
        String newPodName = "Empty Pod2";
        String listName = "My tasks";
        String listDescription = "This tasks are for week";
        String taskName = "my new task";
        String memberName = "juan martinez (Pod Lead)";
        PodsPage podsPage = new PodsPage();
        podsPage.displayNewPodModal();
        NewPodModal podsModal = new NewPodModal();
        podsModal.createNewPod(newPodName);
        TaskListPage taskList = new TaskListPage();
        taskList.addNewTaskList(listName, listDescription);
        AddTaskPage addTask = new AddTaskPage();
        String actual = addTask.createNewTask(listName, taskName, memberName);

        assert actual.equals(taskName);
    }
}