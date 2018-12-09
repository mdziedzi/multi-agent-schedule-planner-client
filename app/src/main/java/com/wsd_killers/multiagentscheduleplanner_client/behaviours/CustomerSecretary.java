package com.wsd_killers.multiagentscheduleplanner_client.behaviours;

import com.wsd_killers.multiagentscheduleplanner_client.Constans.Constans;

import Data.ServiceProvider.ServiceProviderData;
import jade.core.AID;
import jade.lang.acl.ACLMessage;


public class CustomerSecretary extends CommonTask {

    @Override
    public boolean isMessageRelevant(ACLMessage msg) {
        if (msg != null) {
            switch (msg.getConversationId()) {
                case Constans.CustomerSecretaryMessages.RECEIVE_RESERVATION_RESPONSE:
                case Constans.CustomerSecretaryMessages.RECEIVE_TASK:
                case Constans.CustomerSecretaryMessages.SEND_RESERVATION_REQUEST:
                case Constans.CustomerSecretaryMessages.SEND_RESERVATION_STATUS:
                case Constans.CustomerSecretaryMessages.SEND_TASK_DATA:
                case Constans.CustomerSecretaryMessages.RECEIVE_SERVICE_DATA:
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }

    @Override
    public ACLMessage processMessage(ACLMessage msg) {
        if (msg != null) {
            System.out.println("Message: " + msg.getConversationId());
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
                case Constans.CustomerSecretaryMessages.RECEIVE_SERVICE_DATA:
                    return onReceiveServiceData(msg);
                case Constans.CustomerSecretaryMessages.SEND_TASK_DATA:
                    return onSendTaskData(msg);
                default:
                    return createNotUnderstoodMessage(msg);
            }
        }
        return new ACLMessage();
    }

    private ACLMessage onSendTaskData(ACLMessage msg) {

        ACLMessage message = new ACLMessage();

        basicBehaviour.updateListOfServiceProviders();
        for (AID receiver : basicBehaviour.getYellowPages()) {
            System.out.println("onSendTaskData(): wysylam sendservicedata");
            message.addReceiver(receiver);
            message.setConversationId(Constans.ServiceProviderSecretaryMessages.SEND_SERVICE_DATA);
            basicBehaviour.sendMessageToAgent(message);
        }

        return new ACLMessage();
    }

    private ACLMessage onReceiveServiceData(ACLMessage msg) {
        //todo: tutaj otrzymujemy informacje od agenta serwisu o jego godzinach otwarcia itp. itd.
        //todo: trzeba zaimportowac klase ServiceProviderData i odczytac ja z pola msg.getContent()
        ServiceProviderData serviceProviderData = ServiceProviderData.deserialize(msg.getContent());
        System.out.println(serviceProviderData.toString());

        return new ACLMessage();
    }
}
