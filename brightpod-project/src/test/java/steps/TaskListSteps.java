package steps;

import brightpod.SearchPod;
import brightpod.TaskListPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class TaskListSteps {
    HashMap<String, String> actual;
    TaskListPage taskList;

    @When("^Create a TaskList with the following$")
    public void createATaskListWithTheFollowing(final Map<String, String> data) {
        taskList = new TaskListPage();
        taskList = taskList.addNewTaskList(data.get("name"), data.get("description"),
                Boolean.parseBoolean(data.get("isVisible")));
        actual = taskList.getFieldsText(data.get("name"), data.get("description"));

    }

    @And("^Verify TaskList values$")
    public void verifyTaskListValues(final Map<String, String> data) {
        HashMap<String, String> expected = new HashMap<>();
        expected.putAll(data);
        for (String key: actual.keySet()) {
            Assert.assertEquals("message: ", expected.get(key), actual.get(key));
        }
    }

    @When("^Remove taskList by name \"([^\"]*)\"$")
    public void removeTaskListByName(String listName) throws Throwable {
        taskList.removeTaskListSearched(listName);
        SearchPod search = new SearchPod();
        String actual = search.verifyDeletedElement(listName);
        String expected = "Oops, there is nothing to show here.";
        taskList.clickOnPodsTabIcon();
        assert actual.equals(expected);
    }
}
