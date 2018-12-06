package com.wsd_killers.multiagentscheduleplanner_client.behaviours;

import com.wsd_killers.multiagentscheduleplanner_client.Constans.Constans;
import com.wsd_killers.multiagentscheduleplanner_client.Exceptions.BadFormatException;
import com.wsd_killers.multiagentscheduleplanner_client.Exceptions.TimeIntegrityException;
import com.wsd_killers.multiagentscheduleplanner_client.data.ToDoTask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import jade.lang.acl.ACLMessage;

import static android.text.TextUtils.isEmpty;


public class CustomerInterface extends CommonBehaviour implements com.wsd_killers.multiagentscheduleplanner_client.Interfaces.CustomerInterfaceInterface {

    //    private String name;
//    private String startTime;
//    private String endTime;
//    private String duration;
    private ArrayList<ToDoTask> toDoTasks;

//    public void setCustomerData(com.wsd_killers.multiagentscheduleplanner_client.data.FinalTask data) throws negativeValueException {
//        if (data.getName() != null && name != data.getName()) {
//            name = data.getName();
//        }
//        if (data.getStartTime() != null && startTime != data.getStartTime()) {
//            startTime = data.getStartTime();
//        }
//        if (data.getEndTime() != null && endTime != data.getEndTime()) {
//            endTime = data.getEndTime();
//        }
//        if (data.getDuration() != null && endTime != data.getDuration()) {
//            duration = data.getDuration();
//        }
//        if (data.getName() != null && !Objects.equals(name, data.getName())) {
//            name = data.getName();
//        }
//        if (data.getDuration() != null && !Objects.equals(duration, data.getDuration())) {
//            duration = data.getDuration();
//        }
//        if (data.getStartTime() != null && !Objects.equals(startTime, data.getStartTime())) {
//            startTime = data.getStartTime();
//        }
//        if (data.getEndTime() != null && !Objects.equals(startTime, data.getEndTime())) {
//            endTime = data.getEndTime();
//        }
//
//
//    }

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
        collectDataFromCustomer(toDoTasks);
    }

    // CollectDataFromCustomer
    private void collectDataFromCustomer(ArrayList<ToDoTask> toDoTasks) {
        try {
            for (ToDoTask t : toDoTasks) {
                verifyToDoTask(t);
            }
        } catch (BadFormatException | TimeIntegrityException e) {
            //todo: niech tutaj wyświetli się chociaż Toast
            e.printStackTrace();
            System.out.println(e.getMessage());
            return;
        }
        this.toDoTasks = toDoTasks;
        proceedData(toDoTasks);
    }

    /**
     * Proceed data to CustomerScheduler role
     *
     * @param toDoTasks list of verified tasks to schedule.
     */
    private void proceedData(ArrayList<ToDoTask> toDoTasks) {

    }

    private void verifyToDoTask(ToDoTask toDoTask) throws BadFormatException, TimeIntegrityException {
        verifyTaskNameFormat(toDoTask);
        verifyTaskTimeIntervalBeginFormat(toDoTask);
        verifyTaskTimeIntervalEndFormat(toDoTask);
        verifyTaskDurationFormat(toDoTask);
        verifyTaskTypeFormat(toDoTask);
        verifySlotsQuantityFormat(toDoTask);

        verifyTimesIntegrity(toDoTask);
    }

    private void verifyTimesIntegrity(ToDoTask toDoTask) throws TimeIntegrityException {
        Date begin = toDoTask.getTimeIntervalBegin();
        Date end = toDoTask.getTimeIntervalEnd();
        Date duration = toDoTask.getEstimatedDuration();
        checkIfBeginDateIsLessThanEndDate(begin, end);
        checkIfDurationIsLessThanTimeInterval(begin, end, duration);
    }

    private void checkIfDurationIsLessThanTimeInterval(Date begin, Date end, Date duration) throws TimeIntegrityException {
        Calendar beginCalendar = Calendar.getInstance();
        beginCalendar.setTime(begin);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);

        Calendar durationCalendar = Calendar.getInstance();
        durationCalendar.setTime(duration);

        if ((endCalendar.getTimeInMillis() - beginCalendar.getTimeInMillis()) < durationCalendar.getTimeInMillis()) {
            throw new TimeIntegrityException("DurationIsLessTahnTimeInterval");
        }
    }

    private void checkIfBeginDateIsLessThanEndDate(Date begin, Date end) throws TimeIntegrityException {
        if (!begin.before(end)) {
            throw new TimeIntegrityException("BeginDateIsLessThanEndDate");
        }
    }


    private void verifySlotsQuantityFormat(ToDoTask toDoTask) throws BadFormatException {
        String verifiedSlotsQuantity = String.valueOf(toDoTask.getSlotsQuantity());
        if (isEmpty(verifiedSlotsQuantity) || !isNumber(verifiedSlotsQuantity) || !isMoreThanZero(verifiedSlotsQuantity)) {
            throw new BadFormatException("ToDoTaskSlotsQuantityFormat");
        }
    }


    private void verifyTaskTypeFormat(ToDoTask toDoTask) {
        // there is no point to verify it for now
    }

    private void verifyTaskDurationFormat(ToDoTask toDoTask) throws BadFormatException {
//        String verifiedDuration = toDoTask.getEstimatedDuration().toString();
//        if (isEmpty(verifiedDuration) || !isTime(verifiedDuration)) {
//            throw new BadFormatException("ToDoTaskDurationFormat");
//        }
        Date verifiedDuration = toDoTask.getEstimatedDuration();
        if (verifiedDuration == null) {
            throw new BadFormatException("ToDoTaskDurationFormat");
        }
    }

    private void verifyTaskTimeIntervalEndFormat(ToDoTask toDoTask) throws BadFormatException {
//        String verifiedTimeIntervalEnd = toDoTask.getTimeIntervalEnd().toString();
//        if (isEmpty(verifiedTimeIntervalEnd) || !isTime(verifiedTimeIntervalEnd)) {
//            throw new BadFormatException("ToDoTaskIntervalEndFormat");
//        }
        Date verifiedTimeIntervalEnd = toDoTask.getTimeIntervalEnd();
        if (verifiedTimeIntervalEnd == null) {
            throw new BadFormatException("ToDoTaskIntervalEndFormat");
        }
    }

    private void verifyTaskTimeIntervalBeginFormat(ToDoTask toDoTask) throws BadFormatException {
//        String verifiedTimeIntervalBegin = toDoTask.getTimeIntervalBegin().toString();
//        if (isEmpty(verifiedTimeIntervalBegin) || !isTime(verifiedTimeIntervalBegin)) {
//            throw new BadFormatException("ToDoTaskTimeIntervalBeginFormat");
//        }
        Date verifiedTimeIntervalBegin = toDoTask.getTimeIntervalBegin();
        if (verifiedTimeIntervalBegin == null) {
            throw new BadFormatException("ToDoTaskTimeIntervalBeginFormat");
        }
    }

    private void verifyTaskNameFormat(ToDoTask toDoTask) throws BadFormatException {
        String verifiedName = toDoTask.getName();
        if (isEmpty(verifiedName)) {
            throw new BadFormatException("ToDoTaskName");
        }
    }

    private boolean isTime(String verifiedName) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
//        timeFormat.setLenient(false);
        try {
            timeFormat.parse(verifiedName);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    private boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private boolean isMoreThanZero(String s) {
        return Integer.parseInt(s) > 0;
    }

}
