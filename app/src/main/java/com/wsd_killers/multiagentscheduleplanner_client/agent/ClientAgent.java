package com.wsd_killers.multiagentscheduleplanner_client.agent;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;

public class ClientAgent extends Agent {

    protected void setup() {

        System.out.println("Agent: I'm alive!\n");

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                System.out.println("Jestem androindowym agentem!");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void takeDown() {

        System.out.println("Agent: I'm dead!\n");

    }
}
