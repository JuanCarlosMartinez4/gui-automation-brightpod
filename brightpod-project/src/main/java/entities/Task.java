package entities;

import brightpod.constants.TaskConstant;
import utils.Helper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class Task {
    private String taskName;
    private String member;
    private String dueDate;
    private boolean HighPriority;
    private String taskListName;

    private Set<String> modifiedTaskFields = new HashSet<>();

    /**
     * Gets Task name.
     * @return Task name.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Gets Member name.
     * @return Member name.
     */
    public String getMember() {
        return member;
    }

    /**
     * Gets Due Date value.
     * @return Due Date value.
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     * Gets if Task is High Priority.
     * @return boolean value.
     */
    public boolean isHighPriority() {
        return HighPriority;
    }

    /**
     * Gets Task List Name.
     * @return Task List Name value.
     */
    public String getTaskListName() {
        return taskListName;
    }

    /**
     * Sets Task name.
     * @param taskName value.
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Sets Member name.
     * @param member value.
     */
    public void setMember(String member) {
        this.member = member;
    }

    /**
     * Sets Due Date value.
     * @param dueDate value.
     */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Sets High Priority value.
     * @param highPriority value.
     */
    public void setHighPriority(boolean highPriority) {
        HighPriority = highPriority;
    }

    /**
     * Sets Task List Name.
     * @param taskListName value.
     */
    public void setTaskListName(String taskListName) {
        this.taskListName = taskListName;
    }

    public void setTaskInformation(final Map<String, String> taskInformation) {
        Map<String, String> currentTaskInformation = new HashMap<>(taskInformation);
        if (taskInformation.get(TaskConstant.DUE_DATE) != null) {
            currentTaskInformation.put(TaskConstant.DUE_DATE,
                Helper.formatDate(taskInformation.get(TaskConstant.DUE_DATE)));
        }

        HashMap<String, Runnable> strategyMap = composeStrategyMap(currentTaskInformation);
        currentTaskInformation.keySet().forEach(key -> strategyMap.get(key).run());
        modifiedTaskFields.addAll(currentTaskInformation.keySet());
    }

    private HashMap<String, Runnable> composeStrategyMap(Map<String, String> taskInformation) {
        HashMap<String, Runnable> strategyMap = new HashMap<>();

        strategyMap.put(TaskConstant.TASK_NAME, () -> setTaskName(taskInformation.get(TaskConstant.TASK_NAME)));
        strategyMap.put(TaskConstant.MEMBER, () -> setMember(taskInformation.get(TaskConstant.MEMBER)));
        strategyMap.put(TaskConstant.DUE_DATE, () -> setDueDate(taskInformation.get(TaskConstant.DUE_DATE)));
        strategyMap.put(TaskConstant.HIGH_PRIORITY, () ->
                setHighPriority(Boolean.parseBoolean(taskInformation.get(TaskConstant.HIGH_PRIORITY))));
        return strategyMap;
    }

    public HashMap<String, String> getTaskInformation() {
        HashMap<String, String> taskValues = new HashMap<>();
        HashMap<String, Supplier> strategyMap = composeStrategyMapGet();

        for (String field : modifiedTaskFields) {
            taskValues.put(field, strategyMap.get(field).get().toString());
        }
        return taskValues;
    }

    private HashMap<String, Supplier> composeStrategyMapGet() {
        HashMap<String, Supplier> strategyMap = new HashMap<>();
        strategyMap.put(TaskConstant.TASK_NAME, () -> getTaskName());
        strategyMap.put(TaskConstant.MEMBER, () -> getMember());
        strategyMap.put(TaskConstant.DUE_DATE, () -> getDueDate());
        strategyMap.put(TaskConstant.HIGH_PRIORITY, () -> Boolean.toString(isHighPriority()));
        return strategyMap;
    }
}
