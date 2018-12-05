package com.wsd_killers.multiagentscheduleplanner_client.data;

public abstract class Task {

    protected String name;
    protected TaskType type;
    protected int slotsQuantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public int getSlotsQuantity() {
        return slotsQuantity;
    }

    public void setSlotsQuantity(int slotsQuantity) {
        this.slotsQuantity = slotsQuantity;
    }
}