package at.ventocom.minishop.task;

/**
 * Interface define a generic task. Every task contains a (unique) task number used for ordering
 * task before running.
 *
 */
public interface Task {
    /**
     * Returns current task number.
     *
     * @return task number value
     */
    int getTaskNumber();

    /**
     * Runs the task.
     */
    void run();

    /**
     * Returns a header generated to identify current task run.
     *
     * @return task header {@link String}
     */
    String getTaskHeader();
}
