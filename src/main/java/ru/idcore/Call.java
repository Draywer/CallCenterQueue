package ru.idcore;

public class Call extends Thread {
    private static final int SIZE_CODE = 999;
    private static final int SIZE_NUMBER = 9_999_999;
    private final int code;
    private final int number;
    private CallCenter callCenter;
    Logger logger = Logger.getInstance();

    public Call(CallCenter callCenter) {
        this.callCenter = callCenter;
        code = (int) (Math.random() * ((SIZE_CODE + 1)));
        number = (int) (Math.random() * ((SIZE_NUMBER + 1)));
    }

    public int getCode() {
        return code;
    }

    public int getNumber() {
        return number;
    }

    private String getCallNumber() {
        return "+7 (" + code + ") " + number;
    }

    @Override
    public String toString() {
        return getCallNumber();
    }

    @Override
    public void run() {
        callCenter.setNewCall(this);
    }
}
