package entities;

import java.util.*;
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

    final private String POD_NAME = "Pod Name";
    final private String START_DATE = "Start Date";
    final private String DUE_DATE = "Due Date";
    final private String BUDGET_TIME = "Budget Time";
    final private String CLIENT = "Client";
    final private String POD_LEAD = "Project Lead";
    final private String POD_COLOR = "Color";
    final private String DESCRIPTION = "Description";

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

    public void setPodInformation(final Map<String, String> podInformation) {
        HashMap<String, Runnable> strategyMap = composeStrategyMap(podInformation);
        podInformation.keySet().forEach(key -> strategyMap.get(key).run());
        modifiedPodFields.addAll(podInformation.keySet());
    }

    private HashMap<String, Runnable> composeStrategyMap(Map<String, String> podInformation) {
        HashMap<String, Runnable> strategyMap = new HashMap<>();

        strategyMap.put(POD_NAME, () -> setPodName(podInformation.get(POD_NAME)));
        strategyMap.put(START_DATE, () ->  setStartDate(podInformation.get(START_DATE)));
        strategyMap.put(DUE_DATE, () -> setDueDate(podInformation.get(DUE_DATE)));
        strategyMap.put(BUDGET_TIME, () -> setBudgetedTime(podInformation.get(BUDGET_TIME)));
        strategyMap.put(CLIENT, () -> setClient(podInformation.get(CLIENT)));
        strategyMap.put(POD_LEAD, () -> setPodLead(podInformation.get(POD_LEAD)));
        strategyMap.put(POD_COLOR, () -> setPodColor(podInformation.get(POD_COLOR)));
        strategyMap.put(DESCRIPTION, () -> setDescription(podInformation.get(DESCRIPTION)));
        return strategyMap;
    }

    public HashMap<String, String> getPodInformation() {
        HashMap<String, String> values = new HashMap<>();
        HashMap<String, Supplier> strategyMap = composeStrategyMapGet();
        for (String field: modifiedPodFields) {
            values.put(field, strategyMap.get(field).get().toString());
        }
        return values;
    }

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
