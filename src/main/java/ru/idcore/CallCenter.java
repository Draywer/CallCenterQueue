package ru.idcore;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CallCenter extends Thread {
    private ConcurrentLinkedQueue<Call> callQueue;
    private final int maxCalls;
    private final int timerInCall;
    private int countOperators;
    private List<Operator> operators;
    Logger logger = Logger.getInstance();


    public CallCenter(int maxCalls, int timerInCall, int countOperators) {
        this.callQueue = new ConcurrentLinkedQueue<>();
        this.timerInCall = timerInCall;
        operators = new ArrayList<>();
        this.countOperators = countOperators;
        createOperators(countOperators);
        this.maxCalls = maxCalls;

    }

    public void setNewCall(Call call) {
        //System.out.println("Входящий звонок: " + call);
        logger.log("Входящий звонок: " + call);
        callQueue.offer(call);
    }

    public int getCountOperators() {
        return countOperators;
    }

    public void setCountOperators(int countOperators) {
        this.countOperators = countOperators;
    }

    public List<Operator> getOperators() {
        return operators;
    }

    public void setOperators(List<Operator> operators) {
        this.operators = operators;
    }

    public int getTimerInCall() {
        return timerInCall;
    }


    public ConcurrentLinkedQueue<Call> getCallQueue() {
        return callQueue;
    }

    public void setCallQueue(ConcurrentLinkedQueue<Call> callQueue) {
        this.callQueue = callQueue;
    }

    public int getMaxCalls() {
        return maxCalls;
    }

    private void createOperators(int countOperators) {
        for (int i = 0; i < countOperators; i++) {
            Operator operator = new Operator(this);
            operator.setName("Опреатор-" + i);
            operators.add(operator);
        }
    }

    @Override
    public void run() {
        for (Operator operator : operators
        ) {
            operator.start();
        }
    }
}
