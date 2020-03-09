package com.brightpod.selectsite.steps;

import brightpod.PodsPage;
import brightpod.ScreenShot;
import brightpod.SearchPod;
import brightpod.SettingTextLink;
import brightpod.TaskListPage;
import brightpod.FormPodPage;
import brightpod.NewPodModal;
import brightpod.PageTransporter;
import brightpod.LogoutPage;
import entities.Context;
import entities.Pod;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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
            ScreenShot.captureScreenShot(key);
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
    }

    @When("^Edit a Pod with the following$")
    public void editAPodWithTheFollowing(final Map<String, String> podInformation) {
        setting = new SettingTextLink();
        setting.editPod();
        pod.setPodInformation(podInformation);
        taskList = formPod.updatePod(pod, podInformation.keySet());
    }

    @Then("The {string} Pod should not exist")
    public void thePodShouldNotExist(final String podName) {
        SearchPod searchPod = new SearchPod();
        String actual = searchPod.verifyDeletedElement(podName);
        String expected = "Oops, there is nothing to show here.";
        assert actual.equals(expected);
        ScreenShot.captureScreenShot(actual);

    }
}
