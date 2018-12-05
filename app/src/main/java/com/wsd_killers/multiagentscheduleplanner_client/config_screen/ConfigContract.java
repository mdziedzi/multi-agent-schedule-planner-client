package com.wsd_killers.multiagentscheduleplanner_client.config_screen;

public interface ConfigContract {

    interface View {

    }

    interface Presenter {

        void bindAgent(String agentName);

        void confirmAgenda();
    }

}
