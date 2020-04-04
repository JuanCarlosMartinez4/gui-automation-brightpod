package com.brightpod.steps;

import brightpod.AddTaskPage;
import brightpod.SearchElement;
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
    private SearchElement search;

    // Task List values.
    private HashMap<String, String> actualTaskListValues;

    public TaskListSteps(Context context) {
        this.context = context;
        this.pod = context.getPod();
        this.taskList = context.getTaskList();
    }

    @When("^I create a TaskList with the following values$")
    public void createTaskList(final Map<String, String> taskListInformation) {
        taskList.setTaskListInformation(taskListInformation);
        taskListPage = new TaskListPage();
        taskListPage = taskListPage.addTaskListInformation(taskList, taskListInformation.keySet());
    }

    @And("^The TaskList should contains inserted data values$")
    public void taskListShouldContains() {
        actualTaskListValues = taskListPage.getCreatedTaskListInformation(taskList.getTaskListInformation());
        for (String key : actualTaskListValues.keySet()) {
            Assert.assertEquals(key + ": ", taskList.getTaskListInformation().get(key),
                    actualTaskListValues.get(key));
        }
    }

    @When("^I remove the taskList by name \"([^\"]*)\"$")
    public void removeTaskList(String listName) {
        taskListPage.removeTaskListSearched(listName);
    }

    @And("^I search the taskList by name \"([^\"]*)\"$")
    public void searchTaskListByName(final String listName) {
        StepUtil.searchElement(listName);
    }

    @When("^I edit the TaskList with the following values$")
    public void editTaskList(final Map<String, String> taskListInformation) {
        taskListPage.editTaskListSearched(taskList.getName());
        taskList.setTaskListInformation(taskListInformation);
        addTasKPage = taskListPage.updateTaskListInformation(taskList, taskListInformation.keySet());
    }

    @Then("The TaskList {string} should not exist")
    public void taskListShouldNotTExist(final String taskListName) {
        search = new SearchElement();
        String actual = search.verifyDeletedElement(taskListName);
        String expected = "Oops, there is nothing to show here.";
        taskListPage.clickOnPodsTabIcon();
        assert actual.equals(expected);
    }
}
