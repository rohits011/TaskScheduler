package com.singhsoft.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.singhsoft.taskscheduler.model.Task;

public class TaskExecutor {
    private final TaskManager taskQueue;
    private final ExecutorService executorService = Executors.newFixedThreadPool(2);

    public TaskExecutor(TaskManager taskQueue) {
        this.taskQueue = taskQueue;
    }

    public void startProcessing() {
        executorService.submit(() -> {
            while (true) {
                Task task = taskQueue.retrieveNextTask();
                if (task != null) {
                    System.out.println("Thread "+Thread.currentThread().getName()+" Executing: " + task);
                    taskQueue.markTaskCompleted(task);
                    try {
                        Thread.sleep(1000); // Simulate task execution time
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
