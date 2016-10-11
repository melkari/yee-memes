package com.example.t2viat00.notepad;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final EditText editor = (EditText) findViewById(R.id.editText);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String content = sharedPreferences.getString("content", "");
        editor.setText(content);
        editor.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                //mby something
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //mby something more
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                SharedPreferences.Editor speditor = sharedPreferences.edit();
                speditor.putString("content", editor.getText().toString());
                speditor.apply();
            }

        });
    }
}
