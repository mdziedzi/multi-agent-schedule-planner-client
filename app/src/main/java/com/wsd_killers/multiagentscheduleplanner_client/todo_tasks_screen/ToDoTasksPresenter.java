package com.wsd_killers.multiagentscheduleplanner_client.todo_tasks_screen;

import android.util.Log;

import com.wsd_killers.multiagentscheduleplanner_client.agent.ClientAgentInterface;
import com.wsd_killers.multiagentscheduleplanner_client.data.ToDoTaskRepository;

import jade.core.MicroRuntime;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;

public class ToDoTasksPresenter implements ToDoTasksContract.Presenter {

    private static final String TAG = ToDoTasksPresenter.class.getSimpleName();
    private final ToDoTasksContract.View configView;
    private ClientAgentInterface clientAgentInterface;

    public ToDoTasksPresenter(ToDoTasksContract.View view) {
        configView = view;
    }

    @Override
    public void bindAgent(String agentName) {
        try {
            clientAgentInterface = MicroRuntime.getAgent(agentName)
                    .getO2AInterface(ClientAgentInterface.class);
            clientAgentInterface.sayHello();
        } catch (StaleProxyException e) {
            Log.e(TAG, "onCreate: internal error");
        } catch (ControllerException e) {
            Log.e(TAG, "onCreate:  server error");
        }
    }

    @Override
    public void confirmAgenda() {
        // tutaj powinniśmy wystartować agenta
        clientAgentInterface.insertData(ToDoTaskRepository.getInstance().getTasks());

    }

}
