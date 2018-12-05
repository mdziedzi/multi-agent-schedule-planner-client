package com.wsd_killers.multiagentscheduleplanner_client.config_screen;

import android.util.Log;

import com.wsd_killers.multiagentscheduleplanner_client.agent.ClientAgentInterface;
import com.wsd_killers.multiagentscheduleplanner_client.data.ToDoTaskRepository;

import jade.core.MicroRuntime;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;

public class ConfigPresenter implements ConfigContract.Presenter {

    private static final String TAG = ConfigPresenter.class.getSimpleName();
    private final ConfigContract.View configView;
    private ClientAgentInterface clientAgentInterface;

    public ConfigPresenter(ConfigContract.View view) {
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
