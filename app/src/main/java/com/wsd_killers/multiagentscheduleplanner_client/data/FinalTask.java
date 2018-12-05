package com.wsd_killers.multiagentscheduleplanner_client.data;

public class FinalTask extends Task {

    private String startTime;
    private String endTime;
    private String duration;

    public FinalTask(String name, TaskType type, int slotsQuantity, String startTime, String endTime, String duration) {
        this.name = name;
        this.type = type;
        this.slotsQuantity = slotsQuantity;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
