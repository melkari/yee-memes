package com.example.t2viat00.observerapp;

import java.util.Date;

public class ThreadThing extends Thread {
    private String progress;

    public interface StatusInterface {
        public void reportStatus(String progress);
    }

    StatusInterface statusInterface;
    public ThreadThing(StatusInterface cb) {
        this.statusInterface = cb;
    }

    @Override
    public void run() {
        try {
            long threadId = Thread.currentThread().getId();
            progress = "Created new Thread ID: " + threadId + ".";
            statusInterface.reportStatus(progress);
            for(int i = 10; i <100; i+=10){
                Thread.sleep(3000);
                progress = "Thread ID: " + threadId + " On Progress: " + i + "%";
                statusInterface.reportStatus(progress);
            }
            Thread.sleep(3000);
            progress = "Thread ID: " + threadId + " Complete.";
            statusInterface.reportStatus(progress);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}