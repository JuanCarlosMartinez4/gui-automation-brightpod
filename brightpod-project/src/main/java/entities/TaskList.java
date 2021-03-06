package entities;

import brightpod.constants.TaskListConstant;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class TaskList {
    private String name;
    private String description;
    private boolean isVisibleToClients;

    private Set<String> modifiedTaskListFields = new HashSet<>();

    /**
     * Gets Task List name.
     * @return Task List name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets Task List description.
     * @return Task List description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets if is visible to client.
     * @return boolean value.
     */
    public boolean isVisibleToClients() {
        return isVisibleToClients;
    }

    /**
     * Sets Task List name.
     * @param name value.
     */
    private void setName(final String name) {
        this.name = name;
    }

    /**
     * Sets Task List description.
     * @param description value.
     */
    private void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Sets visible to client.
     * @param visibleToClients value.
     */
    private void setVisibleToClients(final boolean visibleToClients) {
        isVisibleToClients = visibleToClients;
    }

    public void setTaskListInformation(final Map<String, String> taskListInformation) {
        HashMap<String, Runnable> strategyMap = composeStrategyMap(taskListInformation);
        taskListInformation.keySet().forEach(key -> strategyMap.get(key).run());
        modifiedTaskListFields.addAll(taskListInformation.keySet());
    }

    /**
     * Visits all setter methods of task list.
     * @param taskListInformation map.
     * @return map of visited setter methods.
     */
    private HashMap<String, Runnable> composeStrategyMap(Map<String, String> taskListInformation) {
        HashMap<String, Runnable> strategyMap = new HashMap<>();

        strategyMap.put(TaskListConstant.NAME, () -> setName(taskListInformation.get(TaskListConstant.NAME)));
        strategyMap.put(TaskListConstant.DESCRIPTION, () ->
            setDescription(taskListInformation.get(TaskListConstant.DESCRIPTION)));
        strategyMap.put(TaskListConstant.IS_VISIBLE, () ->
            setVisibleToClients(Boolean.parseBoolean(taskListInformation.get(TaskListConstant.IS_VISIBLE))));
        return strategyMap;
    }

    /**
     * Gets task list information.
     * @return task list values.
     */
    public HashMap<String, String> getTaskListInformation() {
        HashMap<String, String> taskListValues = new HashMap<>();
        HashMap<String, Supplier> strategyMap = composeStrategyMapGet();
        for (String field : modifiedTaskListFields) {
            taskListValues.put(field, strategyMap.get(field).get().toString());
        }
        return taskListValues;
    }

    /**
     * Visits all getter methods of task list.
     * @return map of visited get methods.
     */
    private HashMap<String, Supplier> composeStrategyMapGet() {
        HashMap<String, Supplier> strategyMap = new HashMap<>();
        strategyMap.put(TaskListConstant.NAME, () -> getName());
        strategyMap.put(TaskListConstant.DESCRIPTION, () -> getDescription());
        strategyMap.put(TaskListConstant.IS_VISIBLE, () -> Boolean.toString(isVisibleToClients()));
        return strategyMap;
    }
}
