package entities;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class TaskList {
    private String name;
    private String description;
    private boolean isVisibleToClients;

    private static final String NAME = "Name";
    private static final String DESCRIPTION = "Description";
    private static final String IS_VISIBLE = "Is Visible";

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

    private HashMap<String, Runnable> composeStrategyMap(Map<String, String> taskListInformation) {
        HashMap<String, Runnable> strategyMap = new HashMap<>();

        strategyMap.put(NAME, () -> setName(taskListInformation.get(NAME)));
        strategyMap.put(DESCRIPTION, () -> setDescription(taskListInformation.get(DESCRIPTION)));
        strategyMap.put(IS_VISIBLE, () -> setVisibleToClients(Boolean.parseBoolean(taskListInformation.get(IS_VISIBLE))));
        return strategyMap;
    }

    public HashMap<String, String> getTaskListInformation() {
        HashMap<String, String> taskListValues = new HashMap<>();
        HashMap<String, Supplier> strategyMap = composeStrategyMapGet();
        for (String field : modifiedTaskListFields) {
            taskListValues.put(field, strategyMap.get(field).get().toString());
        }
        return taskListValues;
    }

    private HashMap<String, Supplier> composeStrategyMapGet() {
        HashMap<String, Supplier> strategyMap = new HashMap<>();
        strategyMap.put(NAME, () -> getName());
        strategyMap.put(DESCRIPTION, () -> getDescription());
        strategyMap.put(IS_VISIBLE, () -> Boolean.toString(isVisibleToClients()));
        return strategyMap;
    }
}
