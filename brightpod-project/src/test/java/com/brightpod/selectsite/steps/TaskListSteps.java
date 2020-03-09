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

    // Task List values.
    private HashMap<String, String> actualTaskListValues;

    public TaskListSteps(Context context) {
        this.context = context;
        this.pod = context.getPod();
        this.taskList = context.getTaskList();
    }

    @When("^Create a TaskList with the following values$")
    public void createATaskListWithTheFollowingValues(final Map<String, String> taskListInformation) {
        taskList.setTaskListInformation(taskListInformation);
        taskListPage = new TaskListPage();
        taskListPage = taskListPage.addTaskListInformation(taskList, taskListInformation.keySet());

//        actualTaskListValues = taskListPage.getCreatedTaskListInformation();
    }

    @And("^TaskList should contains$")
    public void taskListShouldContains() {
        actualTaskListValues = taskListPage.getCreatedTaskListInformation(taskList.getTaskListInformation());
        for (String key : actualTaskListValues.keySet()) {
            Assert.assertEquals(key + ": ", taskList.getTaskListInformation().get(key),
                    actualTaskListValues.get(key));
        }
    }

    @When("^Remove taskList by name \"([^\"]*)\"$")
    public void removeTaskListByName(String listName) {
        taskListPage.removeTaskListSearched(listName);

    }

    @And("^Search taskList by name \"([^\"]*)\"$")
    public void searchTaskListByName(final String listName) {
        StepUtil.searchElement(listName);
    }

    @When("^Edit a TaskList with the following values$")
    public void editATaskListWithTheFollowing(final Map<String, String> taskListInformation) {
        taskListPage.editTaskListSearched(taskList.getName());
        taskList.setTaskListInformation(taskListInformation);
//        addTasKPage = new AddTaskPage();

        addTasKPage = taskListPage.updateTaskListInformation(taskList, taskListInformation.keySet());
    }

    @Then("TaskList {string} should not exist")
    public void taskListShouldTExist(final String taskListName) {
        SearchPod search = new SearchPod();
        String actual = search.verifyDeletedElement(taskListName);
        String expected = "Oops, there is nothing to show here.";
        taskListPage.clickOnPodsTabIcon();
        assert actual.equals(expected);
    }
}
