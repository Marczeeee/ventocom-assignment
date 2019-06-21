package at.ventocom.minishop.task.impl;

import java.util.Map;

import at.ventocom.minishop.purchase.Purchase;
import at.ventocom.minishop.task.AbstractTask;

/**
 * Task number two. Prints out the total number of payments.
 *
 */
public class Task2 extends AbstractTask {
    /**
     * Ctor.
     *
     * @param purchaseData
     *            purchase data object
     * @param sharedTaskVariables
     *            {@link Map} of task variables
     */
    public Task2(
            final Map<Integer, Purchase> purchaseData,
            final Map<String, String> sharedTaskVariables) {
        super(purchaseData, sharedTaskVariables);
    }

    public int getTaskNumber() {
        return 2;
    }

    @Override
    protected void doTask() {
        System.out.println("A fizetések száma: " + getPurchaseData().size());
    }
}
