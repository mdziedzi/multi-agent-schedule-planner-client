package com.wsd_killers.multiagentscheduleplanner_client.Exceptions;

public class TimeIntegrityException extends Throwable {
    public TimeIntegrityException(String msg) {
        super("Time integrity corrupted: " + msg);
    }
}
