package com.wsd_killers.multiagentscheduleplanner_client.data;

import java.util.Date;

public class ToDoTask extends Task {

    private Date timeIntervalBegin;
    private Date timeIntervalEnd;
    private Date estimatedDuration;

    public ToDoTask(String name, TaskType type, int slotsQuantity, Date timeIntervalBegin, Date timeIntervalEnd, Date estimatedDuration) {
        this.name = name;
        this.type = type;
        this.slotsQuantity = slotsQuantity;
        this.timeIntervalBegin = timeIntervalBegin;
        this.timeIntervalEnd = timeIntervalEnd;
        this.estimatedDuration = estimatedDuration;
    }

    public Date getTimeIntervalBegin() {
        return timeIntervalBegin;
    }

    public void setTimeIntervalBegin(Date timeIntervalBegin) {
        this.timeIntervalBegin = timeIntervalBegin;
    }

    public Date getTimeIntervalEnd() {
        return timeIntervalEnd;
    }

    public void setTimeIntervalEnd(Date timeIntervalEnd) {
        this.timeIntervalEnd = timeIntervalEnd;
    }

    public Date getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(Date estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

}
