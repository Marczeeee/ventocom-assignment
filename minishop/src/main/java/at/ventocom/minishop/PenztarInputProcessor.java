package at.ventocom.minishop;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import at.ventocom.minishop.purchase.Purchase;

/**
 * Processes purchases at a cash desk. Parses an input file line by line and transforms raw data
 * into processed and cumulated data.
 *
 */
public class PenztarInputProcessor {
    /** String indicating if a purchase finished. */
    private static final String PAYMENT_INDICATOR_STR = "F";
    /** Payment calculator helper object. */
    private static final PaymentCalculator PAYMENT_CALCULATOR = new PaymentCalculator();

    /**
     * Processes the input data file line by line. The data transformed into series of
     * {@link Purchase} objects.
     *
     * @param inputFile
     *            file containing input data
     * @return {@link Map} of all purchases contained in the input file. The key of the {@link Map}
     *         if the number of a purchase, the value is a {@link Purchase} object containing
     *         purchase details
     * @throws FileNotFoundException
     *             If the input {@link File} is <code>null</code>, doesn't exists of isn't a file.
     */
    public Map<Integer, Purchase> processInputFile(final File inputFile)
            throws FileNotFoundException {
        if (inputFile == null || !inputFile.exists() || !inputFile.isFile()) {
            throw new FileNotFoundException("Input file must be valid file and must exist!");
        }

        final Map<Integer, Purchase> purchaseMap = new HashMap<Integer, Purchase>();

        final Scanner sc = new Scanner(inputFile);

        int purchaseCounter = 1;
        int purchaseItemCount = 1;
        BigDecimal totalValue = BigDecimal.ZERO;
        Purchase purchase = new Purchase();
        try {
            while (sc.hasNextLine()) {
                final String line = sc.nextLine();

                if (StringUtils.equals(PAYMENT_INDICATOR_STR, line)) {
                    purchase.setPurchaseNumber(purchaseCounter);
                    purchase.setPurchaseItemCount(purchaseItemCount);
                    purchase.setTotalValue(totalValue);
                    purchaseMap.put(purchaseCounter, purchase);
                    purchaseCounter++;
                    purchaseItemCount = 0;
                    totalValue = BigDecimal.ZERO;
                    purchase = new Purchase();
                } else {
                    final BigDecimal itemPrice = processItemLine(line, purchase);
                    totalValue = totalValue.add(itemPrice);
                    purchaseItemCount++;
                }
            }
        }
        finally {
            sc.close();
        }
        return purchaseMap;
    }

    /**
     * Processes an input line containing data of a purchased item. Checks if there was already
     * purchased more of this kind of item, registers this item in the {@link Purchase} object and
     * calculates the price of the item based on possible previous purchased of the same item type.
     *
     * @param line
     *            single line containing item data
     * @param purchase
     *            current {@link Purchase} object
     * @return price of the purchased item contained in the processed line
     */
    private BigDecimal processItemLine(final String line, final Purchase purchase) {
        if (StringUtils.isNotBlank(line)) {
            if (!purchase.getItemContainer().containsKey(line)) {
                purchase.getItemContainer().put(line, 0);
            }

            Integer itemCount = purchase.getItemContainer().get(line);
            itemCount++;
            purchase.getItemContainer().put(line, itemCount);

            return PAYMENT_CALCULATOR.getPriceOfNthItem(itemCount);
        }
        throw new IllegalArgumentException("Item line can't be null or empty!");
    }
}
