package com.wsd_killers.multiagentscheduleplanner_client.agent;

import com.wsd_killers.multiagentscheduleplanner_client.data.ToDoTask;

import java.util.ArrayList;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import com.wsd_killers.multiagentscheduleplanner_client.behaviours.*;

import java.util.ArrayList;

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

        ArrayList<CommonBehaviour> myBehaviours;

        myBehaviours = new ArrayList<>();

        myBehaviours.add(new CustomerInterface());
        myBehaviours.add(new CustomerScheduler());
        myBehaviours.add(new CustomerSecretary());

        addBehaviour(new ClientBehaviour(myBehaviours) {
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

    @Override
    public void insertData(ArrayList<ToDoTask> toDoTaskstasks) {
        // tutaj budzi się agent

    }

}