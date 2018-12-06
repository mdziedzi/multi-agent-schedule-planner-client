package com.wsd_killers.multiagentscheduleplanner_client.Exceptions;

public class BadFormatException extends Throwable {
    public BadFormatException(String toDoTaskName) {
        super("Błąd formatu w: " + toDoTaskName);
    }
}
