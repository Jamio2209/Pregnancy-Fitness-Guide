package com.zerotechy.pregnancyfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;



public class About extends AppCompatActivity {
TextView abouttxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about);
        abouttxt=(TextView)findViewById(R.id.abouttxt);
        abouttxt.setText(getResources().getString(R.string.about));




    }
}