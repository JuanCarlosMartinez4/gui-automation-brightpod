package com.brightpod.selectsite.steps;

import brightpod.AddTaskPage;
import brightpod.SearchPod;
import brightpod.TaskListPage;

import entities.Context;
import entities.Pod;
import entities.TaskList;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class TaskListSteps {

    private Context context;
    private Pod pod;
    private TaskList taskList;

    // Pages
    private TaskListPage taskListPage;
    private AddTaskPage addTasKPage;
    private SearchPod search;

    // Task List values.
    private HashMap<String, String> actualTaskListValues;

    public TaskListSteps(Context context) {
        this.context = context;
        this.pod = context.getPod();
        this.taskList = context.getTaskList();
    }

    @When("^Creates a TaskList with the following values$")
    public void createATaskListWithTheFollowingValues(final Map<String, String> taskListInformation) {
        taskList.setTaskListInformation(taskListInformation);
        taskListPage = new TaskListPage();
        taskListPage = taskListPage.addTaskListInformation(taskList, taskListInformation.keySet());
    }

    @And("^TaskList should contains input data values$")
    public void taskListShouldContains() {
        actualTaskListValues = taskListPage.getCreatedTaskListInformation(taskList.getTaskListInformation());
        for (String key : actualTaskListValues.keySet()) {
            Assert.assertEquals(key + ": ", taskList.getTaskListInformation().get(key),
                    actualTaskListValues.get(key));
        }
    }

    @When("^Removes taskList by name \"([^\"]*)\"$")
    public void removeTaskListByName(String listName) {
        taskListPage.removeTaskListSearched(listName);
    }

    @And("^Searches taskList by name \"([^\"]*)\"$")
    public void searchTaskListByName(final String listName) {
        StepUtil.searchElement(listName);
    }

    @When("^Edits a TaskList with the following values$")
    public void editATaskListWithTheFollowing(final Map<String, String> taskListInformation) {
        taskListPage.editTaskListSearched(taskList.getName());
        taskList.setTaskListInformation(taskListInformation);

        addTasKPage = taskListPage.updateTaskListInformation(taskList, taskListInformation.keySet());
    }

    @Then("TaskList {string} should not exist")
    public void taskListShouldNotTExist(final String taskListName) {
        search = new SearchPod();
        String actual = search.verifyDeletedElement(taskListName);
        String expected = "Oops, there is nothing to show here.";
        taskListPage.clickOnPodsTabIcon();
        assert actual.equals(expected);
    }
}
