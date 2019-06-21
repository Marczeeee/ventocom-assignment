package at.ventocom.minishop;

import java.math.BigDecimal;

/**
 * Helps calculating payment values of items.
 *
 */
public class PaymentCalculator {
    /** Price of the first item of a product. */
    private static final BigDecimal FIRST_ITEM_PRICE = BigDecimal.valueOf(500, 0);
    /** Price of the second item of a product. */
    private static final BigDecimal SECOND_ITEM_PRICE = BigDecimal.valueOf(450, 0);
    /** Price of the third and any more items of a product. */
    private static final BigDecimal ANY_OTHER_ITEM_PRICE = BigDecimal.valueOf(400, 0);

    /**
     * Returns the fixed price of an <i>nth</i> item.
     *
     * @param itemNr
     *            number of item (nth item)
     * @return Price of the item
     */
    public BigDecimal getPriceOfNthItem(final int itemNr) {
        BigDecimal itemPrice;
        switch (itemNr) {
            case 1:
                itemPrice = FIRST_ITEM_PRICE;
                break;
            case 2:
                itemPrice = SECOND_ITEM_PRICE;
                break;
            default:
                itemPrice = ANY_OTHER_ITEM_PRICE;
                break;
        }
        return itemPrice;
    }

    /**
     * Calculates the total value of <i>n</i> items.
     *
     * @param numberOfItems
     *            number of items
     * @return Total payment value of the items
     */
    public BigDecimal getTotalValueOfNItem(final int numberOfItems) {
        if (numberOfItems <= 0) {
            throw new IllegalArgumentException("Number of items can't be less than 1");
        }
        BigDecimal totalValue = BigDecimal.ZERO;
        for (int itemNr = 1; itemNr <= numberOfItems; itemNr++) {
            totalValue = totalValue.add(getPriceOfNthItem(itemNr));
        }
        return totalValue;
    }
}
