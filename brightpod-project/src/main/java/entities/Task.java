package entities;

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

    private static final String TASK_NAME = "Task Name";
    private static final String MEMBER = "Member";
    private static final String DUE_DATE = "Due Date";
    private static final String HIGH_PRIORITY = "High Priority";
    private static final String TASK_LIST_NAME = "Task List Name";

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
        if (taskInformation.get(DUE_DATE) != null)
            currentTaskInformation.put(DUE_DATE, Helper.formatDate(taskInformation.get(DUE_DATE)));
        HashMap<String, Runnable> strategyMap = composeStrategyMap(currentTaskInformation);
        currentTaskInformation.keySet().forEach(key -> strategyMap.get(key).run());
        modifiedTaskFields.addAll(currentTaskInformation.keySet());
    }

    private HashMap<String, Runnable> composeStrategyMap(Map<String, String> taskInformation) {
        HashMap<String, Runnable> strategyMap = new HashMap<>();

        strategyMap.put(TASK_NAME, () -> setTaskName(taskInformation.get(TASK_NAME)));
        strategyMap.put(MEMBER, () -> setMember(taskInformation.get(MEMBER)));
        strategyMap.put(DUE_DATE, () -> setDueDate(taskInformation.get(DUE_DATE)));
        strategyMap.put(HIGH_PRIORITY, () -> setHighPriority(Boolean.parseBoolean(taskInformation.get(HIGH_PRIORITY))));
//        strategyMap.put(TASK_LIST_NAME, () -> setTaskListName(taskInformation.get(TASK_LIST_NAME)));
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
        strategyMap.put(TASK_NAME, () -> getTaskName());
        strategyMap.put(MEMBER, () -> getMember());
        strategyMap.put(DUE_DATE, () -> getDueDate());
        strategyMap.put(HIGH_PRIORITY, () -> Boolean.toString(isHighPriority()));
        return strategyMap;
    }
}
