package Eluvian.objects;

import Eluvian.objects.Agent;

public class PriorityAgent extends Agent {
    private double priority;

    public PriorityAgent(int row, int col, Agent parent, double priority, int length) {
        super(row, col, parent, length);
        this.priority = priority;
    }

    public double getPriority() {
        return this.priority;
    }

}