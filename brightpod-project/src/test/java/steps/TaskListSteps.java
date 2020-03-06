package steps;

import brightpod.AddTaskPage;
import brightpod.SearchPod;
import brightpod.TaskListPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;

import entities.Context;
import entities.Pod;
import entities.TaskList;

import org.junit.Assert;

import java.util.Map;

public class TaskListSteps {

    private Context context;
    private Pod pod;
    private TaskList taskList;
    private AddTaskPage addTasKPage;

    // Pages
    private TaskListPage taskListPage;

    // Task List values.
    private Map<String, String> actualTaskListValues;

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

        actualTaskListValues = taskListPage.getCreatedTaskListInformation(taskListInformation.get("name"),
                taskListInformation.get("description"));
    }

    @And("^Verify TaskList values$")
    public void verifyTaskListValues() {
        for (String key : actualTaskListValues.keySet()) {
            Assert.assertEquals(key + ": ", taskList.getTaskListInformation().get(key),
                    actualTaskListValues.get(key));
        }
    }

    @When("^Remove taskList by name \"([^\"]*)\"$")
    public void removeTaskListByName(String listName) {
        taskListPage.removeTaskListSearched(listName);
        SearchPod search = new SearchPod();
        String actual = search.verifyDeletedElement(listName);
        String expected = "Oops, there is nothing to show here.";
        taskListPage.clickOnPodsTabIcon();
        assert actual.equals(expected);
    }

    @And("^Search taskList by name \"([^\"]*)\"$")
    public void searchTaskListByName(final String listName) {
        StepUtil.searchElement(listName);
    }

    @When("^Edit a TaskList with the following$")
    public void editATaskListWithTheFollowing(final Map<String, String> taskListInformation) {
        taskList.setTaskListInformation(taskListInformation);
        addTasKPage = new AddTaskPage();

        addTasKPage = taskListPage.updateTaskListInformation(taskList, taskListInformation.keySet());
    }
}
