package com.example.t2viat00.guntherapp;

/**
 * Created by t2viat00 on 13.9.2016.
 */
import java.util.Date;

import android.content.Context;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

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
            System.out.print(e.getMessage());
        }
    }
}