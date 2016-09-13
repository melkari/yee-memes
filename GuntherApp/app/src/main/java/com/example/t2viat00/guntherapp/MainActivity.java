package com.example.t2viat00.guntherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button startstopButton;
    boolean state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startstopButton = (Button) findViewById(R.id.startstopButton);
        startstopButton.setOnClickListener(MainActivity.this);
    }
    @Override
    public void onClick(View v) {
        ThreadJuttu yee = new ThreadJuttu();
        if(!state){
            yee.start();
            startstopButton.setText("Stop");
            state = true;
        }
        else
        {
            yee.interrupt();
            startstopButton.setText("Start");
            state = false;
        }
    }
}

