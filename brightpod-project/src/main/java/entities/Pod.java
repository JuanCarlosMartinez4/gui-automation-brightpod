package entities;

import utils.Helper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class Pod {
    private String podName;
    private String startDate;
    private String dueDate;
    private String budgetedTime;
    private String client;
    private String podLead;
    private String podColor;
    private String description;

    private Set<String> modifiedPodFields = new HashSet<>();

    final static private String POD_NAME = "Pod Name";
    final static private String START_DATE = "Start Date";
    final static private String DUE_DATE = "Due Date";
    final static private String BUDGET_TIME = "Budget Time";
    final static private String CLIENT = "Client";
    final static private String POD_LEAD = "Project Lead";
    final static private String POD_COLOR = "Color";
    final static private String DESCRIPTION = "Description";

    /**
     * Gets pod name.
     * @return pod name.
     */
    public String getPodName() {
        return podName;
    }

    /**
     * Gets Start Date.
     * @return Start Date.
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Gets Due Date.
     * @return Due Date.
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     * Gets Budgeted Time.
     * @return Budgeted Time.
     */
    public String getBudgetedTime() {
        return budgetedTime;
    }

    /**
     * Gets Client name.
     * @return Client name.
     */
    public String getClient() {
        return client;
    }

    /**
     * Gets Pod Lead Name.
     * @return Pod Lead Name.
     */
    public String getPodLead() {
        return podLead;
    }

    /**
     * Gets Pod color.
     * @return Pod color.
     */
    public String getPodColor() {
        return podColor;
    }

    /**
     * Gets pod Description.
     * @return pod Description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets modified pod fields.
     * @return a list of key.
     */
    public Set<String> getModifiedPodFields() {
        return modifiedPodFields;
    }

    /**
     * Sets Pot Name.
     * @param podName value.
     */
    private void setPodName(final String podName) {
        this.podName = podName;
    }

    /**
     * Sets Start Date.
     * @param startDate value.
     */
    private void setStartDate(final String startDate) {
        this.startDate = startDate;
    }

    /**
     * Sets Due Date.
     * @param dueDate value.
     */
    private void setDueDate(final String dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Sets Budgeted Time.
     * @param budgetedTime value.
     */
    private void setBudgetedTime(final String budgetedTime) {
        this.budgetedTime = budgetedTime;
    }

    /**
     * Sets Client name.
     * @param client name value.
     */
    private void setClient(final String client) {
        this.client = client;
    }

    /**
     * Sets Pod Lead name.
     * @param podLead name value.
     */
    private void setPodLead(final String podLead) {
        this.podLead = podLead;
    }

    /**
     * Sets Pod Color value.
     * @param podColor value.
     */
    private void setPodColor(final String podColor) {
        this.podColor = podColor;
    }

    /**
     * Sets pod Description value.
     * @param description value.
     */
    private void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Sets pod information values.
     * @param podInformation map.
     */
    public void setPodInformation(final Map<String, String> podInformation) {
        Map<String, String> currentPodInformation = new HashMap<>(podInformation);
        if (podInformation.get(START_DATE) != null)
            currentPodInformation.put(START_DATE, Helper.formatDate(podInformation.get(START_DATE)));
        if (podInformation.get(DUE_DATE) != null)
            currentPodInformation.put(DUE_DATE, Helper.formatDate(podInformation.get(DUE_DATE)));
        HashMap<String, Runnable> strategyMap = composeStrategyMap(currentPodInformation);
        currentPodInformation.keySet().forEach(key -> strategyMap.get(key).run());
        modifiedPodFields.addAll(currentPodInformation.keySet());
    }

    /**
     * Allows to visit all setter methods.
     * @param currentPodInformation map.
     * @return map of visited methods.
     */
    private HashMap<String, Runnable> composeStrategyMap(Map<String, String> currentPodInformation) {
        HashMap<String, Runnable> strategyMap = new HashMap<>();

        strategyMap.put(POD_NAME, () -> setPodName(currentPodInformation.get(POD_NAME)));
        strategyMap.put(START_DATE, () ->  setStartDate(currentPodInformation.get(START_DATE)));
        strategyMap.put(DUE_DATE, () -> setDueDate(currentPodInformation.get(DUE_DATE)));
        strategyMap.put(BUDGET_TIME, () -> setBudgetedTime(currentPodInformation.get(BUDGET_TIME)));
        strategyMap.put(CLIENT, () -> setClient(currentPodInformation.get(CLIENT)));
        strategyMap.put(POD_LEAD, () -> setPodLead(currentPodInformation.get(POD_LEAD)));
        strategyMap.put(POD_COLOR, () -> setPodColor(currentPodInformation.get(POD_COLOR)));
        strategyMap.put(DESCRIPTION, () -> setDescription(currentPodInformation.get(DESCRIPTION)));
        return strategyMap;
    }

    /**
     * Gets pod information.
     * @return map of pod values.
     */
    public HashMap<String, String> getPodInformation() {
        HashMap<String, String> values = new HashMap<>();
        HashMap<String, Supplier> strategyMap = composeStrategyMapGet();
        for (String field: modifiedPodFields) {
            values.put(field, strategyMap.get(field).get().toString());
        }
        return values;
    }

    /**
     * Visits all getter methods.
     * @return map of visited methods values.
     */
    private HashMap<String, Supplier> composeStrategyMapGet() {
        HashMap<String, Supplier> strategyMap = new HashMap<>();

        strategyMap.put(POD_NAME, () -> getPodName());
        strategyMap.put(START_DATE, () ->  getStartDate());
        strategyMap.put(DUE_DATE, () -> getDueDate());
        strategyMap.put(BUDGET_TIME, () -> getBudgetedTime());
        strategyMap.put(CLIENT, () -> getClient());
        strategyMap.put(POD_LEAD, () -> getPodLead());
        strategyMap.put(POD_COLOR, () -> getPodColor());
        strategyMap.put(DESCRIPTION, () -> getDescription());
        return strategyMap;
    }
}
