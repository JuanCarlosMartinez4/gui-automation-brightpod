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
        String actual = loginPage.login(email, password);
        String expected = "Create a New Pod";

        assert actual.equals(expected);
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
        String actual = podsPage.activePodsTab();
        String expected = "Create a New Pod";

        assert actual.equals(expected);
    }

    @Test
    public void test_createANewPod_newPod() {

        String newPodName = "Empty Pod1";
        PodsPage podsPage = new PodsPage();
        String value = podsPage.displayNewPodModal();
        NewPodModal podsModal = new NewPodModal();
        String actual = podsModal.createNewPod(newPodName);

        assert actual.equals(newPodName);
    }

    @Test
    public void test_createANewTaskList_newTaskList() {
        String newPodName = "Empty Pod2";
        String listName = "My tasks";
        String listDescription = "This tasks are for week";
        PodsPage podsPage = new PodsPage();
        podsPage.displayNewPodModal();
        NewPodModal podsModal = new NewPodModal();
        podsModal.createNewPod(newPodName);
        TaskListPage taskList = new TaskListPage();
        String actual = taskList.addNewTaskList(listName, listDescription);

        assert actual.equals(listName);
    }

    @Test
    public void test_createANewTask_newTask() {
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

    @Test
    public void test_archiveAPod_podArchived() {
        String newPodName = "Empty Pod 001";
        PodsPage podsPage = new PodsPage();
        podsPage.displayNewPodModal();
        NewPodModal podsModal = new NewPodModal();
        String podName = podsModal.createNewPod(newPodName);
        SearchPod searchPod = new SearchPod();
        searchPod.searchPodByName(podName);
        SettingTextLink setting = new SettingTextLink();
        setting.archivePod();
        String actual = searchPod.verifyDeletedPod(podName);
        String expected = "Oops, there is nothing to show here.";
        assert actual.equals(expected);
    }

    @Test
    public void test_searchPodByName_returnPodFound() {
        String podName = "Empty Pod2";
        PodsPage podsPage = new PodsPage();
        podsPage.displayNewPodModal();
        NewPodModal podsModal = new NewPodModal();
        podsModal.createNewPod(podName);
        SearchPod searchPod = new SearchPod();
        String actual = searchPod.searchPodByName(podName);
        assert actual.equals(podName);
    }

    @Test
    public void test_goBackToPodsPage_returnPodsPage() {
        NewPodModal podsModal = new NewPodModal();
        Object actual = podsModal.goBackToPodsPage();
        PodsPage podsPage = new PodsPage();
        assert actual.getClass() == podsPage.getClass();
    }
}