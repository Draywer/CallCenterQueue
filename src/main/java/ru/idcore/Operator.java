package ru.idcore;

public class Operator extends Thread {
    Logger logger = Logger.getInstance();
    private CallCenter callCenter;

    public Operator(CallCenter callCenter) {
        this.callCenter = callCenter;
    }

    public CallCenter getCallCenter() {
        return callCenter;
    }

    public void setCallCenter(CallCenter callCenter) {
        this.callCenter = callCenter;
    }

    @Override
    public void run() {

        int time = callCenter.getTimerInCall() * callCenter.getCountOperators();
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println(getName() + " начал прием звонков...");
        logger.log(getName() + " начал прием звонков...");
        Call call;
        String prefix = getName() + " принял вызов: ";
        while ((call = callCenter.getCallQueue().poll()) != null) {
            //System.out.println(prefix + call);
            logger.log(prefix + call);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //System.out.println(getName() + " окончил прием звонков...");
        logger.log(getName() + " окончил прием звонков...");
    }

}

