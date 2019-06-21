package at.ventocom.minishop;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import at.ventocom.minishop.purchase.Purchase;
import at.ventocom.minishop.task.Task;

/**
 * {@link Task} processor class running all tasks set to be completed.
 *
 */
public class TaskProcessor {
    /**
     * {@link List} of tasks order by their task number values.
     */
    private final Set<Task> tasks = new TreeSet<Task>(new Comparator<Task>() {
        public int compare(final Task o1, final Task o2) {
            return Integer.compare(o1.getTaskNumber(), o2.getTaskNumber());
        }
    });

    /**
     * Ctor.
     *
     * @param purchaseData
     *            purchase data holder object
     */
    public TaskProcessor(final Map<Integer, Purchase> purchaseData) {
        super();
    }

    /**
     * Add new {@link Task} to the {@link Set} of tasks to be run.
     *
     * @param task
     *            {@link Task} to be added
     */
    public final void addTask(final Task task) {
        tasks.add(task);
    }

    /**
     * Runs all tasks sequentially.
     */
    public final void runTasks() {
        for (final Task task : tasks) {
            task.run();
        }
    }
}
