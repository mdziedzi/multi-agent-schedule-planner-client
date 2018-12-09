package com.wsd_killers.multiagentscheduleplanner_client.Data;

import java.util.ArrayList;

public class FinalTasksRepository {

    private static FinalTasksRepository instance;

    private ArrayList<FinalTask> tasks;

    private FinalTasksRepository() {
    }

    public static FinalTasksRepository getInstance() {
        if (instance == null) {
            instance = new FinalTasksRepository();
        }
        return instance;
    }

    public void addNewTask(FinalTask newTask) {
        tasks.add(newTask);
    }

    public ArrayList<FinalTask> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<FinalTask> tasks) {
        this.tasks = tasks;
    }
}
