package com.example.t2viat00.guntherapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button startstopButton;
    boolean state = false;
    ThreadJuttu currentThread = new ThreadJuttu();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startstopButton = (Button) findViewById(R.id.startstopButton);
        startstopButton.setOnClickListener(MainActivity.this);
    }
    @Override
    public void onClick(View v) {
        try {
            if(!state){
                startstopButton.setText("Stop");
                state = true;
                ThreadJuttu newThread = new ThreadJuttu();
                currentThread = newThread;
                newThread.start();
            }
            else
            {
                startstopButton.setText("Start");
                state = false;
                currentThread.interrupt();
            }
        }
        catch (Exception e) {
            System.out.print(e.getMessage() + "\n");
        }

    }
}

