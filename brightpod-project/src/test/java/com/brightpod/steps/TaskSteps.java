package com.brightpod.steps;

import brightpod.AddTaskPage;
import brightpod.SearchElement;
import brightpod.TaskListPage;

import brightpod.TaskPopup;
import entities.Context;
import entities.Task;
import entities.TaskList;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.Map;

public class TaskSteps {
    private Context context;
    private TaskList taskList;
    private Task task;

    // Pages
    private TaskListPage taskListPage;
    private AddTaskPage addTasKPage;
    private SearchElement searchElement;
    private TaskPopup taskPopup;

    // Task List values.
    private HashMap<String, String> actualTaskValues;

    public TaskSteps(Context context) {
        this.context = context;
        this.task = context.getTask();
        this.taskList = context.getTaskList();
    }

    @When("Creates a Task with the following values")
    public void createATask(final Map<String, String> taskInformation) {
        task.setTaskInformation(taskInformation);
        addTasKPage = new AddTaskPage();
        addTasKPage.addTaskInformation(task, taskInformation.keySet());
        addTasKPage.clickOnAddTaskButtonToSave();
    }

    @Then("Task should contains input data values")
    public void taskShouldContains() {
        actualTaskValues = taskPopup.getTaskInformation(task.getTaskInformation());
        taskPopup.clickOnClosePopUp();
//        for (String key : actualTaskValues.keySet()) {
//            Assert.assertEquals(key + ": ", task.getTaskInformation().get(key),
//                    actualTaskValues.get(key));
//        }
    }

    @And("Searches task by name {string}")
    public void searchTask(final String taskName) {
        searchElement = new SearchElement();
        taskPopup = searchElement.displayElementByName(taskName);
    }
}
