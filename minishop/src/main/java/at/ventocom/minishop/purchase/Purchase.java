package at.ventocom.minishop.purchase;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Holding data of a purchase involving items and number of items bought, total value of the items
 * etc.
 *
 */
public class Purchase {
    /**
     * Sequence number of the purchase.
     */
    private int purchaseNumber;
    /**
     * Total number of distinct items purchased.
     */
    private int purchaseItemCount;
    /**
     * Total value of the purchase.
     */
    private BigDecimal totalValue;
    /**
     * {@link Map} containing the number of purchased items of each item type in the current
     * purchase.
     */
    private final Map<String, Integer> itemContainer = new HashMap<String, Integer>(8);

    /**
     * Ctor.
     */
    public Purchase() {
        super();
    }

    public int getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setPurchaseNumber(final int purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }

    public int getPurchaseItemCount() {
        return purchaseItemCount;
    }

    public void setPurchaseItemCount(final int purchaseItemCount) {
        this.purchaseItemCount = purchaseItemCount;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(final BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public Map<String, Integer> getItemContainer() {
        return itemContainer;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
