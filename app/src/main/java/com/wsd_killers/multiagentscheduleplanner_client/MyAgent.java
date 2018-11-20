package com.wsd_killers.multiagentscheduleplanner_client;

import jade.core.Agent;

public class MyAgent extends Agent {

    protected void setup() {

        System.out.println("Agent: I'm alive!\n");

    }

    @Override
    protected void takeDown() {

        System.out.println("Agent: I'm dead!\n");

    }
}
