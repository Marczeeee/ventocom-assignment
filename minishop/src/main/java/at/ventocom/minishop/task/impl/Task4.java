package at.ventocom.minishop.task.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import at.ventocom.minishop.purchase.Purchase;
import at.ventocom.minishop.task.AbstractTask;
import at.ventocom.minishop.task.TaskConstants;

/**
 * Task number four. Reads input from user through console interface.
 *
 */
public class Task4 extends AbstractTask {
    /**
     * Ctor.
     *
     * @param purchaseData
     *            purchase data object
     * @param sharedTaskVariables
     *            {@link Map} of task variables
     */
    public Task4(
            final Map<Integer, Purchase> purchaseData,
            final Map<String, String> sharedTaskVariables) {
        super(purchaseData, sharedTaskVariables);
    }

    public int getTaskNumber() {
        return 4;
    }

    @Override
    protected void doTask() {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Adja meg egy vásárlás sorszámát! ");
        try {
            final String purchaseNr = reader.readLine();
            getSharedTaskVariables().put(TaskConstants.PURCHASE_NR_VAR_KEY, purchaseNr);
            System.out.print("Adja meg egy árucikk nevét! ");
            final String itemName = reader.readLine();
            getSharedTaskVariables().put(TaskConstants.ITEM_NAME_VAR_KEY, itemName);
            System.out.print("Adja meg a vásárolt darabszámot! ");
            final String purchasedItemCount = reader.readLine();
            getSharedTaskVariables().put(TaskConstants.PURCHASED_ITEM_COUNT_VAR_KEY,
                    purchasedItemCount);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
