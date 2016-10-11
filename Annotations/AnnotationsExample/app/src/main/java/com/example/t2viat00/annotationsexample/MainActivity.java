package com.example.t2viat00.annotationsexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    ArrayList<String> urlcontent = new ArrayList<String>();

    @ViewById
    EditText editText;

    @ViewById(R.id.textView)
    TextView textView;

    @Click
    void button() {
        backgroundOperation(editText.getText().toString());
    }

    @Background
    void backgroundOperation(String url) {
        try {
            URL myURL = new URL(url);
            URLConnection yc = myURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            while((inputLine = in.readLine()) != null) {
                urlcontent.add(inputLine);
            }
            in.close();
            updateUi(urlcontent);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @UiThread
    void updateUi(ArrayList<String> urlcontentgot) {
        for(String line : urlcontentgot) {
            String textviewtext = textView.getText().toString() + line;
            textView.setText(textviewtext);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
