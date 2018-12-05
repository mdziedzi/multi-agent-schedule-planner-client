package com.wsd_killers.multiagentscheduleplanner_client.behaviours;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public abstract class CommonBehaviour extends Behaviour {

    public abstract boolean isMessageRelevant(ACLMessage msg);

    protected ACLMessage msg;

    public void SetACLMessage(ACLMessage message) {
        msg = message;
    }

    public ACLMessage createNotUnderstoodMessage(ACLMessage message) {
        ACLMessage reply = message.createReply();
        reply.setPerformative(ACLMessage.NOT_UNDERSTOOD);
        reply.setContent("Not understood message");
        return reply;
    }
}