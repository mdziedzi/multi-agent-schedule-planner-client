package com.wsd_killers.multiagentscheduleplanner_client.behaviours;

import jade.lang.acl.ACLMessage;
import com.wsd_killers.multiagentscheduleplanner_client.Constans.Constans;
import com.wsd_killers.multiagentscheduleplanner_client.Exceptions.negativeValueException;


import java.util.Date;
import java.util.Objects;


public class CustomerInterface extends CommonBehaviour implements com.wsd_killers.multiagentscheduleplanner_client.Interfaces.CustomerInterface {

    public void setCustomerData(com.wsd_killers.multiagentscheduleplanner_client.Data.CustomerData data) throws negativeValueException{


    }

    @Override
    public void action() {
        if (msg != null) {
            System.out.println("Message: " + msg.toString());
            String conversationId = msg.getConversationId();
            switch (conversationId) {
                case Constans.ServiceProviderInterfaceMessages.VERIFY_RESERVATION:
                    break;
                case Constans.ServiceProviderInterfaceMessages.SEND_SERVICE_DATA:
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
                case Constans.CustomerInterfaceMessages.SEND_TASK_DATA:
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
