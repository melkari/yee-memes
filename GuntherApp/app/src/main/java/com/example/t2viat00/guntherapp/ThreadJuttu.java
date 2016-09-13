package com.example.t2viat00.guntherapp;

/**
 * Created by t2viat00 on 13.9.2016.
 */
import java.util.Date;

class ThreadJuttu extends Thread {
    public void run() {
        try{
            while(true){
                String date = new Date().toString();
                System.out.println(date + "GUNTHER TULE TAKAISIN!");
                sleep(5000);
            }
        }
        catch(Exception e) {
            System.out.print("thread interrupted \n");
        }
    }
}