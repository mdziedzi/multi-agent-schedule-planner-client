package com.wsd_killers.multiagentscheduleplanner_client.behaviours;

import java.util.ArrayList;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public abstract class ClientBehaviour extends CyclicBehaviour {

    private ArrayList<CommonBehaviour> myBehaviours;

    public ClientBehaviour(ArrayList<CommonBehaviour> behaviours) { myBehaviours = new ArrayList<>(behaviours);}

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        boolean isSPFound = false;
        if (msg != null) {
            for (CommonBehaviour sp : myBehaviours) {
                if (sp.isMessageRelevant(msg)) {
                    isSPFound = true;
                    sp.SetACLMessage(msg);
                    sp.action();
                }
            }
            if (!isSPFound) {
                myAgent.send(createNotUnderstoodMessage(msg));
            }
        }
    }

    public ACLMessage createNotUnderstoodMessage(ACLMessage message) {
        ACLMessage reply = message.createReply();
        reply.setPerformative(ACLMessage.NOT_UNDERSTOOD);
        reply.setContent("Not understood message");
        return reply;
    }

}
