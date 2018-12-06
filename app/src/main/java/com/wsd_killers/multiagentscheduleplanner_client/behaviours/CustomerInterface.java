package com.wsd_killers.multiagentscheduleplanner_client.behaviours;

import com.wsd_killers.multiagentscheduleplanner_client.Constans.Constans;
import com.wsd_killers.multiagentscheduleplanner_client.Exceptions.negativeValueException;
import com.wsd_killers.multiagentscheduleplanner_client.data.ToDoTask;

import java.util.ArrayList;
import java.util.Objects;

import jade.lang.acl.ACLMessage;


public class CustomerInterface extends CommonBehaviour implements com.wsd_killers.multiagentscheduleplanner_client.Interfaces.CustomerInterfaceInterface {

    private String name;
    private String startTime;
    private String endTime;
    private String duration;

    public void setCustomerData(com.wsd_killers.multiagentscheduleplanner_client.data.FinalTask data) throws negativeValueException{
        if (data.getName() != null && name != data.getName()) {
            name = data.getName();
        }
        if (data.getStartTime() != null && startTime != data.getStartTime()) {
            startTime = data.getStartTime();
        }
        if (data.getEndTime() != null && endTime != data.getEndTime()) {
            endTime = data.getEndTime();
        }
        if (data.getDuration() != null && endTime != data.getDuration()) {
            duration = data.getDuration();
        }
        if (data.getName() != null && !Objects.equals(name, data.getName())) {
            name = data.getName();
        }
        if (data.getDuration() != null && !Objects.equals(duration, data.getDuration())) {
            duration = data.getDuration();
        }
        if (data.getStartTime() != null && !Objects.equals(startTime, data.getStartTime())) {
            startTime = data.getStartTime();
        }
        if (data.getEndTime() != null && !Objects.equals(startTime, data.getEndTime())) {
            endTime = data.getEndTime();
        }


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

    public void setClientData(ArrayList<ToDoTask> toDoTasks) {
        unpackToDoTasks(toDoTasks);
    }

    private void unpackToDoTasks(ArrayList<ToDoTask> toDoTasks) {
        //todo
    }
}
