package com.wsd_killers.multiagentscheduleplanner_client.data;

import java.util.ArrayList;

public class ToDoTaskRepository {

    private static ToDoTaskRepository instance;

    private ArrayList<ToDoTask> tasks;

    private ToDoTaskRepository() {
    }

    public static ToDoTaskRepository getInstance() {
        if (instance == null) {
            instance = new ToDoTaskRepository();
        }
        return instance;
    }

    public void addNewTask(ToDoTask newTask) {
        tasks.add(newTask);
    }

    public ArrayList<ToDoTask> getTasks() {
        return tasks;
    }
}
