package at.ventocom.minishop.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import at.ventocom.minishop.PenztarInputProcessor;
import at.ventocom.minishop.purchase.Purchase;

/**
 * Tests of {@link PenztarInputProcessor} class.
 *
 */
public class TestPenztarInputProcessor {
    /** File containing test input data. */
    private File inputFile;
    /** File containing test results data. */
    private File resultFile;

    @BeforeClass
    public void before() {
        final ClassLoader classLoader = getClass().getClassLoader();
        inputFile = FileUtils.toFile(classLoader.getResource("penztar.txt"));
        resultFile = FileUtils.toFile(classLoader.getResource("osszeg.txt"));
    }

    @Test
    public void testPenztarProcessingWithPredefinedPenztarInputFile() throws FileNotFoundException {
        final PenztarInputProcessor penztarInputProcessor = new PenztarInputProcessor();
        final Map<Integer, Purchase> purchaseMap =
                penztarInputProcessor.processInputFile(inputFile);
        Assert.assertNotNull(purchaseMap);
        Assert.assertFalse(purchaseMap.isEmpty());

        final Scanner sc = new Scanner(resultFile);
        int rowCount = 0;
        try {
            while (sc.hasNextLine()) {
                final String line = sc.nextLine();
                if (StringUtils.isNotBlank(line)) {
                    rowCount++;
                    final String[] lineSplit = line.split(":");
                    final int purchaseCount = Integer.parseInt(lineSplit[0]);
                    final BigDecimal totalValue = new BigDecimal(lineSplit[1].trim());
                    Assert.assertNotNull(purchaseCount);
                    Assert.assertNotNull(purchaseMap.get(purchaseCount));
                    Assert.assertEquals(totalValue, purchaseMap.get(purchaseCount).getTotalValue(),
                            "Purchase (" + purchaseCount + ")");
                }
            }
        }
        finally {
            sc.close();
        }

        Assert.assertEquals(rowCount, purchaseMap.size());
    }
}
