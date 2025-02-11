
package com.singhsoft.taskscheduler.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Task implements Comparable<Task>,Serializable {
    private static final long serialVersionUID = 1L; 
    private String taskId;
    private String taskName;
    private int priority;
    private long executionTime;
    private boolean completedStatus;
    @Override
    public int compareTo(Task task) {
        return Integer.compare(task.priority, this.priority);
    }
}
