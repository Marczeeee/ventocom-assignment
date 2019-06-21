package at.ventocom.minishop.task.impl;

import java.util.Map;
import java.util.Map.Entry;

import at.ventocom.minishop.purchase.Purchase;
import at.ventocom.minishop.task.AbstractTask;
import at.ventocom.minishop.task.TaskConstants;

/**
 * Task number five. Finds out the item given by the user when was bought the first and the last
 * time, and how many times was bought.
 *
 */
public class Task5 extends AbstractTask {
    /**
     * Ctor.
     *
     * @param purchaseData
     *            purchase data object
     * @param sharedTaskVariables
     *            {@link Map} of task variables
     */
    public Task5(
            final Map<Integer, Purchase> purchaseData,
            final Map<String, String> sharedTaskVariables) {
        super(purchaseData, sharedTaskVariables);
    }

    public int getTaskNumber() {
        return 5;
    }

    @Override
    protected void doTask() {
        int firstPurchaseNr = Integer.MAX_VALUE;
        int lastPurchaseNr = Integer.MIN_VALUE;
        int totalPurchasedCount = 0;
        final String inputItemName = getSharedTaskVariables().get(TaskConstants.ITEM_NAME_VAR_KEY);
        for (final Entry<Integer, Purchase> purchaseEntry : getPurchaseData().entrySet()) {
            if (purchaseEntry.getValue().getItemContainer().containsKey(inputItemName)) {
                final int purchaseNr = purchaseEntry.getKey();
                if (purchaseNr < firstPurchaseNr) {
                    firstPurchaseNr = purchaseNr;
                }
                if (purchaseNr > lastPurchaseNr) {
                    lastPurchaseNr = purchaseNr;
                }
                totalPurchasedCount +=
                        purchaseEntry.getValue().getItemContainer().get(inputItemName);
            }
        }
        System.out.println(String.format("Az első vásárlás sorszáma: %d", firstPurchaseNr));
        System.out.println(String.format("Az utolsó vásárlás sorszáma: %d", lastPurchaseNr));
        System.out.println(String.format("%d vásárlás során vettek belőle.", totalPurchasedCount));
    }
}
