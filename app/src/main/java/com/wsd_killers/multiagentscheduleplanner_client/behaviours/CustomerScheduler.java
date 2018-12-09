package com.wsd_killers.multiagentscheduleplanner_client.behaviours;


import com.wsd_killers.multiagentscheduleplanner_client.Constans.Constans;

import java.util.Date;

import jade.lang.acl.ACLMessage;

//import Data.ReservationData;


public class CustomerScheduler extends CommonTask {

    private static final Date slotDuration = new Date(0, 0, 0, 0, 15);
//    private ArrayList<ArrayList<ReservationData>> reservations;

    /*
    public CustomerScheduler() {reservations = null;}
    */

    @Override
    public boolean isMessageRelevant(ACLMessage msg) {
        if (msg != null) {
            switch (msg.getConversationId()) {
                case Constans.CustomerSchedulerMessages.NOTIFY_CHANGES:
                case Constans.CustomerSchedulerMessages.RECEIVE_RESERVATION_STATUS:
                case Constans.CustomerSchedulerMessages.RECIVE_TASK_DATA:
                case Constans.CustomerSchedulerMessages.SEND_TASK:
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
            System.out.println("Message: " + msg.toString());
            String conversationId = msg.getConversationId();
            switch (conversationId) {
                case Constans.CustomerSchedulerMessages.NOTIFY_CHANGES:
                    break;
                case Constans.CustomerSchedulerMessages.RECEIVE_RESERVATION_STATUS:
                    break;
                case Constans.CustomerSchedulerMessages.RECIVE_TASK_DATA:
                    break;
                case Constans.CustomerSchedulerMessages.SEND_TASK:
                    break;
                default:
                    return createNotUnderstoodMessage(msg);
            }
        }
        return new ACLMessage();
    }

}
