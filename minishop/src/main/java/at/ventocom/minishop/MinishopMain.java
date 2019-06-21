package at.ventocom.minishop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import at.ventocom.minishop.purchase.Purchase;
import at.ventocom.minishop.task.impl.Task2;
import at.ventocom.minishop.task.impl.Task3;
import at.ventocom.minishop.task.impl.Task4;
import at.ventocom.minishop.task.impl.Task5;
import at.ventocom.minishop.task.impl.Task6;
import at.ventocom.minishop.task.impl.Task7;
import at.ventocom.minishop.util.FileHandler;

/**
 * Main class of the application.
 *
 */
public class MinishopMain {
    /**
     * Main method of the application.
     *
     * @param args
     *            application arguments
     */
    public static void main(final String[] args) {
        final Options options = new Options();
        options.addRequiredOption("i", "inputFile", true, "Data input file");
        options.addRequiredOption("o", "outputFile", true, "Output file containing data summary");

        final CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
        } catch (final ParseException e) {
            cmd = null;
            System.err.println(
                    String.format("Failed to parse command line arguments! %s", e.getMessage()));
            final HelpFormatter helpFormatter = new HelpFormatter();
            helpFormatter.printHelp("minishop", options);
            System.exit(1);
        }

        final String inputFileArgValue = cmd.getOptionValue("i");
        final String outputFileArgValue = cmd.getOptionValue("o");

        try {
            MinishopMain.runApp(inputFileArgValue, outputFileArgValue);
        } catch (final FileNotFoundException e) {
            System.err.println("Input file wasn't found (" + inputFileArgValue + ")");
            System.exit(1);
        }
        System.exit(0);
    }

    /**
     * Runs the application which processes data read from the input file.
     *
     * @param inputFilePath
     *            path of the input file
     * @param outputFilePath
     *            path of the output file
     * @throws FileNotFoundException
     *             If the input file couldn't be found.
     */
    private static void runApp(final String inputFilePath, final String outputFilePath)
            throws FileNotFoundException {
        final FileHandler fileHandler = new FileHandler();
        final File inputFile = fileHandler.loadFile(inputFilePath);
        final PenztarInputProcessor penztarInputProcessor = new PenztarInputProcessor();
        final Map<Integer, Purchase> purchaseData =
                penztarInputProcessor.processInputFile(inputFile);
        final TaskProcessor taskProcessor = new TaskProcessor(purchaseData);
        final Map<String, String> sharedTaskVariables = new HashMap<String, String>();
        taskProcessor.addTask(new Task2(purchaseData, sharedTaskVariables));
        taskProcessor.addTask(new Task3(purchaseData, sharedTaskVariables));
        taskProcessor.addTask(new Task4(purchaseData, sharedTaskVariables));
        taskProcessor.addTask(new Task5(purchaseData, sharedTaskVariables));
        taskProcessor.addTask(new Task6(purchaseData, sharedTaskVariables));
        taskProcessor.addTask(new Task7(purchaseData, sharedTaskVariables));
        taskProcessor.runTasks();
        try {
            fileHandler.writeSummaryFile(outputFilePath, purchaseData);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
