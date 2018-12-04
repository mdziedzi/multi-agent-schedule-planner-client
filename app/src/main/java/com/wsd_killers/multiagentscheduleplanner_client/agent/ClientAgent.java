package com.wsd_killers.multiagentscheduleplanner_client.agent;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;

public class ClientAgent extends Agent implements ClientAgentInterface {

    protected void setup() {

        System.out.println("Agent: I'm alive!\n");

        registerO2AInterface(ClientAgentInterface.class, this);

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

    @Override
    public void sayHello() {
        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                System.out.println("helloooo");
            }
        });
    }
}