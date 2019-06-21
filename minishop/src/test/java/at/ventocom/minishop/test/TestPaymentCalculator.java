package at.ventocom.minishop.test;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;

import at.ventocom.minishop.PaymentCalculator;

/**
 * Tests of {@link PaymentCalculator} class.
 *
 */
public class TestPaymentCalculator {
    @Test
    public void testTotalValueOfOneItem() {
        final PaymentCalculator paymentCalculator = new PaymentCalculator();
        final BigDecimal totalValue = paymentCalculator.getTotalValueOfNItem(1);
        Assert.assertEquals(totalValue, BigDecimal.valueOf(500));
    }

    @Test
    public void testTotalValueOfTwoItems() {
        final PaymentCalculator paymentCalculator = new PaymentCalculator();
        final BigDecimal totalValue = paymentCalculator.getTotalValueOfNItem(2);
        Assert.assertEquals(totalValue, BigDecimal.valueOf(950));
    }

    @Test
    public void testTotalValueOfThreeItems() {
        final PaymentCalculator paymentCalculator = new PaymentCalculator();
        final BigDecimal totalValue = paymentCalculator.getTotalValueOfNItem(3);
        Assert.assertEquals(totalValue, BigDecimal.valueOf(1350));
    }

    @Test
    public void testTotalValueOfMoreThanThreeItems() {
        final PaymentCalculator paymentCalculator = new PaymentCalculator();
        final Random random = new SecureRandom();
        final int randomItemCount = Math.abs(random.nextInt(1000));
        final int itemCount = randomItemCount + 3;
        final BigDecimal totalValue = paymentCalculator.getTotalValueOfNItem(itemCount);
        final BigDecimal calculatedTotalValue =
                BigDecimal.valueOf(500).add(BigDecimal.valueOf(450)).add(
                        BigDecimal.valueOf(400).multiply(BigDecimal.valueOf(randomItemCount + 1)));
        Assert.assertEquals(totalValue, calculatedTotalValue);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testTotalValueWithNegativeItemCount() {
        final PaymentCalculator paymentCalculator = new PaymentCalculator();
        paymentCalculator.getTotalValueOfNItem(Integer.MIN_VALUE);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testTotalValueWithZeroItemCount() {
        final PaymentCalculator paymentCalculator = new PaymentCalculator();
        paymentCalculator.getTotalValueOfNItem(0);
    }
}
