package at.ventocom.minishop.task;

import java.util.Map;

import at.ventocom.minishop.purchase.Purchase;

/**
 * Abstract {@link Task} implementation containing purchase data and shared task variables which can
 * be used to transfer task-dependent data between different task runs.
 *
 */
public abstract class AbstractTask implements Task {
    /**
     * Purchase data holder object.
     */
    private final Map<Integer, Purchase> purchaseData;
    /**
     * {@link Map} containing data values produced by tasks should be shared with other task
     * instances.
     */
    private final Map<String, String> sharedTaskVariables;

    /**
     * Default string format of the task header text.
     */
    protected static final String TASK_HEADER_FORMAT = "%d. feladat";

    /**
     * Ctor.
     *
     * @param purchaseData
     *            purchase data object
     * @param sharedTaskVariables
     *            {@link Map} of task variables
     */
    public AbstractTask(
            final Map<Integer, Purchase> purchaseData,
            final Map<String, String> sharedTaskVariables) {
        super();
        this.purchaseData = purchaseData;
        this.sharedTaskVariables = sharedTaskVariables;
    }

    /**
     * Returns the {@link Map} containing all details of {@link Purchase} objects.
     *
     * @return {@link Map} of purchase data
     */
    protected final Map<Integer, Purchase> getPurchaseData() {
        return purchaseData;
    }

    /**
     * Return the {@link Map} of shared task variables.
     *
     * @return {@link Map} of task variables
     */
    protected final Map<String, String> getSharedTaskVariables() {
        return sharedTaskVariables;
    }

    public final void run() {
        System.out.println(getTaskHeader());
        doTask();
        System.out.println();
    }

    public String getTaskHeader() {
        return String.format(TASK_HEADER_FORMAT, getTaskNumber());
    }

    /**
     * Performs task operations.
     */
    protected abstract void doTask();
}
