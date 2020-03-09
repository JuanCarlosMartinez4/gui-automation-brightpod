package com.brightpod.selectsite.steps;

import brightpod.AddTaskPage;
import brightpod.TaskListPage;
import brightpod.TaskPopup;
import entities.Context;
import entities.Pod;
import entities.Task;
import entities.TaskList;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class TaskSteps {
    private Context context;
    private TaskList taskList;
    private Task task;

    // Pages
    private TaskListPage taskListPage;
    private AddTaskPage addTasKPage;

    // Task List values.
    private HashMap<String, String> actualTaskValues;

    public TaskSteps(Context context) {
        this.context = context;
        this.task = context.getTask();
        this.taskList = context.getTaskList();
    }

    @When("Create a Task with the following values")
    public void createATaskWithTheFollowingValues(final Map<String, String> taskInformation) {
        task.setTaskInformation(taskInformation);
        addTasKPage = new AddTaskPage();
//        addTasKPage = addTasKPage.addTaskInformation(task, taskInformation);
    }

    @Then("Task should contains")
    public void taskShouldContains() {
//        actualTaskValues = addTaskPage.getCreatedTaskInformation(task.getTaskInformation());
//        TaskPopup taskPopup = addTask.clickOnTaskNameLink(taskName);
//        HashMap<String, String> actual = taskPopup.getFieldsText();
        for (String key : actualTaskValues.keySet()) {
            Assert.assertEquals(key + ": ", task.getTaskInformation().get(key),
                    actualTaskValues.get(key));
        }
    }
}
