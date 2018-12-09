package com.wsd_killers.multiagentscheduleplanner_client.behaviours;

import java.util.ArrayList;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class BasicBehaviour extends CyclicBehaviour {

    private ArrayList<CommonTask> TasksList;

    public BasicBehaviour(ArrayList<CommonTask> tasks) {
        TasksList = new ArrayList<>(tasks);
    }

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        boolean isSPFound = false;
        if (msg != null) {
            for (CommonTask sp : TasksList) {
                if (sp.isMessageRelevant(msg)) {
                    isSPFound = true;
                    myAgent.send(sp.ProcessMessage(msg));
                }
            }
            if (!isSPFound) {
                myAgent.send(createNotUnderstoodMessage(msg));
            }
        }
    }

    public boolean sendMessageToTask(ACLMessage msg) {
        for (CommonTask ct : TasksList) {
            if (ct.isMessageRelevant(msg)) {
                ct.ProcessMessage(msg);
                return true;
            }
        }
        return false;
    }

    public ACLMessage createNotUnderstoodMessage(ACLMessage message) {
        ACLMessage reply = message.createReply();
        reply.setPerformative(ACLMessage.NOT_UNDERSTOOD);
        reply.setContent("Not understood message");
        return reply;
    }
}
