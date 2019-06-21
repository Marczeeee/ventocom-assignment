package at.ventocom.minishop.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import at.ventocom.minishop.purchase.Purchase;

/**
 * Helper class for dealing with certain file operations.
 *
 */
public class FileHandler {
    /**
     * Loads the file on the path given.
     *
     * @param path
     *            path of input file
     * @return The loaded file
     */
    public File loadFile(final String path) {
        if (StringUtils.isBlank(path)) {
            throw new IllegalArgumentException("Input file path can't be null or empty!");
        }

        return new File(path);
    }

    /**
     * Writes a purchase summary output file containing the total value of all the purchases along
     * with their purchase numbers.
     *
     * @param filePath
     *            output file path
     * @param purchaseData
     *            {@link Map} containing all purchase data
     * @throws IOException
     *             If an I/O error occurs during writing the output file.
     */
    public void writeSummaryFile(final String filePath, final Map<Integer, Purchase> purchaseData)
            throws IOException {
        if (StringUtils.isBlank(filePath)) {
            throw new IllegalArgumentException("Output file path can't be null or empty!");
        }

        final File outputFile = new File(filePath);
        final FileWriter fileWriter = new FileWriter(outputFile);
        for (final Entry<Integer, Purchase> purchaseEntry : purchaseData.entrySet()) {
            final BigDecimal totalValue = purchaseEntry.getValue().getTotalValue();
            final DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(0);
            df.setGroupingUsed(false);
            fileWriter.write(
                    String.format("%d: %s\n", purchaseEntry.getKey(), df.format(totalValue)));
        }
        fileWriter.flush();
        fileWriter.close();
    }
}
