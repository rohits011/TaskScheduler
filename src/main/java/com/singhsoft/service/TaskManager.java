package com.singhsoft.service;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.singhsoft.taskscheduler.model.Task;

public class TaskManager {
    private final PriorityQueue<Task> taskQueue = new PriorityQueue<>();
    private final Queue<Task> completedTasks = new ConcurrentLinkedQueue<>();

    public TaskManager() {
        loadPendingTasks(); // Load tasks on startup
    }

    public synchronized void addTask(Task task) {
        taskQueue.offer(task);
        savePendingTasks();
    }

    public synchronized Task retrieveNextTask() {
        Task task = taskQueue.poll();
        savePendingTasks();
        return task;
    }

    public synchronized boolean removeTask(Task task) {
        boolean removed = taskQueue.remove(task);
        savePendingTasks();
        return removed;
    }

    private void savePendingTasks() {
        TaskPersistence.saveTasks(new ArrayList<>(taskQueue));
    }

    private void loadPendingTasks() {
        List<Task> tasks = TaskPersistence.loadTasks();
        taskQueue.addAll(tasks);
    }

    public void shutdown() {
        savePendingTasks();
    }

    public void markTaskCompleted(Task task) {
        task.setCompletedStatus(true);
        completedTasks.offer(task);
    }

    public String getCompletedTasks() {
       return completedTasks.toString();
    }
}
