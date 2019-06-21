package at.ventocom.minishop.task.impl;

import java.util.Map;

import at.ventocom.minishop.purchase.Purchase;
import at.ventocom.minishop.task.AbstractTask;

/**
 * Task number three. Prints out the number of purchased items bought by the first customer.
 *
 */
public class Task3 extends AbstractTask {
    /**
     * Ctor.
     *
     * @param purchaseData
     *            purchase data object
     * @param sharedTaskVariables
     *            {@link Map} of task variables
     */
    public Task3(
            final Map<Integer, Purchase> purchaseData,
            final Map<String, String> sharedTaskVariables) {
        super(purchaseData, sharedTaskVariables);
    }

    public int getTaskNumber() {
        return 3;
    }

    @Override
    protected void doTask() {
        final int itemCount = getPurchaseData().get(1).getPurchaseItemCount();
        System.out.println(
                String.format("Az első vásárló %d darab árucikket vásárolt.", itemCount));
    }
}
