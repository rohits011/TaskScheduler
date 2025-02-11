package com.singhsoft.taskscheduler.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String name;
    private String email;
    private List<Task> tasks;
}
