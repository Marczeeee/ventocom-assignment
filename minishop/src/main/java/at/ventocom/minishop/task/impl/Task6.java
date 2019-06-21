package at.ventocom.minishop.task.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Map;

import at.ventocom.minishop.PaymentCalculator;
import at.ventocom.minishop.purchase.Purchase;
import at.ventocom.minishop.task.AbstractTask;
import at.ventocom.minishop.task.TaskConstants;

/**
 * Task number six. Calculates and prints the amount of payment when paying for a certain number of
 * items.
 *
 */
public class Task6 extends AbstractTask {
    /**
     * Ctor.
     *
     * @param purchaseData
     *            purchase data object
     * @param sharedTaskVariables
     *            {@link Map} of task variables
     */
    public Task6(
            final Map<Integer, Purchase> purchaseData,
            final Map<String, String> sharedTaskVariables) {
        super(purchaseData, sharedTaskVariables);
    }

    public int getTaskNumber() {
        return 6;
    }

    @Override
    protected void doTask() {
        final String purchasedItemCountString =
                getSharedTaskVariables().get(TaskConstants.PURCHASED_ITEM_COUNT_VAR_KEY);
        final int purchasedItemCount = Integer.parseInt(purchasedItemCountString);
        final PaymentCalculator paymentCalculator = new PaymentCalculator();
        final BigDecimal paymentTotal = paymentCalculator.getTotalValueOfNItem(purchasedItemCount);
        final DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(0);
        df.setGroupingUsed(false);
        System.out.println(String.format("%d darab vételekor fizetendő: %s", purchasedItemCount,
                df.format(paymentTotal)));
    }
}
