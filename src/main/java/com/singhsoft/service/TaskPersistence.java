package com.singhsoft.service;
import java.io.*;
import java.util.List;

import com.singhsoft.taskscheduler.model.Task;

public class TaskPersistence {
    private static final String FILE_PATH = "data.ser";

    public static void saveTasks(List<Task> tasks) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Task> loadTasks() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (List<Task>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous tasks found.");
            return List.of(); // Return empty list if file not found
        }
    }
}
