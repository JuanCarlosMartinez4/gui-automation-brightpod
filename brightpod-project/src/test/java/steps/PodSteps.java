package steps;

import brightpod.PodsPage;
import brightpod.SettingTextLink;
import brightpod.TaskListPage;
import brightpod.FormPodPage;
import brightpod.NewPodModal;
import brightpod.PageTransporter;
import brightpod.MenuNavbar;
import brightpod.LogoutPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import entities.Context;
import entities.Pod;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class PodSteps {

    // Pages
    private PodsPage podsPage;
    private TaskListPage taskList;
    private SettingTextLink setting;
    private FormPodPage formPod;
    private LogoutPage logoutPage;

    // Modals
    private NewPodModal newPodModal;

    // Entities
    Context context;
    Pod pod;

    // Pod values
    private Map<String, String> actualPodValues;

    public PodSteps(final Context context) {
        this.context = context;
        this.pod = context.getPod();
    }

    @Then("^The application displays \"([^\"]*)\" page$")
    public void theApplicationDisplaysPage(String page) {
        PageTransporter.goToUrl(page);
    }

    @When("Create a Pod with the following values$")
    public void createAPodWithTheFollowingValues(final Map<String, String> podInformation) {
        pod.setPodInformation(podInformation);
        podsPage = new PodsPage();
        newPodModal = podsPage.clickNewPodButton();
        formPod = newPodModal.createNewPod();
        taskList = formPod.createNewPod(pod, podInformation.keySet());
    }

    @And("^Pod should contains$")
    public void podShouldContains() {
        setting = new SettingTextLink();
        setting.editPod();
        actualPodValues = new HashMap<>();
        actualPodValues = formPod.getPodInformation(pod.getPodInformation());
        for (String key: actualPodValues.keySet()) {
            Assert.assertEquals(key + ":: ", pod.getPodInformation().get(key),
                    actualPodValues.get(key));
        }
    }

    @When("^Search pod by name \"([^\"]*)\"$")
    public void searchPodByName(final String podName) {
        StepUtil.searchElement(podName);
    }

    @When("^Remove pod$")
    public void removePod() {
        setting = new SettingTextLink();
        setting.archivePod();
        MenuNavbar navbar = new MenuNavbar();
        navbar.logout();
        logoutPage = new LogoutPage();
        logoutPage.returnInitPage();
    }

    @When("^Edit a Pod with the following$")
    public void editAPodWithTheFollowing(final Map<String, String> podInformation) {
        setting = new SettingTextLink();
        setting.editPod();
        pod.setPodInformation(podInformation);
        taskList = formPod.updatePod(pod, podInformation.keySet());
    }
}
