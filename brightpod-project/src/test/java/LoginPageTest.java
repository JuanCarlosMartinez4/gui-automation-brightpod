import brightpod.AddTaskPage;
import brightpod.LoginPage;
import brightpod.LogoutPage;
import brightpod.MenuNavbar;
import brightpod.NewPodModal;
import brightpod.PodsPage;
import brightpod.SearchPod;
import brightpod.SettingTextLink;
import brightpod.TaskListPage;
import core.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginPageTest {

    @Before
    public void initialize() {
        String email = "juan.martinez.tacc11@gmail.com";
        String password = "passacction20B";
        LoginPage loginPage = new LoginPage();
        loginPage.login(email, password);
    }

    @After
    public void TeardownTest() {
        MenuNavbar navbar = new MenuNavbar();
        navbar.logout();
        LogoutPage logoutPage = new LogoutPage();
        logoutPage.logout();
        WebDriverManager.getInstance().quitDriver();
    }

    @Test
    public void test_loginInBrightpod_homePage() {
        PodsPage podsPage = new PodsPage();
        podsPage.activePodsTab();
    }

    @Test
    public void test_createANewPod_newPod() {

        String newPodName = "Empty Pod1";
        PodsPage podsPage = new PodsPage();
        podsPage.createNewPod();

        NewPodModal podsModal = new NewPodModal();
        podsModal.createNewPod(newPodName);
    }

    @Test
    public void test_createANewTaskList_newTaskList() {
        String newPodName = "Empty Pod2";
        String listName = "My tasks";
        String listDescription = "This tasks are for week";
        PodsPage podsPage = new PodsPage();
        podsPage.createNewPod();
        NewPodModal podsModal = new NewPodModal();
        podsModal.createNewPod(newPodName);
        TaskListPage taskList = new TaskListPage();
        Object actual = taskList.addNewTaskList(listName, listDescription);
    }

    @Test
    public void test_createANewTask_newTask() {
        String newPodName = "Empty Pod2";
        String listName = "My tasks";
        String listDescription = "This tasks are for week";
        String taskName = "my new task";
        NewPodModal podsModal = new NewPodModal();
        podsModal.createNewPod(newPodName);
        TaskListPage taskList = new TaskListPage();
        taskList.addNewTaskList(listName, listDescription);
        AddTaskPage addTask = new AddTaskPage();
        addTask.addNewTask(taskName);
        // It is not working
    }

    @Test
    public void test_archiveAPod_podArchived() {
        String newPodName = "Empty Pod3";
        NewPodModal podsModal = new NewPodModal();
        podsModal.createNewPod(newPodName);
        SettingTextLink setting = new SettingTextLink();
        setting.openSettings();
        setting.archivePod();
        // It is not working
    }

    @Test
    public void test_goBackToPodsPage_returnPodsPage() {
        NewPodModal podsModal = new NewPodModal();
        Object actual = podsModal.goBackToPodsPage();
        PodsPage podsPage = new PodsPage();
        assert actual.getClass() == podsPage.getClass();
    }

    @Test
    public void test_searchPodByName_returnPodFound() {
        String podName = "Empty Pod2";
        NewPodModal podsModal = new NewPodModal();
        podsModal.createNewPod(podName);
        SearchPod searchPod = new SearchPod();
        Object actual = searchPod.searchPodByName(podName);
        PodsPage podsPage = new PodsPage();
        assert actual.getClass() == podsPage.getClass();
    }
}