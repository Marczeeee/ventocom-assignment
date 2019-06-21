package at.ventocom.minishop.test;

import java.util.Collections;

import org.mockito.Mockito;
import org.testng.annotations.Test;

import at.ventocom.minishop.TaskProcessor;
import at.ventocom.minishop.task.Task;

/**
 * Test of {@link TaskProcessor} class.
 *
 */
public class TestTaskProcessor {
    @Test
    public void testTaskProcessorTaskRunning() {
        final TaskProcessor taskProcessor = new TaskProcessor(Collections.EMPTY_MAP);
        final Task task1Mock = Mockito.mock(Task.class);
        Mockito.when(task1Mock.getTaskNumber()).thenReturn(1);
        taskProcessor.addTask(task1Mock);
        final Task task2Mock = Mockito.mock(Task.class);
        Mockito.when(task2Mock.getTaskNumber()).thenReturn(2);
        taskProcessor.addTask(task2Mock);
        final Task task3Mock = Mockito.mock(Task.class);
        Mockito.when(task3Mock.getTaskNumber()).thenReturn(3);
        taskProcessor.addTask(task3Mock);

        taskProcessor.runTasks();
        Mockito.verify(task1Mock, Mockito.atLeastOnce()).getTaskNumber();
        Mockito.verify(task1Mock, Mockito.times(1)).run();
        Mockito.verify(task2Mock, Mockito.atLeastOnce()).getTaskNumber();
        Mockito.verify(task2Mock, Mockito.times(1)).run();
        Mockito.verify(task3Mock, Mockito.atLeastOnce()).getTaskNumber();
        Mockito.verify(task3Mock, Mockito.times(1)).run();
    }
}
