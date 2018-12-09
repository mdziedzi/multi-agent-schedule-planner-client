package com.wsd_killers.multiagentscheduleplanner_client.data;

import java.util.Date;

public class FinalTask extends Task {

    private Date startTime;
    private Date endTime;
    private Date duration;

    public FinalTask(String name, TaskType type, int slotsQuantity, Date startTime, Date endTime, Date duration) {
        this.name = name;
        this.type = type;
        this.slotsQuantity = slotsQuantity;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
    }

    public FinalTask() {

    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }
}
