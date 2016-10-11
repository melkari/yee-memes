package com.example.t2viat00.glideexercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.androidannotations.annotations.ViewById;

public class MainActivity extends AppCompatActivity {

    @ViewById
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Glide.with(this).load("https://github.com/melkari/yee-memes/blob/master/tbfDhAj.png?raw=true").into(imageView);
    }


}
