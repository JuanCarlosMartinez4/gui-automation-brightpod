package com.brightpod.selectsite.steps;

import brightpod.PodsPage;
import brightpod.SearchElement;
import brightpod.SettingTextLink;
import brightpod.TaskListPage;
import brightpod.FormPodPage;
import brightpod.NewPodModal;
import brightpod.LogoutPage;

import entities.Context;
import entities.Pod;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class PodSteps {

    // Pages
    private PodsPage podsPage;
    private TaskListPage taskListPage;
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

    @When("Creates a Pod with the following values$")
    public void createAPodWithTheFollowingValues(final Map<String, String> podInformation) {
        pod.setPodInformation(podInformation);
        podsPage = new PodsPage();
        newPodModal = podsPage.clickNewPodButton();
        formPod = newPodModal.createNewPod();
        taskListPage = formPod.createNewPod(pod, podInformation.keySet());
    }

    @Then("^Pod should contains input data values$")
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

    @When("^Searches pod by name \"([^\"]*)\"$")
    public void searchPodByName(final String podName) {
        StepUtil.searchElement(podName);
    }

    @When("^Removes pod$")
    public void removePod() {
        setting = new SettingTextLink();
        setting.archivePod();
    }

    @When("^Edits a Pod with the following$")
    public void editAPodWithTheFollowing(final Map<String, String> podInformation) {
        setting = new SettingTextLink();
        setting.editPod();
        pod.setPodInformation(podInformation);
        taskListPage = formPod.updatePod(pod, podInformation.keySet());
    }

    @Then("The {string} Pod should not exist")
    public void thePodShouldNotExist(final String podName) {
        SearchElement searchElement = new SearchElement();
        String actual = searchElement.verifyDeletedElement(podName);
        String expected = "Oops, there is nothing to show here.";
        assert actual.equals(expected);
    }
}
