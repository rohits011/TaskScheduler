package com.singhsoft;

import com.singhsoft.service.TaskExecutor;
import com.singhsoft.service.TaskManager;
import com.singhsoft.taskscheduler.model.Task;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

         TaskManager taskManager = new TaskManager();
        TaskExecutor executor = new TaskExecutor(taskManager);
        Task task4 = new Task("4", "Task 4", 2, System.currentTimeMillis(), false);
        Task task5 = new Task("5", "Task 5", 4, System.currentTimeMillis(), false);
        Task task6 = new Task("6", "Task 6", 6, System.currentTimeMillis(), false);
        Task task1 = new Task("1", "Task 1", 5, System.currentTimeMillis(), false);
        Task task2 = new Task("2", "Task 2", 3, System.currentTimeMillis(), false);
        Task task3 = new Task("3", "Task 3", 1, System.currentTimeMillis(), false);
        // Add sample tasks
        taskManager.addTask(task6);
        taskManager.addTask(task2);
        taskManager.addTask(task3);
        taskManager.addTask(task4);
      
        executor.startProcessing();
        taskManager.addTask(task5);
        taskManager.addTask(task1);

        try {
            Thread.sleep(5000); // Let the executor run
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();

        System.out.println("Completed Tasks: " + taskManager.getCompletedTasks());
    }
}
