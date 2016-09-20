package com.example.t2viat00.urlapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button readURL = (Button) findViewById(R.id.urlbutton);
        final EditText line = (EditText) findViewById(R.id.editText);
        readURL.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0) {
                try {
                    String url = line.getText().toString();
                    URLreader myURLr = new URLreader(url);
                    TextView urlTextView = (TextView) findViewById(R.id.textView);
                    myURLr.start();
                    myURLr.getURLcontent();
                    for(String line : myURLr.getURLcontent()) {
                        urlTextView.setText(urlTextView.getText() + line);
                    }
                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }
}
