package entities;

import java.util.HashMap;
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

    private Map<String, String> podInformation;

    public void setPodInformation(final Map<String, String> podInformation) {
        HashMap<String, Runnable> strategyMap = composeStrategyMap(podInformation);
        podInformation.keySet().forEach(key -> strategyMap.get(key).run());
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

//    public Map<String, Supplier<String>> getPodInformation(final Map<String, String> podInformation) {
//        HashMap<String, Supplier<String>> strategySupplier = composeStrategyMapGet(podInformation);
//        strategySupplier.keySet().forEach(Key -> strategySupplier.get(Key).get());
//        return strategySupplier;
//        Supplier<Map<String, String>> supplier = () -> podInformation;
//        return supplier.get();
//    }

    public Map<String, Supplier<String>> getPodInformation2(final Set<String> podInformation) {
        HashMap<String, Supplier<String>> strategySupplier = composeStrategyMapGet(this);
        podInformation.forEach(value -> strategySupplier.get(value));
        return strategySupplier;
    }

    private HashMap<String, Supplier<String>> composeStrategyMapGet(Pod pod) {
        HashMap<String, Supplier<String>> strategyMap = new HashMap<>();

        strategyMap.put(POD_NAME, () -> pod.getPodName());
        strategyMap.put(START_DATE, () ->  pod.getStartDate());
        strategyMap.put(DUE_DATE, () -> pod.getDueDate());
        strategyMap.put(BUDGET_TIME, () -> pod.getBudgetedTime());
        strategyMap.put(CLIENT, () -> pod.getClient());
        strategyMap.put(POD_LEAD, () -> pod.getPodLead());
        strategyMap.put(POD_COLOR, () -> pod.getPodColor());
        strategyMap.put(DESCRIPTION, () -> pod.getDescription());
        return strategyMap;
    }
}
