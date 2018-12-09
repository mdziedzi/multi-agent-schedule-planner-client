package com.wsd_killers.multiagentscheduleplanner_client.agent;

import com.wsd_killers.multiagentscheduleplanner_client.Data.ToDoTask;

import java.util.ArrayList;

public interface ClientAgentInterface {
    void sayHello();

    void insertData(ArrayList<ToDoTask> toDoTaskstasks);
}
