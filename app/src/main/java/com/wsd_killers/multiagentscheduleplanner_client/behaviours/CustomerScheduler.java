package com.wsd_killers.multiagentscheduleplanner_client.behaviours;


import com.wsd_killers.multiagentscheduleplanner_client.Constans.Constans;
import com.wsd_killers.multiagentscheduleplanner_client.data.FinalTask;
import com.wsd_killers.multiagentscheduleplanner_client.data.ToDoTask;
import com.wsd_killers.multiagentscheduleplanner_client.utils.SerializationUtils;

import java.util.ArrayList;
import java.util.Date;

import jade.lang.acl.ACLMessage;

//import Data.ReservationData;


public class CustomerScheduler extends CommonTask {

    private static final Date slotDuration = new Date(0, 0, 0, 0, 15);
    private ArrayList<ToDoTask> toDoTasks;
    private ArrayList<FinalTask> agenda = new ArrayList<>();
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
                case Constans.CustomerSchedulerMessages.SEND_TASKS:
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
                case Constans.CustomerSchedulerMessages.NOTIFY_CHANGES:
                    break;
                case Constans.CustomerSchedulerMessages.RECEIVE_RESERVATION_STATUS:
                    break;
                case Constans.CustomerSchedulerMessages.RECIVE_TASK_DATA:
                    return onReceiveTaskData(msg); //todo: change
                case Constans.CustomerSchedulerMessages.SEND_TASKS:
                    return onReceiveTaskData(msg);
                default:
                    return createNotUnderstoodMessage(msg);
            }
        }
        return new ACLMessage();
    }

    private ACLMessage onReceiveTaskData(ACLMessage msg) {
        toDoTasks = ((ArrayList<ToDoTask>) SerializationUtils.deserializeFromString(msg.getContent()));
        presetAgenda(toDoTasks);
        return outsourceBookingForCustomerSecretary();
    }

    private ACLMessage outsourceBookingForCustomerSecretary() {
        for (FinalTask finalTask : agenda) {
            ACLMessage internalMsg = new ACLMessage();
            internalMsg.setConversationId(Constans.CustomerSecretaryMessages.SEND_TASK_DATA);
            internalMsg.setContent(SerializationUtils.serializeToString(finalTask));
            sendMessageToOtherTask(internalMsg);
        }
        return new ACLMessage();
    }

    private void presetAgenda(ArrayList<ToDoTask> toDoTasks) {
        for (ToDoTask toDoTask : toDoTasks) {
            agenda.add(convertToDoTaskForFinalTask(toDoTask));
        }
    }

    //dummy
    private FinalTask convertToDoTaskForFinalTask(ToDoTask toDoTask) {
        FinalTask finalTask = new FinalTask();
        finalTask.setName(toDoTask.getName());
        finalTask.setType(toDoTask.getType());
        finalTask.setSlotsQuantity(toDoTask.getSlotsQuantity());
        finalTask.setDuration(toDoTask.getEstimatedDuration());
        finalTask.setStartTime(toDoTask.getTimeIntervalBegin());
        Date endTime = new Date(toDoTask.getTimeIntervalBegin().getTime() + toDoTask.getEstimatedDuration().getTime());
        finalTask.setEndTime(endTime);
        return null;
    }

}
