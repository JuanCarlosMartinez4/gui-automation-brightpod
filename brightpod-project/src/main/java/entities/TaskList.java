package entities;

import java.util.HashMap;
import java.util.Map;

public class TaskList {
    private String name;
    private String description;
    private boolean isVisibleToClients;

    final private String NAME = "Name";
    final private String DESCRIPTION = "Description";
    final private String IS_VISIBLE = "Is Visible";

    private Map<String, String> taskListValues;

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
    }

    private HashMap<String, Runnable> composeStrategyMap(Map<String, String> taskListInformation) {
        HashMap<String, Runnable> strategyMap = new HashMap<>();

        strategyMap.put(NAME, () -> setName(taskListInformation.get(NAME)));
        strategyMap.put(DESCRIPTION, () ->  setDescription(taskListInformation.get(DESCRIPTION)));
        strategyMap.put(IS_VISIBLE, () -> setVisibleToClients(Boolean.parseBoolean(taskListInformation.get(IS_VISIBLE))));
        return strategyMap;
    }

    public Map<String, String> getTaskListInformation() {
        taskListValues = new HashMap<>();
        taskListValues.put("Name", getName());
        taskListValues.put("Description", getDescription());
        taskListValues.put("Is Visible", Boolean.toString(isVisibleToClients()));
        return taskListValues;
    }
}
