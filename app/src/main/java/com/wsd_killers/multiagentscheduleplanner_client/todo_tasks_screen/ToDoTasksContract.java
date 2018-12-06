package com.wsd_killers.multiagentscheduleplanner_client.todo_tasks_screen;

public interface ToDoTasksContract {

    interface View {

    }

    interface Presenter {

        void bindAgent(String agentName);

        void confirmAgenda();
    }

}
