package com.wsd_killers.multiagentscheduleplanner_client.agent;

import com.wsd_killers.multiagentscheduleplanner_client.data.ToDoTask;

import java.util.ArrayList;

public interface ClientAgentInterface {
    void sayHello();

    void insertData(ArrayList<ToDoTask> toDoTaskstasks);
}
