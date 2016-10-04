package com.example.t2viat00.observerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ThreadThing.StatusInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button newThreadButton = (Button) findViewById(R.id.ntButton);
        newThreadButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0) {
                startThreads();
            }
        });

    }
    public void startThreads() {
        ThreadThing threadThing = new ThreadThing(this);
        threadThing.start();
    }

    protected void updateUI(String progress)
    {
        TextView progressText = (TextView) findViewById(R.id.progressTextView);
        String newProgress = progressText.getText().toString() + "\n" + progress;
        progressText.setText(newProgress);

    }

    @Override
    public void reportStatus(final String progress) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                updateUI(progress);
            }
        });
    }
}
