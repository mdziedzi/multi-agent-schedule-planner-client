package com.wsd_killers.multiagentscheduleplanner_client.Exceptions;

public class negativeValueException extends Exception{
    public negativeValueException(String msg) {
        super("Negative value in: " + msg);
    }
}
