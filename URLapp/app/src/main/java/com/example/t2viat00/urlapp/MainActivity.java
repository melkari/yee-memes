package com.example.t2viat00.urlapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements URLreader.CommInterface{
    URLreader myURLr = new URLreader(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button readURL = (Button) findViewById(R.id.urlbutton);

        readURL.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0) {
                aloita();
            }
        });
    }

    public void aloita()
    {
        try {
            final EditText line = (EditText) findViewById(R.id.editText);
            String url = line.getText().toString();
            myURLr.getURLdata(url);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    protected void updateUI()
    {
        TextView urlTextView = (TextView) findViewById(R.id.textView);
        for(String line : myURLr.getURLcontent()) {
            String textviewtext = urlTextView.getText().toString() + line;
            urlTextView.setText(textviewtext);
        }
    }

    @Override
    public void urlContentAvailable() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                updateUI();
            }
        });
    }
}
