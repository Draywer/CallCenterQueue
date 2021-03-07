package ru.idcore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int maxCalls = 10;
        int timerInCall = 500;
        int countOperators = 5;
        CallCenter callCenter = new CallCenter(maxCalls, timerInCall, countOperators);

        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        service.submit(callCenter);

        for (int i = 0; i < maxCalls; i++) {
            service.submit(new Call(callCenter));
            Thread.sleep(timerInCall);
        }

        service.awaitTermination(3, TimeUnit.SECONDS);
        service.shutdown();
    }
}
