package at.ventocom.minishop.task.impl;

import java.util.Map;
import java.util.Map.Entry;

import at.ventocom.minishop.purchase.Purchase;
import at.ventocom.minishop.task.AbstractTask;
import at.ventocom.minishop.task.TaskConstants;

/**
 * Task number seven. Prints the number of items purchased in a certain purchase.
 *
 */
public class Task7 extends AbstractTask {
    /**
     * Ctor.
     *
     * @param purchaseData
     *            purchase data object
     * @param sharedTaskVariables
     *            {@link Map} of task variables
     */
    public Task7(
            final Map<Integer, Purchase> purchaseData,
            final Map<String, String> sharedTaskVariables) {
        super(purchaseData, sharedTaskVariables);
    }

    public int getTaskNumber() {
        return 7;
    }

    @Override
    protected void doTask() {
        final String purchasedNrString =
                getSharedTaskVariables().get(TaskConstants.PURCHASE_NR_VAR_KEY);
        final int purchaseNr = Integer.parseInt(purchasedNrString);
        final Purchase purchase = getPurchaseData().get(purchaseNr);
        for (final Entry<String, Integer> purchasedItemEntry : purchase.getItemContainer().entrySet()) {
            System.out.println(String.format("%d %s", purchasedItemEntry.getValue(),
                    purchasedItemEntry.getKey()));
        }
    }
}
