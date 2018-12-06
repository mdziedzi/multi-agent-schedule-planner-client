package com.wsd_killers.multiagentscheduleplanner_client.agent;

import com.wsd_killers.multiagentscheduleplanner_client.behaviours.ClientBehaviour;
import com.wsd_killers.multiagentscheduleplanner_client.behaviours.CommonBehaviour;
import com.wsd_killers.multiagentscheduleplanner_client.behaviours.CustomerInterface;
import com.wsd_killers.multiagentscheduleplanner_client.behaviours.CustomerScheduler;
import com.wsd_killers.multiagentscheduleplanner_client.behaviours.CustomerSecretary;
import com.wsd_killers.multiagentscheduleplanner_client.data.ToDoTask;

import java.util.ArrayList;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;

public class ClientAgent extends Agent implements ClientAgentInterface {

    private ArrayList<CommonBehaviour> myBehaviours;
    private CustomerInterface customerInterface = new CustomerInterface();


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


        myBehaviours = new ArrayList<>();

        myBehaviours.add(new CustomerInterface());
        myBehaviours.add(new CustomerScheduler());
        myBehaviours.add(new CustomerSecretary());

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

        customerInterface.setClientData(toDoTaskstasks);
        addBehaviour(new ClientBehaviour(myBehaviours) {
        });

    }

}