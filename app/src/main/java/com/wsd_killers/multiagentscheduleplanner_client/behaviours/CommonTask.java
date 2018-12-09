package com.wsd_killers.multiagentscheduleplanner_client.behaviours;

import jade.lang.acl.ACLMessage;

public abstract class CommonTask {

    protected BasicBehaviour basicBehaviour;

    public void SetBasicBehaviour(BasicBehaviour bb) {
        basicBehaviour = bb;
    }

    public boolean sendMessageToOtherTask(ACLMessage msg) {
        return basicBehaviour.sendMessageToTask(msg);
    }

    public abstract boolean isMessageRelevant(ACLMessage msg);

    public abstract ACLMessage processMessage(ACLMessage message);

    public ACLMessage createNotUnderstoodMessage(ACLMessage message) {
        ACLMessage reply = message.createReply();
        reply.setPerformative(ACLMessage.NOT_UNDERSTOOD);
        reply.setContent("Not understood message");
        return reply;
    }
}
