package com.wsd_killers.multiagentscheduleplanner_client.agent;

import com.wsd_killers.multiagentscheduleplanner_client.Constans.Constans;
import com.wsd_killers.multiagentscheduleplanner_client.behaviours.BasicBehaviour;
import com.wsd_killers.multiagentscheduleplanner_client.behaviours.CommonTask;
import com.wsd_killers.multiagentscheduleplanner_client.behaviours.CustomerInterface;
import com.wsd_killers.multiagentscheduleplanner_client.behaviours.CustomerScheduler;
import com.wsd_killers.multiagentscheduleplanner_client.behaviours.CustomerSecretary;
import com.wsd_killers.multiagentscheduleplanner_client.data.ToDoTask;
import com.wsd_killers.multiagentscheduleplanner_client.utils.SerializationUtils;

import java.util.ArrayList;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

public class ClientAgent extends Agent implements ClientAgentInterface {

    private ArrayList<CommonTask> tasks;

    //    private CustomerInterface customerInterface = new CustomerInterface();
    private BasicBehaviour bb;


    protected void setup() {

        System.out.println("Agent: I'm alive!\n");

        registerO2AInterface(ClientAgentInterface.class, this);

        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("client");
        sd.setName("client-default-name");
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }

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

        tasks = new ArrayList<>();

        tasks.add(new CustomerInterface());
        tasks.add(new CustomerScheduler());
        tasks.add(new CustomerSecretary());

        bb = new BasicBehaviour(tasks);
        for (CommonTask ct : tasks) {
            ct.SetBasicBehaviour(bb);
        }

        addBehaviour(bb);
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
        ACLMessage msg = new ACLMessage();
        msg.setConversationId(Constans.CustomerInterfaceMessages.SEND_TASK_DATA);
        msg.setContent(SerializationUtils.serializeToString(toDoTaskstasks));
        bb.sendMessageToTask(msg);
    }

}