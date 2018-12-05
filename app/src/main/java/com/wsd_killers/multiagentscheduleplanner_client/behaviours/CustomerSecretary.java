package com.wsd_killers.multiagentscheduleplanner_client.behaviours;

import com.wsd_killers.multiagentscheduleplanner_client.Constans.Constans;
import jade.lang.acl.ACLMessage;


public class CustomerSecretary extends CommonBehaviour {
    @Override
    public void action() {
        if (msg != null) {
            System.out.println("Message: " + msg.toString());
            String conversationId = msg.getConversationId();
            switch (conversationId) {
                case Constans.CustomerSecretaryMessages.RECEIVE_RESERVATION_RESPONSE:
                    break;
                case Constans.CustomerSecretaryMessages.RECEIVE_TASK:
                    break;
                case Constans.CustomerSecretaryMessages.SEND_RESERVATION_REQUEST:
                    break;
                case Constans.CustomerSecretaryMessages.SEND_RESERVATION_STATUS:
                    break;
                case Constans.CustomerSecretaryMessages.SEND_TASK_DATA:
                    break;
                default:
                    myAgent.send(createNotUnderstoodMessage(msg));
                    break;
            }
        }

    }

    @Override
    public boolean isMessageRelevant(ACLMessage msg) {
        if (msg != null) {
            switch (msg.getConversationId()) {
                case Constans.CustomerSecretaryMessages.RECEIVE_RESERVATION_RESPONSE:
                case Constans.CustomerSecretaryMessages.RECEIVE_TASK:
                case Constans.CustomerSecretaryMessages.SEND_RESERVATION_REQUEST:
                case Constans.CustomerSecretaryMessages.SEND_RESERVATION_STATUS:
                case Constans.CustomerSecretaryMessages.SEND_TASK_DATA:
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }

    @Override
    public boolean done() {
        return false;
    }
}
