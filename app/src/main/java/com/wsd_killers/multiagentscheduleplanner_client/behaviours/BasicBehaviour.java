package com.wsd_killers.multiagentscheduleplanner_client.behaviours;

import java.util.ArrayList;
import java.util.Arrays;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

public class BasicBehaviour extends CyclicBehaviour {

    private ArrayList<CommonTask> TasksList;

    public BasicBehaviour(ArrayList<CommonTask> tasks) {
        TasksList = new ArrayList<>(tasks);
    }

    private ArrayList<AID> yellowPages = new ArrayList<>();

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        boolean isSPFound = false;
        if (msg != null) {
            for (CommonTask sp : TasksList) {
                if (sp.isMessageRelevant(msg)) {
                    isSPFound = true;
                    myAgent.send(sp.processMessage(msg));
                }
            }
            if (!isSPFound) {
                myAgent.send(createNotUnderstoodMessage(msg));
            }
        } else {
            block();
        }
    }

    public void sendMessageToAgent(ACLMessage msg) {
        myAgent.send(msg);
    }

    public String sendMessageToTask(ACLMessage msg) {
        for (CommonTask ct : TasksList) {
            if (ct.isMessageRelevant(msg)) {
                return ct.processMessage(msg).getContent();
            }
        }
        return "";
    }

    public ACLMessage createNotUnderstoodMessage(ACLMessage message) {
        ACLMessage reply = message.createReply();
        reply.setPerformative(ACLMessage.NOT_UNDERSTOOD);
        reply.setContent("Not understood message");
        return reply;
    }

    public void updateListOfServiceProviders() {
        // Update the list of seller agents
        DFAgentDescription template = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setType("service-provider");
        template.addServices(sd);
        try {
            DFAgentDescription[] result = DFService.search(myAgent, template);
            AID[] sellerAgents = new AID[result.length];
            for (int i = 0; i < result.length; ++i) {
                sellerAgents[i] = result[i].getName();
            }
            yellowPages = new ArrayList<>(Arrays.asList(sellerAgents));
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }
    }

    public ArrayList<AID> getYellowPages() {
        return yellowPages;
    }

}
